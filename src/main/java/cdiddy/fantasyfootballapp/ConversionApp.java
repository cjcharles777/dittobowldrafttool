/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cdiddy.fantasyfootballapp;

import cdiddy.dao.PlayersDAO;
import cdiddy.dao.PlayersDAOImpl;
import cdiddy.fantasyfootballapp.conversion.util.ResourceUtil;
import cdiddy.fantasyfootballapp.fantasyfootballconversion.concurrency.PlayerMatchingCallable;
import cdiddy.fantasyfootballapp.fantasyfootballconversion.objects.Game;
import cdiddy.fantasyfootballapp.fantasyfootballconversion.objects.NFLPlayer;
import cdiddy.objects.Name;
import cdiddy.objects.Player;
import cdiddy.utils.system.JacksonPojoMapper;
import java.io.InputStream;
import java.security.CodeSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
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
        public static void main( String[] args )
    {
        try {
            System.out.println( "Hello World!" );
            CodeSource src = App.class.getProtectionDomain().getCodeSource();
            List<String> list = new ArrayList<String>();
            ObjectMapper mapper = new ObjectMapper();
            
            InputStream input = App.class.getResourceAsStream("/players/players.json");

            ExecutorService pool = Executors.newFixedThreadPool(20);
            Set<Future<Player>> set = new HashSet<Future<Player>>();
            Map<String, Object> testme = mapper.readValue(input, Map.class);
            Map<String, Player> playerMap = new HashMap<String, Player>();
            Map<String, NFLPlayer> retiredPlayerMap = new HashMap<String, NFLPlayer>();
            Iterator it = testme.entrySet().iterator();
            while (it.hasNext()) 
            {
                Map.Entry pairs = (Map.Entry)it.next();
                NFLPlayer player = mapper.readValue(JacksonPojoMapper.toJson(pairs.getValue(), false) , NFLPlayer.class);

                Callable<Player> callable = new PlayerMatchingCallable(player, PLAYERS_DAO);
                Future<Player> future = pool.submit(callable);
                set.add(future);
                it.remove(); // avoids a ConcurrentModificationException
            }
            //
            //NFLPlayer me = playerMap.get("00-0026221");
            pool.shutdown();
            while (!pool.isTerminated()) {}

            System.out.println( "JSON converted into POJO" );

            String[] spam = ResourceUtil.getResourceListing(App.class, "nfldata/");
             list = Arrays.asList(spam);
            
         
            for(String fileName : list)
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
           

            
        } 
        catch (Exception ex) 
        {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } 

    }
    
}
