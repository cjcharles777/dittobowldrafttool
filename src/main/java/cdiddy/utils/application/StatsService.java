/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cdiddy.utils.application;

import cdiddy.objects.Player;
import cdiddy.objects.PositionType;
import cdiddy.objects.SeasonStat;
import cdiddy.objects.Stat;
import cdiddy.objects.StatCategory;
import cdiddy.objects.WeeklyStat;
import cdiddy.objects.dao.PositionTypeDAO;
import cdiddy.objects.dao.SeasonStatsDAO;
import cdiddy.objects.dao.StatCategoryDAO;
import cdiddy.objects.dao.StatDAO;
import cdiddy.utils.system.JacksonPojoMapper;
import cdiddy.utils.system.OAuthConnection;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang.StringUtils;
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
    @Autowired
    StatCategoryDAO statCategoryDAOImpl;
    @Autowired
    PositionTypeDAO positionTypeDAOImpl;
    @Autowired
    private StatDAO statDAOImpl;
    @Autowired
    private SeasonStatsDAO seasonStatsDAOImpl;   
        
    public List<StatCategory> retrieveStatCategories()
    {
            Map<String,Object> userData;
            Map<String,Object> params;
            ArrayList<Map> statsCats;
            List<StatCategory> result = new ArrayList<StatCategory>();
            ObjectMapper mapper = new ObjectMapper();
            HashMap<String,PositionType> posTypeMap = new HashMap<String, PositionType>();
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
                
                StatCategory tempSc = mapper.readValue(JacksonPojoMapper.toJson(statsCat.get("stat"), false) , StatCategory.class);
                for(PositionType posType : tempSc.getPosition_types())
                {
                    if(!posTypeMap.containsKey(posType.getPosition_type()))
                    {
                        posTypeMap.put(posType.getPosition_type(),posType);
                    }
                }
                
                result.add(tempSc);
            }
            System.out.println(statsCats.size());
            positionTypeDAOImpl.savePositionTypes(new ArrayList<PositionType>(posTypeMap.values()));
        } catch (IOException ex) {
            Logger.getLogger(StatsService.class.getName()).log(Level.SEVERE, null, ex);
        }
      

            
           // mapper.readValue(JacksonPojoMapper.toJson(trxObj, false), Positions.class);
        return result;
    }
    
        public SeasonStat retrieveSeasonStats(Player p)
    {
        
            
            Map<String,Object> userData;
            Map<String,Object> params;
            ArrayList<Map> seasonStats;
            Map<String, String> seasonInfo;
            SeasonStat result = new SeasonStat();
            ObjectMapper mapper = new ObjectMapper();
            HashMap<String,PositionType> posTypeMap = new HashMap<String, PositionType>();
            String player_key = "nfl.p." + p.getYahooId();
            String response = conn.requestData( "http://fantasysports.yahooapis.com/fantasy/v2/player/"+player_key+"/stats?format=json", Verb.GET);
        try 
        {
            userData = mapper.readValue(response, Map.class);
            params = (Map<String, Object>)userData.get("fantasy_content");
            Map testies = mapper.readValue(JacksonPojoMapper.toJson((((ArrayList<Map>)params.get("player")).get(1)), false) , Map.class);
            seasonInfo = ((Map<String, Map<String, Map<String, String>>>)testies).get("player_stats").get("0");
            seasonStats = ((Map<String, Map<String, ArrayList<Map>>>)testies).get("player_stats").get("stats");
            result.setSeason(seasonInfo.get("season"));
            ArrayList<Stat> statList = new ArrayList<Stat>();
            for(Map<String,Map> stat : seasonStats)
            {
                
                Stat tempStat = mapper.readValue(JacksonPojoMapper.toJson(stat.get("stat"), false) , Stat.class);
  
                
                statList.add(tempStat);
            }
            result.setStats(statList);
            System.out.println(seasonStats.size());
            //positionTypeDAOImpl.savePositionTypes(new ArrayList<PositionType>(posTypeMap.values()));
        } catch (Exception e) {
            Logger.getLogger(StatsService.class.getName()).log(Level.SEVERE, null, e);
        }
      

            
           // mapper.readValue(JacksonPojoMapper.toJson(trxObj, false), Positions.class);
        return result;
    }
    
    public Map<Integer, List<SeasonStat>> retrieveSeasonStats(List<Player> listP)
    {
        
            Map<Integer, List<SeasonStat>> result = new HashMap<Integer, List<SeasonStat>>();
            Map<String,Object> userData;
            Map<String,Object> params;
            ObjectMapper mapper = new ObjectMapper();
            HashMap<String,PositionType> posTypeMap = new HashMap<String, PositionType>();
            
            LinkedList<String> playersList = new LinkedList<String>();
            int i = 0;
            for (Player p : listP)
            {
                String player_key = "nfl.p." + p.getYahooId();
                playersList.add(player_key);
               
            }
            String finalPlayerKey = StringUtils.join(playersList, ",");
            String[] requests = new String[3];
            requests[0] = "http://fantasysports.yahooapis.com/fantasy/v2/players;player_keys="+finalPlayerKey+"/stats?format=json";
            requests[1] = "http://fantasysports.yahooapis.com/fantasy/v2/players;sort_type=season;sort_season=2011;player_keys="+finalPlayerKey+"/stats;type=season;season=2011?format=json";
            requests[2] = "http://fantasysports.yahooapis.com/fantasy/v2/players;sort_type=season;sort_season=2010;player_keys="+finalPlayerKey+"/stats;type=season;season=2010?format=json";
            
            for (String request : requests)
            {    
                 String response = conn.requestData(request, Verb.GET);
                try 
                {
                userData = mapper.readValue(response, Map.class);
                params = (Map<String, Object>)userData.get("fantasy_content");
                Map<String, Object> testies = mapper.readValue(JacksonPojoMapper.toJson(((Map<String,Map>)params.get("players")), false) , Map.class);
                int count = (Integer) testies.get("count");
                int pos = 0;
                while (pos<count)
                {
                    Map<String, String> seasonInfo;
                    SeasonStat ss = new SeasonStat();
                    List<Map> seasonStats;
                    Map<String,Object> temp =(Map<String,Object>)testies.get(pos+"");
                    Map<String, Object> playerstats = (Map<String, Object>)((List) temp.get("player")).get(1);
                    int playerid =  Integer.parseInt(((Map<String, String>)((List)((List) temp.get("player")).get(0)).get(1)).get("player_id"));
                    seasonInfo = ((Map<String,Map>) playerstats.get("player_stats")).get("0");
                    seasonStats = ((Map<String,List>) playerstats.get("player_stats")).get("stats");
                    ss.setSeason(seasonInfo.get("season"));
                    ArrayList<Stat> statList = new ArrayList<Stat>();
                    for(Map<String,Map> stat : seasonStats)
                    {

                        Stat tempStat = mapper.readValue(JacksonPojoMapper.toJson(stat.get("stat"), false) , Stat.class);


                        statList.add(tempStat);
                    }
                    ss.setStats(statList);
                    if(!result.containsKey(playerid))
                    {

                        result.put(playerid, new LinkedList<SeasonStat>());
                        result.get(playerid).add(ss);
                    }
                    else
                    {
                        result.get(playerid).add(ss);
                    }
                    pos++;
                }



                //positionTypeDAOImpl.savePositionTypes(new ArrayList<PositionType>(posTypeMap.values()));
            } catch (Exception e) {
                Logger.getLogger(StatsService.class.getName()).log(Level.SEVERE, null, e);
            }
        }

            
           // mapper.readValue(JacksonPojoMapper.toJson(trxObj, false), Positions.class);
        return result;
    }
        
    public Map<Integer, List<WeeklyStat>> retrieveWeeklyStats(List<Player> listP, int week)
    {
        
            Map<Integer, List<WeeklyStat>> result = new HashMap<Integer, List<WeeklyStat>>();
            Map<String,Object> userData;
            Map<String,Object> params;
            ObjectMapper mapper = new ObjectMapper();
            HashMap<String,PositionType> posTypeMap = new HashMap<String, PositionType>();
            
            LinkedList<String> playersList = new LinkedList<String>();
            int i = 0;
            for (Player p : listP)
            {
                String player_key = "nfl.p." + p.getYahooId();
                playersList.add(player_key);
               
            }
            String finalPlayerKey = StringUtils.join(playersList, ",");
            String[] requests = new String[1];
            requests[0] = "http://fantasysports.yahooapis.com/fantasy/v2/players;player_keys="+finalPlayerKey+"/stats;type=week;week="+week+"?format=json";
            
            for (String request : requests)
            {    
                 String response = conn.requestData(request, Verb.GET);
                try 
                {
                userData = mapper.readValue(response, Map.class);
                params = (Map<String, Object>)userData.get("fantasy_content");
                Map<String, Object> testies = mapper.readValue(JacksonPojoMapper.toJson(((Map<String,Map>)params.get("players")), false) , Map.class);
                int count = (Integer) testies.get("count");
                int pos = 0;
                while (pos<count)
                {
                    Map<String, String> weeklyInfo;
                    WeeklyStat ss = new WeeklyStat();
                    List<Map> weeklyStats;
                    Map<String,Object> temp =(Map<String,Object>)testies.get(pos+"");
                    Map<String, Object> playerstats = (Map<String, Object>)((List) temp.get("player")).get(1);
                    int playerid =  Integer.parseInt(((Map<String, String>)((List)((List) temp.get("player")).get(0)).get(1)).get("player_id"));
                    weeklyInfo = ((Map<String,Map>) playerstats.get("player_stats")).get("0");
                    weeklyStats = ((Map<String,List>) playerstats.get("player_stats")).get("stats");
                    ss.setWeek(weeklyInfo.get("week"));
                    ArrayList<Stat> statList = new ArrayList<Stat>();
                    for(Map<String,Map> stat : weeklyStats)
                    {

                        Stat tempStat = mapper.readValue(JacksonPojoMapper.toJson(stat.get("stat"), false) , Stat.class);


                        statList.add(tempStat);
                    }
                    ss.setStats(statList);
                    if(!result.containsKey(playerid))
                    {

                        result.put(playerid, new LinkedList<WeeklyStat>());
                        result.get(playerid).add(ss);
                    }
                    else
                    {
                        result.get(playerid).add(ss);
                    }
                    pos++;
                }



                //positionTypeDAOImpl.savePositionTypes(new ArrayList<PositionType>(posTypeMap.values()));
            } catch (Exception e) {
                Logger.getLogger(StatsService.class.getName()).log(Level.SEVERE, null, e);
            }
        }

            
           // mapper.readValue(JacksonPojoMapper.toJson(trxObj, false), Positions.class);
        return result;
    }
    public void loadStatCategories()
    {
        
        statCategoryDAOImpl.saveStatCategories(retrieveStatCategories());
    }
    
    public void primeStatCategories() 
    {
         statCategoryDAOImpl.clearStatCategory();
    }

    public void primeStats() {
       
        seasonStatsDAOImpl.clearSeasonStat();
       statDAOImpl.clearStats();
       
    }

    public StatCategory getStatCategory(int statCatId) 
    {
        return statCategoryDAOImpl.getStatCategoryById(statCatId);
    }
}
