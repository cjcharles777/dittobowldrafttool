/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cdiddy.utils.application;


import cdiddy.objects.Player;
import cdiddy.objects.SeasonStat;
import cdiddy.objects.Stat;
import cdiddy.objects.WeeklyStat;
import cdiddy.objects.dao.PlayersDAO;
import cdiddy.objects.dao.SeasonStatsDAO;
import cdiddy.objects.dao.StatDAO;
import cdiddy.objects.dao.WeeklyStatsDAO;
import cdiddy.utils.system.OAuthConnection;
import java.io.IOException;
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
    
    //Yahoo grabs
    
    public void loadPlayers()
    {
        ObjectMapper mapper = new ObjectMapper();
        Map<String,Object> userData;
        Map<String,Object> params;
        ArrayList league;
        List<Player> playerObjList;
        List<Player> playerSaveList = new LinkedList<Player>();
        boolean morePlayers = true;
        int start = 0; 
        while (morePlayers)  
        {   
            
            String response2 = conn.requestData( "http://fantasysports.yahooapis.com/fantasy/v2/league/273.l.8899/players;count=25;start="+start+";?format=json", Verb.GET);
            try 
            {
                userData = mapper.readValue(response2, Map.class);
                params = (Map<String, Object>)userData.get("fantasy_content");
                league = (ArrayList)params.get("league");
                ArrayList<LinkedHashMap<String, List<Collection>>> playersList = new  ArrayList<LinkedHashMap<String, List<Collection>>>(((Map <String, LinkedHashMap>)league.get(1)).get("players").values());
                playersList.remove(playersList.size()-1);
                playerObjList = createPlayersFromList(playersList);
                Map<Integer, List<SeasonStat>> seasonStatmap = statsService.retrieveSeasonStats(playerObjList);
                playerObjList = connectStatsToPlayer(seasonStatmap, playerObjList);
                playerSaveList.addAll(playerObjList);
                Map<Integer, List<WeeklyStat>> statmap = statsService.retrieveWeeklyStats(playerObjList, 1);
                playerObjList = connectWeeklyStatsToPlayer(statmap, playerObjList);
                playerSaveList.addAll(playerObjList);
                start+=playerObjList.size();
                
                if(playerObjList.size() < 25)
                {
                    morePlayers = false;
                }
 
                    
                    storePlayersToDatabase(playerSaveList);
                    playerSaveList = new LinkedList<Player>();

            } catch (IOException ex) 
            {
                Logger.getLogger(PlayerService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public String getPlayerStats(Player p)
    {
        
        String player_key = "nfl.p." + p.getYahooId();
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
        
    public Player createPlayer(List<Object> lhm)
    {
        Player result = new Player();
        for(Object col : lhm)
        {
            if(col instanceof LinkedHashMap)
            {
                LinkedHashMap temp = (LinkedHashMap) col;
                if(temp.get("player_id")!= null)
                {
                result.setYahooId(Integer.parseInt((String)temp.get("player_id")));
                }
                else if(temp.get("name")!= null)
                {
                    result.setFirstName((String) ((LinkedHashMap)temp.get("name")).get("first"));
                    result.setLastName((String) ((LinkedHashMap)temp.get("name")).get("last"));
                }
                else if(temp.get("headshot")!= null)
                {
                result.setHeadshotHtml((String) ((LinkedHashMap)temp.get("headshot")).get("url"));
                }
                else if(temp.get("editorial_team_abbr")!= null)
                {
                result.setTeam((String) temp.get("editorial_team_abbr"));
                }
                else if(temp.get("uniform_number")!= null)
                {
                    if(!((temp.get("uniform_number")) instanceof String )
                            ||(((String)temp.get("uniform_number")).trim()).equals(""))
                    {
                        result.setUniformNumber(-1);
                    }
                    else
                    {
                        result.setUniformNumber(Integer.parseInt((String)temp.get("uniform_number")));
                    }
                
                }
                else if(temp.get("display_position")!= null)
                {
                result.setDisplayPosition(((String) temp.get("display_position")));
                }
            }
            
        }
        //List<SeasonStat> seasonStats = new ArrayList<SeasonStat>();
        //seasonStats.add(statsService.retrieveSeasonStats(result));
        //result.setSeasonStats(seasonStats);
        return result;
    }
    public List<Player> createPlayersFromList(List<LinkedHashMap<String, List<Collection>>> lhmList)
    {
        LinkedList<Player> playerList = new LinkedList<Player>();
        for(LinkedHashMap<String, List<Collection>> temp : lhmList)
        {
            playerList.add(createPlayer((List)temp.get("player").get(0)));
        }
        return playerList;
    }
    

    private List<Player> connectStatsToPlayer(Map<Integer, List<SeasonStat>> statmap, List<Player> playerObjList) 
    {
        List<Player> result = new LinkedList<Player>();
        for(Player p : playerObjList)
        {
                   
            p.setSeasonStats(statmap.get(p.getYahooId()));
            result.add(p);
        
        }
        return result;
    }

    private List<Player> connectWeeklyStatsToPlayer(Map<Integer, List<WeeklyStat>> statmap, List<Player> playerObjList) 
    {
        
        List<Player> result = new LinkedList<Player>();
        for(Player p : playerObjList)
        {
                   
            p.setWeeklyStats(statmap.get(p.getYahooId()));
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
