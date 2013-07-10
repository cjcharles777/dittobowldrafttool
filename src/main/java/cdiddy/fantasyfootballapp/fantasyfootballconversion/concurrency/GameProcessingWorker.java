/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cdiddy.fantasyfootballapp.fantasyfootballconversion.concurrency;

import cdiddy.fantasyfootballapp.fantasyfootballconversion.objects.DefenseStats;
import cdiddy.fantasyfootballapp.fantasyfootballconversion.objects.Game;
import cdiddy.fantasyfootballapp.fantasyfootballconversion.objects.Team;
import cdiddy.fantasyfootballapp.fantasyfootballconversion.objects.TeamStats;
import cdiddy.objects.Stat;
import cdiddy.objects.constants.YahooStatConstants;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
        Team t = game.getAway();
        processTeam(t);
        Stat s = new Stat();
        s.setStat_id(""+YahooStatConstants.DefYdsAllow);
    }
    
    private void processTeam(Team t)
    {
        String abbr = t.getAbbr();
        TeamStats ts = t.getStats();
    }
 
    private void processTeamStats(TeamStats ts)
    {
        Map<String, List<Stat>> result = new HashMap<String, List<Stat>>();
        
        result.putAll(processDefensiveStatsMap(ts.getDefense()));
        
    }
    
    private  Map<String, List<Stat>> processDefensiveStatsMap (Map<String, DefenseStats> defStatsMap )
    {
        Map<String, List<Stat>> result = new HashMap<String, List<Stat>>();
        
       for (Map.Entry<String, DefenseStats> entry : defStatsMap.entrySet())
       {
           DefenseStats tempDefStats = entry.getValue();
           List<Stat> tempYahooStats = new LinkedList<Stat>();
           tempYahooStats.add(createStat(YahooStatConstants.FumForce, tempDefStats.getFfum()));
           tempYahooStats.add(createStat(YahooStatConstants.TackSolo, tempDefStats.getTkl()));
           tempYahooStats.add(createStat(YahooStatConstants.TackAst, tempDefStats.getAst()));
           tempYahooStats.add(createStat(YahooStatConstants.SackDEF, tempDefStats.getSk()));
           tempYahooStats.add(createStat(YahooStatConstants.IntDEF, tempDefStats.getIntr()));
           result.put(entry.getKey(), tempYahooStats);
           
       }
       return result;
    }

    private Stat createStat(int stat_id, String value)
    {
        Stat temp = new Stat();
        temp.setStat_id(""+stat_id);
        temp.setValue(Double.valueOf(value));
        return temp;
    }
       

}
