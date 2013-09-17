/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cdiddy.utils.application;

import cdiddy.objects.Roster;
import cdiddy.objects.Team;
import cdiddy.objects.TeamStandings;
import cdiddy.objects.league.YahooLeague;
import cdiddy.utils.system.JacksonPojoMapper;
import cdiddy.utils.system.OAuthConnection;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    @Autowired
    private YQLQueryUtil yqlUitl ;
    
    public List<Team> loadUserTeams()
    {
        ObjectMapper mapper = new ObjectMapper();
            Map<String,Object> userData;
            Map<String,Object> results;
            List<Map<String, Object>> teamList;
            Map<String,Object> query;
            List<Team>  teamListResults = new LinkedList<Team>();
           
            List<String> yqlList = new LinkedList<String>();
            String yql = "select * from fantasysports.teams where game_key = 'nfl' and use_login=1";
            yqlList.add(yql);
            yql = "select * from fantasysports.teams where game_key = '273' and use_login=1";
            yqlList.add(yql);
            yql = "select * from fantasysports.teams where game_key = '257' and use_login=1";
            yqlList.add(yql);
            yql = "select * from fantasysports.teams where game_key = '242' and use_login=1";
            yqlList.add(yql);
            yql = "select * from fantasysports.teams where game_key = '222' and use_login=1";
            yqlList.add(yql);
            yql = "select * from fantasysports.teams where game_key = '199' and use_login=1";
            yqlList.add(yql);
            for(String ql : yqlList)
            {
                String response = yqlUitl.queryYQL(ql);
                try
                {
                    userData = mapper.readValue(response, Map.class);
                    query = (Map<String, Object>)userData.get("query"); // query details
                    results = (Map<String, Object>)query.get("results"); //result details
                    teamList = (List<Map<String, Object>>)results.get("team"); //result details
                    for (Map map : teamList)
                    {
                        Team tempTeam = mapper.readValue(JacksonPojoMapper.toJson(map, false) , Team.class);
                        teamListResults.add(tempTeam);
                    }


                }
                catch(Exception e)
                {
                     Logger.getLogger(TeamService.class.getName()).log(Level.SEVERE, null, e);
                }
            }
            return teamListResults;
        //
    }
    public List<Team> loadUserTeams(List<YahooLeague> ylList)
    {
        
            ObjectMapper mapper = new ObjectMapper();
            Map<String,Object> userData;
            Map<String,Object> results;
            List<Map<String, Object>> teamList;
            Map<String,Object> query;
            List<Team>  teamListResults = new LinkedList<Team>();
            for(YahooLeague yl : ylList)
            {
                String yql = "select * from fantasysports.teams where league_key = '"+yl.getLeague_key()+"'";
                String response = yqlUitl.queryYQL(yql);
                try
                {
                    userData = mapper.readValue(response, Map.class);
                    query = (Map<String, Object>)userData.get("query"); // query details
                    results = (Map<String, Object>)query.get("results"); //result details
                    teamList = (List<Map<String, Object>>)results.get("team"); //result details
                    for (Map map : teamList)
                    {
                        Team tempTeam = mapper.readValue(JacksonPojoMapper.toJson(map, false) , Team.class);
                        teamListResults.add(tempTeam);
                    }


                }
                catch(Exception e)
                {
                     Logger.getLogger(TeamService.class.getName()).log(Level.SEVERE, null, e);
                }
            }
            return teamListResults;
    }
    
    public List<Team> loadLeaugeTeams(String leaugeid)
    {
        List<Team> result = null;
        ObjectMapper mapper = new ObjectMapper();
        Map<String,Object> userData;
        Map<String,Object> params;
        Map<String,Object> league;
        int curr = 0; 


        String response2 = conn.requestData("http://fantasysports.yahooapis.com/fantasy/v2/league/" + leaugeid +"/teams/standings?format=json", Verb.GET);
        try 
        {
            
            userData = mapper.readValue(response2, Map.class);
            params = (Map<String, Object>)userData.get("fantasy_content");
            league = ((List<Map<String, Map<String,Object>>>)(params.get("league"))).get(1).get("teams");
            int count;
            count = (Integer)league.get("count");
            while(curr < count)
            {
                List<Object> lmpTeams = (List<Object>)(((Map<String,List<Object>>)league.get((new Integer(curr)).toString())).get("team").get(0));
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
            String response2 = conn.requestData("http://fantasysports.yahooapis.com/fantasy/v2/team/"+team.getTeam_key()+"/standings?format=json", Verb.GET);
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
    public Roster getRoster (String teamKey, int week)
    {
            ObjectMapper mapper = new ObjectMapper();
            Map<String,Object> userData;
            Map<String,Object> results;
            Map<String,Object> team;
            Map<String,Object> query;
            Roster rosterResults = null;
            String yql = "select * from fantasysports.teams.roster where team_key='"+ teamKey +"' and week='"+ week +"'";
            String response = yqlUitl.queryYQL(yql);
            try
            {
                userData = mapper.readValue(response, Map.class);
                query = (Map<String, Object>)userData.get("query"); // query details
                results = (Map<String, Object>)query.get("results"); //result details
                team = (Map<String, Object>)results.get("team"); //result details
                Roster roster = mapper.readValue(JacksonPojoMapper.toJson((Map)team.get("roster"), false) , Roster.class);
                rosterResults = roster;
            }
            catch(Exception e)
            {
                 Logger.getLogger(TeamService.class.getName()).log(Level.SEVERE, null, e);
            }
             
             return rosterResults;
    }
    
    public Roster getRoster2 (String teamKey, int week)
    {
            ObjectMapper mapper = new ObjectMapper();
            Map<String,Object> userData;
            Map<String,Object> results;
            Map<String,Object> team;
            Map<String,Object> query;
            Roster rosterResults = null;
            String yql = "select * from fantasysports.teams.roster where team_key='"+ teamKey +"' and week='"+ week +"'";
            String response = yqlUitl.queryYQL(yql);
            try
            {
                userData = mapper.readValue(response, Map.class);
                query = (Map<String, Object>)userData.get("query"); // query details
                results = (Map<String, Object>)query.get("results"); //result details
                team = (Map<String, Object>)results.get("team"); //result details
                Roster roster = mapper.readValue(JacksonPojoMapper.toJson((Map)team.get("roster"), false) , Roster.class);
                rosterResults = roster;
            }
            catch(Exception e)
            {
                 Logger.getLogger(TeamService.class.getName()).log(Level.SEVERE, null, e);
            }
             
             return rosterResults;
    }
    private Team convertToTeam(List<Object> lmpTeams) 
    {
        Team result = new Team();
        for(Object col : lmpTeams)
        {
            if(col instanceof LinkedHashMap)
            {
                Map temp = (Map) col;
                if(temp.get("team_key")!= null)
                {
                result.setTeam_key((String)temp.get("team_key"));
                }
                else if(temp.get("team_id")!= null)
                {
                    result.setTeam_id((String) temp.get("team_id"));
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
                    result.setNumber_of_moves(Integer.toString(numMovesInt));
                }
            }
         }
        return result;
    }
}

