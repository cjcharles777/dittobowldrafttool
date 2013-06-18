/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cdiddy.fantasyfootballapp.fantasyfootballconversion.concurrency;

import cdiddy.fantasyfootballapp.fantasyfootballconversion.objects.Game;
import cdiddy.objects.Stat;
import cdiddy.objects.constants.YahooStatConstants;

/**
 *
 * @author cedric
 */
public class GameProcessingWorker implements Runnable
{
    private Game game;

    public GameProcessingWorker(Game game) 
    {
        this.game = game;
    }

    
    public void run() 
    {
        Stat s = new Stat();
        s.setTable_stat_id(YahooStatConstants.DefYdsAllow);
    }
 
}
