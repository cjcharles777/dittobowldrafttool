/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cdiddy.services.rest;

import cdiddy.objects.Player;
import cdiddy.utils.system.DataRequestCaller;
import java.util.List;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author cedric
 */
public class PlayersRESTService 
{
    private String BaseURL = "http://donkeigy.endofinternet.net:18077/fantasy-football-data-service/restServices/";

    
    public Player retrivePlayer(int playerid) 
    {
        ObjectMapper mapper = new ObjectMapper();
        String URL = BaseURL+"players/retrievePlayer/"+playerid;
        String jsonResult = DataRequestCaller.requestData(URL, "GET");
        
       
        URLConnection urlConnection =  new URL(fetchUrl).openConnection();
    urlConnection.connect();
    JsonReader reader = new JsonReader(
         new InputStreamReader(urlConnection.getInputStream()));
    JsonParser parser = new JsonParser();
    JsonElement rootElement = parser.parse(reader);
    JsonArray tweetsJson = rootElement.getAsJsonArray();
         return playersDAOImpl.getPlayerbyYahooId(playerid);
    }
    
    public List<Player> retrivePlayers(int firstResult, int maxResults) 
    {
        return playersDAOImpl.getPlayers(firstResult, maxResults);
    }
    
}
