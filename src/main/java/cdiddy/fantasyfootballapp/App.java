package cdiddy.fantasyfootballapp;


import cdiddy.gui.FantasyFootballFrame;
import cdiddy.objects.Player;
import cdiddy.objects.SeasonStat;
import cdiddy.utils.application.PlayerService;
import cdiddy.utils.application.StatsService;
import cdiddy.utils.system.OAuthConnection;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Verb;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
      private static final ApplicationContext applicationContext = 
        new ClassPathXmlApplicationContext("app-config.xml");
        private static OAuthConnection conn = applicationContext.getBean(OAuthConnection.class);
        private static PlayerService playerUtil = applicationContext.getBean(PlayerService.class);
       private static StatsService statsService = applicationContext.getBean(StatsService.class); 
    
       
       public static void main( String[] args )
    {
        if(args.length > 0 && args[0].equals("load"))
        {
            loadFreshData();
        }
        else
        {
            FantasyFootballFrame.main(args);
        }
         
    }
    public static void loadFreshData()
    {
        playerUtil.primePlayersDatabase();
        statsService.primeStatCategories();
        statsService.primeStats();
        playerUtil.loadPlayers();
        statsService.loadStatCategories();
    }
}
