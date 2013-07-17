/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cdiddy.fantasyfootballapp.fantasyfootballconversion.concurrency;

import cdiddy.fantasyfootballapp.fantasyfootballconversion.objects.DefenseStats;
import cdiddy.fantasyfootballapp.fantasyfootballconversion.objects.Game;
import cdiddy.fantasyfootballapp.fantasyfootballconversion.objects.PassingStats;
import cdiddy.fantasyfootballapp.fantasyfootballconversion.objects.ReceivingStats;
import cdiddy.fantasyfootballapp.fantasyfootballconversion.objects.RushingStats;
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
        result.putAll(processPassingStatsMap(ts.getPassing()));
        result.putAll(processRushingStatsMap(ts.getRushing()));
        result.putAll(processRecievingStatsMap(ts.getReceiving()));
        result.putAll(result);
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
           //result.put(entry.getKey(), tempYahooStats);
           
       }
       return result;
    }
    private  Map<String, List<Stat>> processPassingStatsMap (Map<String, PassingStats> pasStatsMap )
    {
        Map<String, List<Stat>> result = new HashMap<String, List<Stat>>();
        
       for (Map.Entry<String, PassingStats> entry : pasStatsMap.entrySet())
       {
           PassingStats tempPassStats = entry.getValue();
           List<Stat> tempYahooStats = new LinkedList<Stat>();
           tempYahooStats.add(createStat(YahooStatConstants.PassAtt, tempPassStats.getAtt()));
           tempYahooStats.add(createStat(YahooStatConstants.PassComp, tempPassStats.getCmp()));
           tempYahooStats.add(createStat(YahooStatConstants.QBInt, tempPassStats.getInts()));
           tempYahooStats.add(createStat(YahooStatConstants.PassTD, tempPassStats.getTds()));
           tempYahooStats.add(createStat(YahooStatConstants.PassYds, tempPassStats.getYds()));
           tempYahooStats.add(createStat(YahooStatConstants.TwoPT, tempPassStats.getTwoptm()));
           tempYahooStats.add(createStat(YahooStatConstants.TPA, tempPassStats.getTwopta()));
           tempYahooStats.add(createStat(YahooStatConstants.TPM, tempPassStats.getTwoptm()));
           result.put(entry.getKey(), tempYahooStats);
           
       }
       return result;
    }
    private  Map<String, List<Stat>> processRushingStatsMap (Map<String, RushingStats> rushStatsMap )
    {
        Map<String, List<Stat>> result = new HashMap<String, List<Stat>>();
        
       for (Map.Entry<String, RushingStats> entry : rushStatsMap.entrySet())
       {
           RushingStats tempRushStats = entry.getValue();
           List<Stat> tempYahooStats = new LinkedList<Stat>();
           tempYahooStats.add(createStat(YahooStatConstants.RushAtt, tempRushStats.getAtt()));
           tempYahooStats.add(createStat(YahooStatConstants.RushLongYds, tempRushStats.getLng()));
           tempYahooStats.add(createStat(YahooStatConstants.RushLongTD, tempRushStats.getLngtd()));
           tempYahooStats.add(createStat(YahooStatConstants.RushTD, tempRushStats.getTds()));
           tempYahooStats.add(createStat(YahooStatConstants.RushYds, tempRushStats.getYds()));
           tempYahooStats.add(createStat(YahooStatConstants.TwoPT, tempRushStats.getTwoptm()));
           tempYahooStats.add(createStat(YahooStatConstants.TPARushing, tempRushStats.getTwopta()));
           tempYahooStats.add(createStat(YahooStatConstants.TPMRushing, tempRushStats.getTwoptm()));
           result.put(entry.getKey(), tempYahooStats);
           
       }
       return result;
    }
    private  Map<String, List<Stat>> processRecievingStatsMap (Map<String, ReceivingStats> recStatsMap )
    {
        Map<String, List<Stat>> result = new HashMap<String, List<Stat>>();
        
       for (Map.Entry<String, ReceivingStats> entry : recStatsMap.entrySet())
       {
           ReceivingStats tempRecStats = entry.getValue();
           List<Stat> tempYahooStats = new LinkedList<Stat>();
           tempYahooStats.add(createStat(YahooStatConstants.RecLongYds, tempRecStats.getLng()));
           tempYahooStats.add(createStat(YahooStatConstants.RecLongTD, tempRecStats.getLngtd()));
           tempYahooStats.add(createStat(YahooStatConstants.RecTD, tempRecStats.getTds()));
           tempYahooStats.add(createStat(YahooStatConstants.RecYds, tempRecStats.getYds()));
           tempYahooStats.add(createStat(YahooStatConstants.TwoPT, tempRecStats.getTwoptm()));
           tempYahooStats.add(createStat(YahooStatConstants.TPAReceiving, tempRecStats.getTwopta()));
           tempYahooStats.add(createStat(YahooStatConstants.TPMReceiving, tempRecStats.getTwoptm()));
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
    private  Map<String, List<Stat>> combineStats(Map<String, List<Stat>> base,  Map<String, List<Stat>> addon)
    {
         Map<String, List<Stat>> result = base;
         for (Map.Entry<String, List<Stat>> entry : result.entrySet())
         {
             
         }
         return result;
    }
       

}
