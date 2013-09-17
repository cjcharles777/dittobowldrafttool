/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cdiddy.utils.application;

import cdiddy.dao.SavedYahooLeagueDAO;
import cdiddy.objects.GameWeek;
import cdiddy.objects.draft.DraftResults;
import cdiddy.objects.league.YahooLeague;
import cdiddy.objects.league.YahooLeagueSettings;
import cdiddy.utils.system.JacksonPojoMapper;
import cdiddy.utils.system.OAuthConnection;
import java.io.IOException;
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

@Repository("gameService")
public class GameService 
{
    
    @Autowired
    private OAuthConnection conn;
    @Autowired
    private YQLQueryUtil yqlUitl ;
    @Autowired
    private SavedYahooLeagueDAO savedYahooLeagueDAOImpl;
    
    
    public List<GameWeek> retrieveGameWeeks()
    {
        
        List<GameWeek> result = null;
        ObjectMapper mapper = new ObjectMapper();
        Map<String,Object> userData;
        Map<String, List<Map<String, Object>>> params;
        Map<String,Object> league;
        boolean morePlayers = true;
        int curr = 0; 
      String response2 = conn.requestData("http://fantasysports.yahooapis.com/fantasy/v2/game/nfl/game_weeks?format=json", Verb.GET);
        try 
        {
            userData = mapper.readValue(response2, Map.class);
            params = (Map<String, List<Map<String, Object>>>)userData.get("fantasy_content");
            Object game_weeks = ((Map<String, List<Map<String, Object>>>)params).get("game").get(1).get("game_weeks");
            int count = ((Integer)((Map<String, Object> )game_weeks).get("count"));
            int pos = 0;
            while (pos<count)
            {
                Map gameweek = ((Map<String, Map<String, Map>>)game_weeks).get(new Integer(pos).toString());
                GameWeek tempweek = mapper.readValue(JacksonPojoMapper.toJson(gameweek.get("game_week"), false) , GameWeek.class);
                if(result == null)
                {
                    result = new LinkedList<GameWeek>();
                }
                result.add(tempweek);
                pos++;
            }
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(GameService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
     
    }
    public List<GameWeek> retrieveHistoricalGameWeeks()
    {
        
        List<GameWeek> result = new LinkedList<GameWeek>();
        class SeasonKey
        {
            String year;
            String key;

            public SeasonKey(String year, String key) {
                this.year = year;
                this.key = key;
            }
            
        }
        LinkedList<SeasonKey> seasonKeyList = new LinkedList<SeasonKey>();
        seasonKeyList.add(new SeasonKey("2009", "222"));
        seasonKeyList.add(new SeasonKey("2010", "242"));
        seasonKeyList.add(new SeasonKey("2011", "257"));
        seasonKeyList.add(new SeasonKey("2012", "273"));
        seasonKeyList.add(new SeasonKey("2013", "nfl"));
        for(SeasonKey sk : seasonKeyList)
        {
            ObjectMapper mapper = new ObjectMapper();
            Map<String,Object> userData;
            Map<String, List<Map<String, Object>>> params;
            String response2 = conn.requestData("http://fantasysports.yahooapis.com/fantasy/v2/game/"+sk.key+"/game_weeks?format=json", Verb.GET);
            try 
            {
                userData = mapper.readValue(response2, Map.class);
                params = (Map<String, List<Map<String, Object>>>)userData.get("fantasy_content");
                Object game_weeks = ((Map<String, List<Map<String, Object>>>)params).get("game").get(1).get("game_weeks");
                int count = ((Integer)((Map<String, Object> )game_weeks).get("count"));
                int pos = 0;
                while (pos<count)
                {
                    Map gameweek = ((Map<String, Map<String, Map>>)game_weeks).get(new Integer(pos).toString());
                    GameWeek tempweek = mapper.readValue(JacksonPojoMapper.toJson(gameweek.get("game_week"), false) , GameWeek.class);
                    tempweek.setYear(sk.year);
                    result.add(tempweek);
                    pos++;
                }
            } 
            catch (IOException ex) 
            {
                Logger.getLogger(GameService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
     
    }
    
    public List<YahooLeague> getUserLeagues(String gameKey)
    {
            ObjectMapper mapper = new ObjectMapper();
            Map<String,Object> userData;
            Map<String,Object> results;
            List<Map<String, Object>> leaugeList;
            Map<String,Object> query;
            List<YahooLeague>  leagueListResults = new LinkedList<YahooLeague>();
            String ql = "select * from fantasysports.leagues where game_key = '"+ gameKey+"' and use_login=1";
            String response = yqlUitl.queryYQL(ql);
               try
               {
                   userData = mapper.readValue(response, Map.class);
                   query = (Map<String, Object>)userData.get("query"); // query details
                   results = (Map<String, Object>)query.get("results"); //result details
                   leaugeList = (List<Map<String, Object>>)results.get("league"); //result details
                   for (Map map : leaugeList)
                   {
                       YahooLeague tempLeauge = mapper.readValue(JacksonPojoMapper.toJson(map, false) , YahooLeague.class);
                       leagueListResults.add(tempLeauge);
                   }


               }
               catch(Exception e)
               {
                    Logger.getLogger(TeamService.class.getName()).log(Level.SEVERE, null, e);
               }
               return leagueListResults;
            
    }
    
    public List<YahooLeague> getUserLeagues()
    {
            ObjectMapper mapper = new ObjectMapper();
            Map<String,Object> userData;
            Map<String,Object> results;
            List<Map<String, Object>> leaugeList;
            Map<String,Object> query;
            List<YahooLeague>  leagueListResults = new LinkedList<YahooLeague>();
           
            List<String> yqlList = new LinkedList<String>();
            String yql = "select * from fantasysports.leagues where game_key = 'nfl' and use_login=1";
            yqlList.add(yql);
            yql = "select * from fantasysports.leagues where game_key = '273' and use_login=1";
            yqlList.add(yql);
            yql = "select * from fantasysports.leagues where game_key = '257' and use_login=1";
            yqlList.add(yql);
            yql = "select * from fantasysports.leagues where game_key = '242' and use_login=1";
            yqlList.add(yql);
            yql = "select * from fantasysports.leagues where game_key = '222' and use_login=1";
            yqlList.add(yql);
            yql = "select * from fantasysports.leagues where game_key = '199' and use_login=1";
            yqlList.add(yql);
            for(String ql : yqlList)
            {
                String response = yqlUitl.queryYQL(ql);
                try
                {
                    userData = mapper.readValue(response, Map.class);
                    query = (Map<String, Object>)userData.get("query"); // query details
                    results = (Map<String, Object>)query.get("results"); //result details
                    leaugeList = (List<Map<String, Object>>)results.get("league"); //result details
                    for (Map map : leaugeList)
                    {
                        YahooLeague tempLeauge = mapper.readValue(JacksonPojoMapper.toJson(map, false) , YahooLeague.class);
                        leagueListResults.add(tempLeauge);
                    }


                }
                catch(Exception e)
                {
                     Logger.getLogger(TeamService.class.getName()).log(Level.SEVERE, null, e);
                }
            }
            return leagueListResults;
    }
    
     public YahooLeague getLeague (String leagueid)
    {
            ObjectMapper mapper = new ObjectMapper();
            Map<String,Object> userData;
            Map<String,Object> results;
            List<Map<String, Object>> leaugeList;
            Map<String,Object> query;
            YahooLeague  leagueListResults = new YahooLeague();
            String yql = "select * from fantasysports.leagues where league_key='"+leagueid+"'";
            String response = yqlUitl.queryYQL(yql);
            try
            {
                userData = mapper.readValue(response, Map.class);
                query = (Map<String, Object>)userData.get("query"); // query details
                results = (Map<String, Object>)query.get("results"); //result details
                Map map = (Map<String, Object>)results.get("league"); //result details
                YahooLeague tempLeauge = mapper.readValue(JacksonPojoMapper.toJson(map, false) , YahooLeague.class);
                leagueListResults = tempLeauge;

                
               
            }
            catch(Exception e)
            {
                 Logger.getLogger(TeamService.class.getName()).log(Level.SEVERE, null, e);
            }
             
             return leagueListResults;
    }
    public YahooLeagueSettings getLeagueSettings (String leagueid)
    {
            ObjectMapper mapper = new ObjectMapper();
            Map<String,Object> userData;
            Map<String,Object> results;
            List<Map<String, Object>> leaugeList;
            Map<String,Object> query;
            YahooLeagueSettings  leagueListResults = new YahooLeagueSettings();
            String yql = "select * from fantasysports.leagues.settings where league_key='"+leagueid+"'";
            String response = yqlUitl.queryYQL(yql);
            try
            {
                userData = mapper.readValue(response, Map.class);
                query = (Map<String, Object>)userData.get("query"); // query details
                results = (Map<String, Object>)query.get("results"); //result details
                Map leaugeMap = (Map<String, Object>)results.get("league"); //result details
                Map resultMap = (Map<String, Object>)leaugeMap.get("settings"); //result details
                YahooLeagueSettings tempLeauge = mapper.readValue(JacksonPojoMapper.toJson(resultMap, false) , YahooLeagueSettings.class);
                leagueListResults = tempLeauge;

                
               
            }
            catch(Exception e)
            {
                 Logger.getLogger(TeamService.class.getName()).log(Level.SEVERE, null, e);
            }
             
             return leagueListResults;
    }
    
    public DraftResults getDraftResults (String leagueid)
    {
            ObjectMapper mapper = new ObjectMapper();
            Map<String,Object> userData;
            Map<String,Object> results;
            List<Map<String, Object>> draftList;
            Map<String,Object> query;
            DraftResults  leagueListResults = new DraftResults();
            String yql = "select * from fantasysports.draftresults where league_key='"+leagueid+"'";
            String response = yqlUitl.queryYQL(yql);
            try
            {
                userData = mapper.readValue(response, Map.class);
                query = (Map<String, Object>)userData.get("query"); // query details
                results = (Map<String, Object>)query.get("results"); //result details
                Map leauge = (Map<String, Object>)results.get("league"); //result details
                Map map = (Map<String, Object>)leauge.get("draft_results"); //result details
                DraftResults tempLeauge = mapper.readValue(JacksonPojoMapper.toJson(map, false) , DraftResults.class);
                leagueListResults = tempLeauge;

                
               
            }
            catch(Exception e)
            {
                 Logger.getLogger(TeamService.class.getName()).log(Level.SEVERE, null, e);
            }
             
             return leagueListResults;
    }
    
    public List<YahooLeague> getUserSavedLeagues()
    {
       return savedYahooLeagueDAOImpl.getAllSavedYahooLeagues();
    }
     
    public void saveUserSavedLeagues(List<YahooLeague> ylList)
    {
        savedYahooLeagueDAOImpl.saveSavedYahooLeague(ylList);
    }
    
}
