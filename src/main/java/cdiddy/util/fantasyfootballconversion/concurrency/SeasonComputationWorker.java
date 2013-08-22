/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cdiddy.util.fantasyfootballconversion.concurrency;

import cdiddy.dao.SeasonStatsDAO;
import cdiddy.dao.StatDAO;
import cdiddy.objects.Player;
import cdiddy.objects.util.ConversionServiceUtil;

/**
 *
 * @author Cedric
 */
public class SeasonComputationWorker implements Runnable
{
    private Player player;
    private StatDAO statDAO;
    private SeasonStatsDAO seasonStatsDAO;

    public SeasonComputationWorker(Player player, ConversionServiceUtil csu) 
    {
        this.player = player;
        this.statDAO = csu.getStatDAO();
        this.seasonStatsDAO = csu.getSeasonStatsDAO();
    }
    
    

    public void run() 
    {
        //List
    }

    
}
