/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cdiddy.utils.application;

import cdiddy.objects.GameWeek;
import cdiddy.objects.Team;
import cdiddy.utils.system.OAuthConnection;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.scribe.model.Verb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author cedric
 */

@Repository("gameService")
public class GameService 
{
    
    @Autowired
    OAuthConnection conn;
    
    
    public List<GameWeek> retrieveGameWeeks()
    {
        
        List<Team> result = null;
        ObjectMapper mapper = new ObjectMapper();
        Map<String,Object> userData;
        Map<String, List<Map<String, Object>>> params;
        Map<String,Object> league;
        boolean morePlayers = true;
        int curr = 0; 
      String response2 = conn.requestData("http://fantasysports.yahooapis.com/fantasy/v2/users;use_login=1/games;game_keys=nfl/teams/standings?format=json", Verb.GET);
        try 
        {
            userData = mapper.readValue(response2, Map.class);
            params = (Map<String, List<Map<String, Object>>>)userData.get("fantasy_content");
            Object game_weeks = ((Map<String, List<Map<String, Object>>>)params).get("game").get(1).get("game_weeks");
            int count = ((Integer)((Map<String, Object> )game_weeks).get("count"));
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(GameService.class.getName()).log(Level.SEVERE, null, ex);
        } 
     
    }
}
