/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cdiddy.fantasyfootballapp;

import cdiddy.dao.PlayersDAO;
import cdiddy.fantasyfootballapp.conversion.util.ResourceUtil;
import cdiddy.fantasyfootballapp.fantasyfootballconversion.concurrency.PlayerMatchingCallable;
import cdiddy.fantasyfootballapp.fantasyfootballconversion.objects.Game;
import cdiddy.fantasyfootballapp.fantasyfootballconversion.objects.NFLPlayer;
import cdiddy.objects.Player;
import cdiddy.utils.system.JacksonPojoMapper;
import java.io.InputStream;
import java.security.CodeSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author DMDD
 */
public class ConversionApp {
     private static final ApplicationContext applicationContext = new ClassPathXmlApplicationContext("app-config.xml");
     private static final PlayersDAO PLAYERS_DAO = applicationContext.getBean(PlayersDAO.class);
     private static final int NTHREDS = 15;
        public static void main( String[] args )
    {
        try {
            System.out.println( "Hello World!" );

            CodeSource src = App.class.getProtectionDomain().getCodeSource();
           // List<String> list = new ArrayList<String>();
            ObjectMapper mapper = new ObjectMapper();
            
            InputStream input = App.class.getResourceAsStream("/players/players.json");

            ExecutorService pool = Executors.newFixedThreadPool(10);
            Map<String, Future<Player>> futureMap = new HashMap<String, Future<Player>>();
            Map<String, Object> testme = mapper.readValue(input, Map.class);
            Map<String, Player> playerMap = new HashMap<String, Player>();
            Map<String, NFLPlayer> retiredPlayerMap = new HashMap<String, NFLPlayer>();
            List<Future<Player>> list = new LinkedList<Future<Player>>();
            ExecutorService executor = Executors.newFixedThreadPool(NTHREDS);
            Iterator it = testme.entrySet().iterator();
            while (it.hasNext()) 
            {
                Map.Entry pairs = (Map.Entry)it.next();
                NFLPlayer player = mapper.readValue(JacksonPojoMapper.toJson(pairs.getValue(), false) , NFLPlayer.class);

                Callable<Player> callable = new PlayerMatchingCallable(player, PLAYERS_DAO);
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

            String[] spam = ResourceUtil.getResourceListing(App.class, "nfldata/");
             List<String> fileNameList = Arrays.asList(spam);
            
         
            for(String fileName : fileNameList)
            {
                input = App.class.getResourceAsStream("/nfldata/"+fileName);

                String name = fileName;
                int pos = name.lastIndexOf(".");
                if (pos > 0) {
                    name = name.substring(0, pos);
                }
                testme = mapper.readValue(input, Map.class);
                Object gameObj = testme.get(name);
                Game game = mapper.readValue(JacksonPojoMapper.toJson(testme.get(name), false) , Game.class);
                System.out.println( "JSON converted into POJO" );
            }
           
            System.out.println( "Conversion done!!!!" );
            
        } 
        catch (Exception ex) 
        {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } 

    }
    
}
 