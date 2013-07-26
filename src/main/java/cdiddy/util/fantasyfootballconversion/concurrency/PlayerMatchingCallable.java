/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cdiddy.util.fantasyfootballconversion.concurrency;

import cdiddy.dao.PlayersDAO;
import cdiddy.util.fantasyfootballconversion.objects.NFLPlayer;
import cdiddy.objects.Name;
import cdiddy.objects.Player;
import java.util.List;
import java.util.concurrent.Callable;

/**
 *
 * @author DMDD
 */

public class PlayerMatchingCallable implements Callable
{

    private NFLPlayer player;
    private PlayersDAO playersDAO;

    public PlayerMatchingCallable() {
    }

    public PlayerMatchingCallable(NFLPlayer player, PlayersDAO playersDAO) {
        this.player = player;
        this.playersDAO = playersDAO;
    }

    
    
    public NFLPlayer getPlayer() {
        return player;
    }

    public void setPlayer(NFLPlayer player) {
        this.player = player;
    }
    
    public Player call() throws Exception 
    {
        Player tempPlayer = new Player();
        Name tempPlayerName = new Name();
        tempPlayerName.setFull(player.getName());
        tempPlayer.setEditorial_team_abbr(player.getTeam());
        tempPlayer.setName(tempPlayerName);
        List<Player> result = playersDAO.getPlayers(tempPlayer); 
        System.out.println(player.getProfile_url());
        System.out.println(result.size());
        if(result.size() > 0)
        {
            return result.get(0);
        }
        return null;
    }
    
}
