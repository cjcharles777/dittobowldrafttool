/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cdiddy.dao;

import cdiddy.objects.GameWeek;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Cedric
 */
@Repository("gameWeekDAO")
public interface GameWeekDAO 
{
    public void saveGameWeek(GameWeek gw);
    public void saveGameWeek(List<GameWeek> listGW);
    public List<GameWeek> getAllGameWeek();
    public GameWeek getGameWeekById(int gwId);
    public void deleteGameWeek(GameWeek gw);
    public void clearGameWeek();
    public List<GameWeek> retrieveContainingGameWeek(Date d);
}
