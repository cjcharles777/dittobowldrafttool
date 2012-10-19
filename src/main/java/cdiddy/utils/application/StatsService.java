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
import cdiddy.objects.dao.PlayersDAO;
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
import org.apache.commons.httpclient.util.URIUtil;
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
    YQLQueryUtil yqlUitl ;
    @Autowired
    StatCategoryDAO statCategoryDAOImpl;
    @Autowired
    PlayersDAO playersDAOImpl;
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
        
            
            SeasonStat result = new SeasonStat();
            Map<String,Object> userData;
            Map<String,Object> params;
            ObjectMapper mapper = new ObjectMapper();
            HashMap<String,PositionType> posTypeMap = new HashMap<String, PositionType>();
            String request = new String();
            request = "select * from fantasysports.players.stats where league_key='273.l.8899' and player_key = '"+p.getPlayer_key()+"' and stats_type='season'";
   
                String response = yqlUitl.queryYQL(request);
                 
            try 
            {
         
                    
                userData = mapper.readValue(response, Map.class);
                params = (Map<String, Object>)userData.get("query");
                Map <String,Object> testies = (Map <String,Object>)params.get("results");
                List<Object> playerStatObj= (List<Object>) testies.get("player");
               
                    
                
                for (Object obj : playerStatObj)
                {
                   
                    SeasonStat ss = new SeasonStat();
                    List<Map> weeklyStats;
                    Map<String,Object> temp =(Map<String,Object>)obj;
                    
                    Map<String, Object> playerstats = (Map<String,Object>) temp.get("player_stats");
                    int playerid =  Integer.parseInt((String)temp.get("player_id"));
                    
                    weeklyStats = ((Map<String, List<Map>>) playerstats.get("stats")).get("stat");
                    ss.setSeason((String)playerstats.get("season"));
                    ArrayList<Stat> statList = new ArrayList<Stat>();
                    for(Map<String,Object> stat : weeklyStats)
                    {

                        Stat tempStat = mapper.readValue(JacksonPojoMapper.toJson(stat, false) , Stat.class);


                        statList.add(tempStat);
                    }
                    ss.setStats(statList);
                    result = ss;
                }



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
                String player_key = "\'" + p.getPlayer_key()+"\'";
                playersList.add(player_key);
               
            }
            String finalPlayerKey = StringUtils.join(playersList, ",");
            String[] requests = new String[2];
           
            requests[0] = "select * from fantasysports.players.stats where league_key='273.l.8899' and player_key in ("+finalPlayerKey+") and stats_type='season' and stats_season='2011'";
            requests[1] = "select * from fantasysports.players.stats where league_key='273.l.8899' and player_key in ("+finalPlayerKey+") and stats_type='season'";
            for (String request : requests)
            {    
                String response = yqlUitl.queryYQL(request);
                 
                try 
                {
         
                    
                userData = mapper.readValue(response, Map.class);
                params = (Map<String, Object>)userData.get("query");
                Map <String,Object> testies = (Map <String,Object>)params.get("results");
                List<Object> playerStatObj= (List<Object>) testies.get("player");
               
                    
                
                for (Object obj : playerStatObj)
                {
                   
                    SeasonStat ss = new SeasonStat();
                    List<Map> weeklyStats;
                    Map<String,Object> temp =(Map<String,Object>)obj;
                    
                    Map<String, Object> playerstats = (Map<String,Object>) temp.get("player_stats");
                    int playerid =  Integer.parseInt((String)temp.get("player_id"));
                    
                    weeklyStats = ((Map<String, List<Map>>) playerstats.get("stats")).get("stat");
                    ss.setSeason((String)playerstats.get("season"));
                    ArrayList<Stat> statList = new ArrayList<Stat>();
                    for(Map<String,Object> stat : weeklyStats)
                    {

                        Stat tempStat = mapper.readValue(JacksonPojoMapper.toJson(stat, false) , Stat.class);


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
                String player_key = "\'" + p.getPlayer_key()+"\'";
                playersList.add(player_key);
               
            }
            String finalPlayerKey = StringUtils.join(playersList, ",");
            String[] requests = new String[1];
           
            requests[0] = "select * from fantasysports.players.stats where league_key='273.l.8899' and player_key in ("+finalPlayerKey+") and stats_type='week' and stats_week="+week;
            for (String request : requests)
            {    
                String response = yqlUitl.queryYQL(request);
                 
                try 
                {
         
                    
                userData = mapper.readValue(response, Map.class);
                params = (Map<String, Object>)userData.get("query");
               Map <String,Object> testies = (Map <String,Object>)params.get("results");
                List<Object> playerStatObj= (List<Object>) testies.get("player");
               
                    
                
                for (Object obj : playerStatObj)
                {
                   
                    WeeklyStat ss = new WeeklyStat();
                    List<Map> weeklyStats;
                    Map<String,Object> temp =(Map<String,Object>)obj;
                    
                    Map<String, Object> playerstats = (Map<String,Object>) temp.get("player_stats");
                    int playerid =  Integer.parseInt((String)temp.get("player_id"));
                    
                    weeklyStats = ((Map<String, List<Map>>) playerstats.get("stats")).get("stat");
                    ss.setWeek((String)playerstats.get("week"));
                    ArrayList<Stat> statList = new ArrayList<Stat>();
                    for(Map<String,Object> stat : weeklyStats)
                    {

                        Stat tempStat = mapper.readValue(JacksonPojoMapper.toJson(stat, false) , Stat.class);


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
