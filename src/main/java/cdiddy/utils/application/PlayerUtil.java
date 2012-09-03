/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cdiddy.utils.application;

import cdiddy.objects.Player;
import cdiddy.objects.dao.OAuthDAO;
import cdiddy.objects.dao.PlayersDAO;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author DMDD
 */
@Repository("playerUtil")
public class PlayerUtil 
{

 
    @Autowired
    private PlayersDAO playersDAOImpl;
    
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
                result.setUniformNumber(Integer.parseInt((String)temp.get("uniform_number")));
                }
                else if(temp.get("display_position")!= null)
                {
                result.setDisplayPosition(((String) temp.get("display_position")));
                }
            }
            
        }
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
    
    public void storePlayersToDatabase(List<Player> playerList)
    {
        playersDAOImpl.savePlayers(playerList);
    }
        
    public List<Player> retrivePlayers() 
    {
         return playersDAOImpl.getAllPlayers();
    }
}
