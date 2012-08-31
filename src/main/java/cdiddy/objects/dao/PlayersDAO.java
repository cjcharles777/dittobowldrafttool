/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cdiddy.objects.dao;

import cdiddy.objects.Player;
import java.util.List;

/**
 *
 * @author cedric
 */
public interface PlayersDAO 
{
    public void savePlayer(Player player);
    public List<Player> getAllPlayers(Player player);
    public Player getPlayerById(String playerId);
    public void deletePlayer(Player player);
}
