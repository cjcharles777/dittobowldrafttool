/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cdiddy.services.rest;

import cdiddy.objects.Player;
import cdiddy.utils.system.DataRequestCaller;
import cdiddy.utils.system.JacksonPojoMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author cedric
 */

@Repository("playerRESTUtil")
public class PlayersRESTService 
{
    private String BaseURL = "http://donkeigy.endofinternet.net:18077/fantasy-football-data-service/restServices/";

    
    public Player retrivePlayer(int playerid) 
    {
        try 
        {
            ObjectMapper mapper = new ObjectMapper();
            String URL = BaseURL+"players/retrievePlayer/"+playerid;
            String jsonResult = DataRequestCaller.requestData(URL, "GET");
            Player result = mapper.readValue(jsonResult, Player.class);
           return result;
        } 
        catch (Exception ex) 
        {
            Logger.getLogger(PlayersRESTService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } 
       
    }
    
    public Player retrivePlayerWithYahooKey(String playerKey) 
    {
        try 
        {
            ObjectMapper mapper = new ObjectMapper();
            String URL = BaseURL+"retrievePlayer/playerkey/"+playerKey;
            String jsonResult = DataRequestCaller.requestData(URL, "GET");
            Player result = mapper.readValue(jsonResult, Player.class);
           return result;
        } 
        catch (Exception ex) 
        {
            Logger.getLogger(PlayersRESTService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } 
       
    }
    
    public List<Player> retrivePlayers(int firstResult, int maxResults) 
    {
        try 
        {
            ObjectMapper mapper = new ObjectMapper();
            String URL = BaseURL+"players/retrievePlayers/"+firstResult+"/"+maxResults;
            String jsonResult = DataRequestCaller.requestData(URL, "GET");
            List<Map> retrievedList = mapper.readValue(jsonResult, List.class);
            List<Player> result = new LinkedList<Player>();
            for(Map temp : retrievedList)
            {
               Player tempPlayer = mapper.readValue(JacksonPojoMapper.toJson(temp, false) , Player.class);
               result.add(tempPlayer);
            }
           return result;
        } 
        catch (Exception ex) 
        {
            Logger.getLogger(PlayersRESTService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } 
    }
    
}
