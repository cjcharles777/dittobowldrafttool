package cdiddy.fantasyfootballapp;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Verb;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        
    OAuthRequest request = new OAuthRequest(Verb.GET, "http://fantasysports.yahooapis.com/fantasy/v2/users;use_login=1/games;game_keys=nfl/leagues?format=json");
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

  
    
    
    System.out.println(response.getBody());
    System.out.println(response2.getBody());
        
    System.out.println();
    System.out.println("Thats it man! Go and build something awesome with Scribe! :)");
         
    }
}
