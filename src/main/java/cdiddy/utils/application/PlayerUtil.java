/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cdiddy.utils.application;

import cdiddy.objects.Player;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 *
 * @author DMDD
 */
public class PlayerUtil 
{
    public static Player createPlayer(ArrayList<LinkedHashMap> lhm)
    {
        Player result = new Player();
        result.setId((Integer) lhm.get(0).get("player_id"));
        
        return result;
    }
}
