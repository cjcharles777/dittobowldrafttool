/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cdiddy.utils.application;


import cdiddy.objects.ByeWeek;
import cdiddy.objects.Name;
import cdiddy.objects.Player;
import cdiddy.objects.PlayerPic;
import cdiddy.objects.Position;
import cdiddy.objects.SeasonStat;
import cdiddy.objects.Stat;
import cdiddy.objects.WeeklyStat;
import cdiddy.objects.dao.PlayersDAO;
import cdiddy.objects.dao.SeasonStatsDAO;
import cdiddy.objects.dao.StatDAO;
import cdiddy.objects.dao.WeeklyStatsDAO;
import cdiddy.utils.system.JacksonPojoMapper;
import cdiddy.utils.system.OAuthConnection;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.scribe.model.Verb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author DMDD
 */
@Repository("playerUtil")
public class PlayerService 
{

    @Autowired
    OAuthConnection conn;
    
    @Autowired
    private StatsService statsService;
    @Autowired
    private StatDAO statDAOImpl;
    @Autowired
    private SeasonStatsDAO seasonStatsDAOImpl;
    @Autowired
    private WeeklyStatsDAO weeklyStatsDAOImpl;
    @Autowired
    private PlayersDAO playersDAOImpl;
    @Autowired
    private YQLQueryUtil yqlUitl ;
    
    //Yahoo grabs
    
    public void loadPlayers()
    {
        ObjectMapper mapper = new ObjectMapper();
        Map<String,Object> userData;
        Map<String,Object> results;
        Map<String,Object> query;
        ArrayList league;
        List<Player> playerObjList;
        List<Player> playerSaveList = new LinkedList<Player>();
        boolean morePlayers = true;
        int start = 0; 
        while (morePlayers)  
        {   
            String yql = "select * from fantasysports.players where game_key='nfl' and start="+start;
            String response = yqlUitl.queryYQL(yql);
            
            try 
            {
                userData = mapper.readValue(response, Map.class);
                results = (Map<String, Object>)userData.get("results"); //result details
                query = (Map<String, Object>)userData.get("query"); // query details
                int count = Integer.parseInt((String)query.get("count"));
                List<Map> playersList = (List<Map>) results.get("player");
                playerObjList = new LinkedList<Player>();
                for(Map temp : playersList)
                {
                    playerObjList.add(mapper.readValue(JacksonPojoMapper.toJson(temp, false) , Player.class));
                }
                Map<Integer, List<SeasonStat>> seasonStatmap = statsService.retrieveSeasonStats(playerObjList);
                playerObjList = connectStatsToPlayer(seasonStatmap, playerObjList);       
                Map<Integer, List<WeeklyStat>> statmap = statsService.retrieveWeeklyStats(playerObjList, 1);
                playerObjList = connectWeeklyStatsToPlayer(statmap, playerObjList);
                playerSaveList.addAll(playerObjList);
                start+=count;
                
                if(count < 25)
                {
                    morePlayers = false;
                }
 
                    
                    storePlayersToDatabase(playerSaveList);
                    playerSaveList = new LinkedList<Player>(); 
                   // Thread.sleep(2000);

            } catch (Exception ex) 
            {
                Logger.getLogger(PlayerService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public String getPlayerStats(Player p)
    {
        
        String player_key = p.getPlayer_id();
        String response = conn.requestData( "http://fantasysports.yahooapis.com/fantasy/v2/player/"+player_key+"/stats?format=json", Verb.GET);
    
        return response;
    }
    
        public String getStatsCategories()
    {
        
       // String player_key = "nfl.p." + p.getYahooId();
        String response = conn.requestData( "http://fantasysports.yahooapis.com/fantasy/v2/game/nfl/stat_categories?format=json", Verb.GET);
    
        return response;
    }
      
    // Helper Methods
        
      private List<Player> connectStatsToPlayer(Map<Integer, List<SeasonStat>> statmap, List<Player> playerObjList) 
    {
        List<Player> result = new LinkedList<Player>();
        for(Player p : playerObjList)
        {
                   
            p.setSeasonStats(statmap.get(Integer.parseInt(p.getPlayer_id())));
            result.add(p);
        
        }
        return result;
    }

    private List<Player> connectWeeklyStatsToPlayer(Map<Integer, List<WeeklyStat>> statmap, List<Player> playerObjList) 
    {
        
        List<Player> result = new LinkedList<Player>();
        for(Player p : playerObjList)
        {
                   
            p.setWeeklyStats(statmap.get(Integer.parseInt(p.getPlayer_id())));
            result.add(p);
        
        }
        return result;
    }
    
     public void yahooWeeklyStatsLoad(int week)
    {
        List<Player> allPlayers = playersDAOImpl.getAllPlayers();
        List<Player> playersToGetStats = new LinkedList<Player>();
        int i = 0;
        for(Player p : allPlayers)
        {
            playersToGetStats.add(p);
            i++;
            if(i%25 == 0)
            {
                Map<Integer, List<WeeklyStat>> weeklyStats = statsService.retrieveWeeklyStats(playersToGetStats,week);
                connectWeeklyStatsToPlayer(weeklyStats, playersToGetStats);
                playersToGetStats = new LinkedList<Player>();
                
            }
                  
        }
        storePlayersToDatabase(allPlayers);
    }

    
    //Database Updates
    
    
    public void storePlayersToDatabase(List<Player> playerList)
    {
        LinkedList<SeasonStat> listSS = new LinkedList<SeasonStat>();
        LinkedList<WeeklyStat> listWS = new LinkedList<WeeklyStat>();
        LinkedList<Stat> listStat = new LinkedList<Stat>();
        List<Name> listNames = new LinkedList<Name>();
        List<ByeWeek> listByeWeek = new LinkedList<ByeWeek>();
        List<PlayerPic> listPlayerPic = new LinkedList<PlayerPic>();
        List<Position> listAvailPositions = new LinkedList<Position>();
           
        for(Player p : playerList)
        {
            List<SeasonStat> tempSSList = p.getSeasonStats();
            List<WeeklyStat> tempWSList = p.getWeeklyStats();
            listSS.addAll(tempSSList);
            for(SeasonStat ss : tempSSList)
            {
               listStat.addAll(ss.getStats());
            }
            listWS.addAll(tempWSList);
            for(WeeklyStat ws : tempWSList)
            {
               listStat.addAll(ws.getStats());
            }
            
            listNames.add(p.getName());
            listByeWeek.add(p.getBye_weeks());
            listAvailPositions.addAll(p.getEligible_positions());
            listPlayerPic.add(p.getHeadshot());
        }
        statDAOImpl.saveStats(listStat);
        seasonStatsDAOImpl.saveSeasonStats(listSS);
        weeklyStatsDAOImpl.saveWeeklyStats(listWS);
        playersDAOImpl.savePlayers(playerList);
    }
        
    public List<Player> retrivePlayers() 
    {
         return playersDAOImpl.getAllPlayers();
    }
     public Player retrivePlayer(int playerid) 
    {
         return playersDAOImpl.getPlayerbyYahooId(playerid);
    }
    public List<Player> retrivePlayers(int firstResult, int maxResults) 
    {
        return playersDAOImpl.getPlayers(firstResult, maxResults);
    }
    
    public void primePlayersDatabase() 
    {
         playersDAOImpl.clearPlayers();
    }
    

}
