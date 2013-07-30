/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cdiddy.utils.application;

import cdiddy.dao.GameWeekDAO;
import cdiddy.dao.PlayersDAO;
import cdiddy.dao.SeasonStatsDAO;
import cdiddy.dao.StatDAO;
import cdiddy.dao.WeeklyStatsDAO;
import cdiddy.objects.GameWeek;
import cdiddy.objects.Player;
import cdiddy.util.fantasyfootballconversion.concurrency.GameProcessingWorker;
import cdiddy.util.fantasyfootballconversion.concurrency.PlayerMatchingCallable;
import cdiddy.util.fantasyfootballconversion.objects.Game;
import cdiddy.util.fantasyfootballconversion.objects.NFLPlayer;
import cdiddy.utils.system.JacksonPojoMapper;
import cdiddy.utils.system.ZipUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Cedric
 */
@Repository("conversionService")
public class ConversionService 
{
    
    @Autowired
    private PlayersDAO playersDAO;
    @Autowired
    private GameWeekDAO gameWeekDAO;
    @Autowired
    private GameService gameService;
    @Autowired
    private StatDAO statDAO;
    @Autowired
    private WeeklyStatsDAO weeklyStatsDAO;
    @Autowired
    private SeasonStatsDAO seasonStatsDAO;
    
    private static final int NTHREDS = 15;

    private void primeAndRetrieveGameWeek()
    {
        List<GameWeek> result = gameService.retrieveHistoricalGameWeeks();
        if(result != null && result.size() > 0)
        { 
            gameWeekDAO.clearGameWeek();
            gameWeekDAO.saveGameWeek(result);
            statDAO.clearStats();
            seasonStatsDAO.clearSeasonStat();
            weeklyStatsDAO.clearWeeklyStat();
            
        }
        
    }
    
    
    public void convertFromFile(File file) throws Exception
    {
        System.out.println( "Hello World!" );

        //CodeSource src = App.class.getProtectionDomain().getCodeSource();
       // List<String> list = new ArrayList<String>();
        ObjectMapper mapper = new ObjectMapper();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH);
        File tmpDir = new File("tmp");
        if (!tmpDir.exists()) {
                if (tmpDir.mkdir()) {
                        System.out.println("Directory is created!");
                } else {
                        System.out.println("Failed to create directory!");
                }
        }

        ZipUtil.unzip(file, tmpDir);

        InputStream input = new FileInputStream("tmp/players/players.json");

        ExecutorService pool = Executors.newFixedThreadPool(NTHREDS);
        Map<String, Future<Player>> futureMap = new HashMap<String, Future<Player>>();
        Map<String, Object> testme = mapper.readValue(input, Map.class);
        Map<String, Player> playerMap = new HashMap<String, Player>();
        Map<String, NFLPlayer> retiredPlayerMap = new HashMap<String, NFLPlayer>();
        List<Future<Player>> list = new LinkedList<Future<Player>>();
        Iterator it = testme.entrySet().iterator();
        while (it.hasNext()) 
        {
            Map.Entry pairs = (Map.Entry)it.next();
            NFLPlayer player = mapper.readValue(JacksonPojoMapper.toJson(pairs.getValue(), false) , NFLPlayer.class);

            Callable<Player> callable = new PlayerMatchingCallable(player, playersDAO);
            Future<Player> future = pool.submit(callable);
            futureMap.put(player.getGsisid() , future);

            it.remove(); // avoids a ConcurrentModificationException
        }
        //
        //NFLPlayer me = playerMap.get("00-0026221");
        pool.shutdown();
        while (!pool.isTerminated()) {}

       Iterator futureMapIter = futureMap.entrySet().iterator();
       while (futureMapIter.hasNext()) 
       {
           Map.Entry pairs = (Map.Entry)futureMapIter.next();
           String id = ((String)pairs.getKey());
           Player tempPlayer = ((Future<Player>) pairs.getValue()).get();
           playerMap.put( id , tempPlayer);
           if(tempPlayer != null)
           {
            System.out.println(id + " = " + tempPlayer.getPlayer_key()+ ":" + tempPlayer.getName().getFull());
           }
           futureMapIter.remove(); // avoids a ConcurrentModificationException
       }

        System.out.println( "JSON converted into POJO" );

        ThreadPoolExecutor executorPool;
        executorPool = new ThreadPoolExecutor(5, NTHREDS, new Long(10).longValue(), TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
        File folder = new File("tmp/nfldata/");
        File[] listOfFiles = folder.listFiles();
       // String[] spam = ResourceUtil.getResourceListing(App.class, "tmp/nfldata/");
         List<File> gameFileList = Arrays.asList(listOfFiles);

        primeAndRetrieveGameWeek();
        for(File gameFile : gameFileList)
        {
            input = new FileInputStream(gameFile);

            String name = gameFile.getName();
            int pos = name.lastIndexOf(".");
            if (pos > 0) {
                name = name.substring(0, pos);
            }
            testme = mapper.readValue(input, Map.class);
            Object gameObj = testme.get(name);
            Game game = mapper.readValue(JacksonPojoMapper.toJson(testme.get(name), false) , Game.class);
            System.out.println(name.substring(0, 8));
            game.setGameDate(sdf.parse(name.substring(0, 8)));
            System.out.println( "JSON converted into POJO" );
            executorPool.execute(new GameProcessingWorker(game, playerMap, playersDAO, gameWeekDAO));

        }

        System.out.println( "Conversion done!!!!" );
        //Thread.sleep(30000);
        //shut down the pool
        executorPool.shutdown();
        while (!executorPool.isTerminated()) {}
        
        playersDAO.savePlayers(new LinkedList<Player>(playerMap.values()));

        
    }

}
