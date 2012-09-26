/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cdiddy.utils.application;

import cdiddy.objects.Team;
import cdiddy.objects.TeamStandings;
import cdiddy.utils.system.JacksonPojoMapper;
import cdiddy.utils.system.OAuthConnection;
import java.io.IOException;
import java.util.LinkedHashMap;
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

@Repository("teamService")
public class TeamService 
{
    
    @Autowired
    OAuthConnection conn;
    
    
    public List<Team> loadUserTeams()
    {
        List<Team> result = null;
        ObjectMapper mapper = new ObjectMapper();
        Map<String,Object> userData;
        Map<String,Object> params;
        Map<String,Object> league;
        boolean morePlayers = true;
        int curr = 0; 


        String response2 = conn.requestData("http://fantasysports.yahooapis.com/fantasy/v2/users;use_login=1/games;game_keys=nfl/teams/standings?format=json", Verb.GET);
        try 
        {
            
            userData = mapper.readValue(response2, Map.class);
            params = (Map<String, Object>)userData.get("fantasy_content");
            league = ((Map<String, Map<String, List<Map<String,Map<String,Map<String,List<Map<String,Map<String,Object>>>>>>>>>)params.get("users")).get("0").get("user").get(1).get("games").get("0").get("game").get(1).get("teams");
            int count;
            count = (Integer)league.get("count");
            while(curr < count)
            {
                List<Map> lmpTeams = (List<Map>)(((Map<String,List<Object>>)league.get((new Integer(curr)).toString())).get("team").get(0));
                Team tempTeam = convertToTeam(lmpTeams);
                if(result == null)
                {
                    result = new LinkedList<Team>();
                }
               // tempTeam = loadTeamStandings(tempTeam);
                Map<String, Map> ts = (Map<String, Map>)((Map<String,List<Object>>)league.get((new Integer(curr)).toString())).get("team").get(1);
                 TeamStandings tempStand = mapper.readValue(JacksonPojoMapper.toJson(ts.get("team_standings"), false) , TeamStandings.class);
                 tempTeam.setStandings(tempStand);
                result.add(tempTeam);
                curr++;
            }

        } catch (IOException ex) 
        {
            Logger.getLogger(TeamService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    public List<Team> loadLeaugeTeams(String leaugeid)
    {
        List<Team> result = null;
        ObjectMapper mapper = new ObjectMapper();
        Map<String,Object> userData;
        Map<String,Object> params;
        Map<String,Object> league;
        int curr = 0; 


        String response2 = conn.requestData("http://fantasysports.yahooapis.com/fantasy/v2/league/nfl.l." + leaugeid +"/teams/standings?format=json", Verb.GET);
        try 
        {
            
            userData = mapper.readValue(response2, Map.class);
            params = (Map<String, Object>)userData.get("fantasy_content");
            league = ((List<Map<String, Map<String,Object>>>)(params.get("league"))).get(1).get("teams");
            int count;
            count = (Integer)league.get("count");
            while(curr < count)
            {
                List<Map> lmpTeams = (List<Map>)(((Map<String,List<Object>>)league.get((new Integer(curr)).toString())).get("team").get(0));
                Team tempTeam = convertToTeam(lmpTeams);
                if(result == null)
                {
                    result = new LinkedList<Team>();
                }
               // tempTeam = loadTeamStandings(tempTeam);
                Map<String, Map> ts = (Map<String, Map>)((Map<String,List<Object>>)league.get((new Integer(curr)).toString())).get("team").get(1);
                 TeamStandings tempStand = mapper.readValue(JacksonPojoMapper.toJson(ts.get("team_standings"), false) , TeamStandings.class);
                 tempTeam.setStandings(tempStand);
                result.add(tempTeam);
                curr++;
            }

        } catch (IOException ex) 
        {
            Logger.getLogger(TeamService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    public Team loadTeamStandings(Team team)
    {
        Map<String,Object> userData;
        Map<String,List<Map<String, Object>>> params;
        ObjectMapper mapper = new ObjectMapper();
        try 
        {              
            String response2 = conn.requestData("http://fantasysports.yahooapis.com/fantasy/v2/team/"+team.getTeamKey()+"/standings?format=json", Verb.GET);
            userData = mapper.readValue(response2, Map.class);
            params = (Map<String,List<Map<String, Object>>>)userData.get("fantasy_content");
            
            Map standing = (Map) params.get("team").get(1).get("team_standings");
             TeamStandings tempStand = mapper.readValue(JacksonPojoMapper.toJson(standing, false) , TeamStandings.class);
             team.setStandings(tempStand);
        }
       catch (IOException ex) 
        {
            Logger.getLogger(TeamService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return team;
  
    }
    private Team convertToTeam(List<Map> lmpTeams) 
    {
        Team result = new Team();
        for(Object col : lmpTeams)
        {
            if(col instanceof LinkedHashMap)
            {
                Map temp = (Map) col;
                if(temp.get("team_key")!= null)
                {
                result.setTeamKey((String)temp.get("team_key"));
                }
                else if(temp.get("team_id")!= null)
                {
                    result.setTeamId((String) temp.get("team_id"));
                }
                else if(temp.get("name")!= null)
                {
                result.setName ((String) temp.get("name"));
                }
                else if(temp.get("url")!= null)
                {
                result.setTeamurl((String) temp.get("url"));
                }
                else if(temp.get("team_logos")!= null)
                {
                    List<Map<String,Map<String, String>>> tempList = 
                            (List<Map<String,Map<String, String>>>) temp.get("team_logos");
                    
                    String url = tempList.get(0).get("team_logo").get("url");
                    result.setTeamLogoUrl(url);
                
                }
                else if(temp.get("number_of_moves")!= null)
                {
                    Object numMoves = temp.get("number_of_moves");
                    int numMovesInt;
                    if(numMoves instanceof String)
                    {
                        numMovesInt = Integer.parseInt((String)numMoves);
                    }
                    else
                    {
                     numMovesInt = (Integer)numMoves;
                    }
                    result.setNumberOfMoves(numMovesInt);
                }
            }
         }
        return result;
    }
}

