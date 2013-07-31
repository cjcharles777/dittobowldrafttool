/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cdiddy.objects.util;

import cdiddy.dao.GameWeekDAO;
import cdiddy.dao.PlayersDAO;
import cdiddy.dao.SeasonStatsDAO;
import cdiddy.dao.StatDAO;
import cdiddy.dao.WeeklyStatsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Cedric
 */

@Repository("conversionServiceUtil")
public class ConversionServiceUtil 
{
    @Autowired
    private PlayersDAO playersDAO;
    @Autowired
    private GameWeekDAO gameWeekDAO;
    @Autowired
    private StatDAO statDAO;
    @Autowired
    private WeeklyStatsDAO weeklyStatsDAO;
    @Autowired
    private SeasonStatsDAO seasonStatsDAO;

    public PlayersDAO getPlayersDAO() {
        return playersDAO;
    }

    public GameWeekDAO getGameWeekDAO() {
        return gameWeekDAO;
    }

    public StatDAO getStatDAO() {
        return statDAO;
    }

    public WeeklyStatsDAO getWeeklyStatsDAO() {
        return weeklyStatsDAO;
    }

    public SeasonStatsDAO getSeasonStatsDAO() {
        return seasonStatsDAO;
    }
    
    
}
