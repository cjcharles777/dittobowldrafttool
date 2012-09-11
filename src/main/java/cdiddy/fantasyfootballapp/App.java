package cdiddy.fantasyfootballapp;


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
    public static void main( String[] args )
    {
 
     OAuthConnection conn = applicationContext.getBean(OAuthConnection.class);
      PlayerService playerUtil = applicationContext.getBean(PlayerService.class);
     conn.connect();
         ObjectMapper mapper = new ObjectMapper();
     Map<String,Object> userData;
     Map<String,Object> params;
     ArrayList league;
     List<Player> playerObjList;
     String response = conn.requestData( "http://fantasysports.yahooapis.com/fantasy/v2/users;use_login=1/games;game_keys=nfl/leagues?format=json", Verb.GET);
     String response2 = conn.requestData( "http://fantasysports.yahooapis.com/fantasy/v2/players;player_keys=nfl.p.4263,nfl.p.4262/stats?format=json", Verb.GET);

     
     StatsService statsService = applicationContext.getBean(StatsService.class);
     LinkedList<Player> listP = new LinkedList<Player>();
     Player p = new Player();
     p.setYahooId(4262);
     listP.add(p);
     p = new Player();
     p.setYahooId(24788);
     listP.add(p);
             
     Map<Integer, List<SeasonStat>> result = statsService.retrieveSeasonStats(listP);
     
     /**   OAuthRequest request = new OAuthRequest(Verb.GET, "http://fantasysports.yahooapis.com/fantasy/v2/users;use_login=1/games;game_keys=nfl/leagues?format=json");
    service.signRequest(accessToken, request); // the access token from step 4
    Response response = (Response) request.send();
    
    OAuthRequest request2 = new OAuthRequest(Verb.GET, "http://fantasysports.yahooapis.com/fantasy/v2/league/273.l.8899/players;status=A;count=100?format=json");
    service.signRequest(accessToken, request2); // the access token from step 4
    Response response2 = (Response) request2.send();
    ObjectMapper mapper = new ObjectMapper();
    Map<String,Object> userData;
     Map<String,Object> params;
     ArrayList league;
        try {
            userData = mapper.readValue(response2.getBody(), Map.class);
            params = (Map<String, Object>)userData.get("fantasy_content");
             league = (ArrayList)params.get("league");
             if(league != null)
             {
                league.get(1);
             }
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } 

  
    
    
    System.out.println(response.getBody()); **/
    System.out.println(response);
     System.out.println(response2);       
    System.out.println();
    System.out.println("Thats it man! Go and build something awesome with Scribe! :)");
         
    }
}
