/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cdiddy.objects;

import java.util.List;

/**
 *
 * @author cedric
 */
public class WeekRosterPlayers 
{
    private Integer count;
    private List<Player> player;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer size) {
        this.count = size;
    }

    public List<Player> getPlayer() {
        return player;
    }

    public void setPlayer(List<Player> player) {
        this.player = player;
    }
    
    
}
