/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cdiddy.utils.application;

import cdiddy.objects.StatCategories;
import cdiddy.utils.system.JacksonPojoMapper;
import cdiddy.utils.system.OAuthConnection;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
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
@Repository("statsService")
public class StatsService 
{
    @Autowired
    OAuthConnection conn;
        
        
    public List<StatCategories> retrieveStatCategories()
    {
            Map<String,Object> userData;
            Map<String,Object> params;
            ArrayList<Map> statsCats;
            List<StatCategories> result = new ArrayList<StatCategories>();
            ObjectMapper mapper = new ObjectMapper();
            String response = conn.requestData( "http://fantasysports.yahooapis.com/fantasy/v2/game/nfl/stat_categories?format=json", Verb.GET);
        try 
        {
            userData = mapper.readValue(response, Map.class);
            params = (Map<String, Object>)userData.get("fantasy_content");
            Map testies = mapper.readValue(JacksonPojoMapper.toJson((((ArrayList<Map>)params.get("game")).get(1)), false) , Map.class);
            statsCats = ((Map<String, Map<String, ArrayList<Map>>>)testies).get("stat_categories").get("stats");
           // List<StatCategories> result = new ArrayList<StatCategories>();
            for(Map<String,Map> statsCat : statsCats)
            {
                
                StatCategories tempSc = mapper.readValue(JacksonPojoMapper.toJson(statsCat.get("stat"), false) , StatCategories.class);
                result.add(tempSc);
            }
            System.out.println(statsCats.size());
        } catch (IOException ex) {
            Logger.getLogger(StatsService.class.getName()).log(Level.SEVERE, null, ex);
        }
      

            
           // mapper.readValue(JacksonPojoMapper.toJson(trxObj, false), Positions.class);
        return result;
    }
}
