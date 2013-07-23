/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cdiddy.fantasyfootballapp.fantasyfootballconversion.concurrency;

import cdiddy.dao.PlayersDAO;
import cdiddy.fantasyfootballapp.fantasyfootballconversion.objects.DefenseStats;
import cdiddy.fantasyfootballapp.fantasyfootballconversion.objects.FumblesStats;
import cdiddy.fantasyfootballapp.fantasyfootballconversion.objects.Game;
import cdiddy.fantasyfootballapp.fantasyfootballconversion.objects.KickingStats;
import cdiddy.fantasyfootballapp.fantasyfootballconversion.objects.PassingStats;
import cdiddy.fantasyfootballapp.fantasyfootballconversion.objects.PuntingStats;
import cdiddy.fantasyfootballapp.fantasyfootballconversion.objects.ReceivingStats;
import cdiddy.fantasyfootballapp.fantasyfootballconversion.objects.ReturnStats;
import cdiddy.fantasyfootballapp.fantasyfootballconversion.objects.RushingStats;
import cdiddy.fantasyfootballapp.fantasyfootballconversion.objects.Team;
import cdiddy.fantasyfootballapp.fantasyfootballconversion.objects.TeamStats;
import cdiddy.objects.Player;
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
    private PlayersDAO playersDAO;
    private Map<String, Player> playerMap;

    public GameProcessingWorker(Game game, PlayersDAO playersDAO, Map<String, Player> playerMap) 
    {
        this.game = game;
        this.playersDAO = playersDAO;
        this.playerMap = playerMap;
    }

    
    public void run() 
    {
        Team awayTeam = game.getAway();
        Team homeTeam = game.getHome();
        Map<String, List<Stat>> awayResults = processTeam(awayTeam);
        Map<String, List<Stat>> homeResults = processTeam(homeTeam);
        Map<String, List<Stat>> combinedResults = combineStats(awayResults, homeResults);
       for (Map.Entry<String, List<Stat>> entry : combinedResults.entrySet())
       {
           String nflplayerid = entry.getKey();
           if(playerMap.containsKey(nflplayerid))
           {
              Player nflPlayer = playerMap.get(nflplayerid);
              if(nflPlayer != null && nflPlayer.getName() != null)
              {
                System.out.println("Player : " + nflPlayer.getName().getFull());
              }
              else
              {
                  System.out.println("Player : " + nflplayerid +" does not have Name");
              }
              for(Stat s : entry.getValue())
              {
                  System.out.println("Stat id : " + s.getStat_id()+" Stat value : "+ s.getValue());
              }
              
           }
           
       }
        
    }
    
    private Map<String, List<Stat>> processTeam(Team t)
    {
        String abbr = t.getAbbr();
        //TeamStats ts = t.getStats();
        return processTeamStats(t.getStats());
    }
 
    private Map<String, List<Stat>>  processTeamStats(TeamStats ts)
    {
        Map<String, List<Stat>> result = new HashMap<String, List<Stat>>();
        
        result.putAll(processDefensiveStatsMap(ts.getDefense()));
        if(ts.getPassing() != null)
        {
            result = combineStats(result,processPassingStatsMap(ts.getPassing()));
        }
         if(ts.getRushing() != null)
         {
            result = combineStats(result,processRushingStatsMap(ts.getRushing()));
         }
        if(ts.getReceiving() != null)
        {
            result = combineStats(result,processRecievingStatsMap(ts.getReceiving()));
        }
        if(ts.getFumbles() != null)
        {
            result = combineStats(result,processFumblesStatsMap(ts.getFumbles()));
        }
        if(ts.getKicking() != null)
        {
            result = combineStats(result,processKickingMap(ts.getKicking()));
        }
        if(ts.getKickret() != null)
        {
            result = combineStats(result,processKickRetMap(ts.getKickret()));
        }
        if(ts.getPunting() != null)
        {        
            result = combineStats(result,processPuntingingMap(ts.getPunting()));
        }
        if(ts.getPuntret() != null)
        {
            result = combineStats(result,processPuntRetMap(ts.getPuntret()));
        }
        
        
        return result;
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
    
    private Map<String, List<Stat>> processFumblesStatsMap(Map<String, FumblesStats> fumblesStatMap) 
    {
         Map<String, List<Stat>> result = new HashMap<String, List<Stat>>();
        
       for (Map.Entry<String, FumblesStats> entry : fumblesStatMap.entrySet())
       {
           FumblesStats tempRecStats = entry.getValue();
           List<Stat> tempYahooStats = new LinkedList<Stat>();
           tempYahooStats.add(createStat(YahooStatConstants.FumLost, tempRecStats.getLost()));
           tempYahooStats.add(createStat(YahooStatConstants.FumRecDEF, tempRecStats.getRcv()));
           tempYahooStats.add(createStat(YahooStatConstants.Fum, tempRecStats.getTot()));
           tempYahooStats.add(createStat(YahooStatConstants.FumTrcv, tempRecStats.getTrcv()));
           tempYahooStats.add(createStat(YahooStatConstants.FumYds, tempRecStats.getYds()));

           result.put(entry.getKey(), tempYahooStats);
           
       }
       return result;
    }
       
    private Map<String, List<Stat>> processKickingMap(Map<String, KickingStats> kickStatMap) 
    {
         Map<String, List<Stat>> result = new HashMap<String, List<Stat>>();
        
       for (Map.Entry<String, KickingStats> entry : kickStatMap.entrySet())
       {
           KickingStats tempStats = entry.getValue();
           List<Stat> tempYahooStats = new LinkedList<Stat>();
           tempYahooStats.add(createStat(YahooStatConstants.FGA, tempStats.getFga()));
           tempYahooStats.add(createStat(YahooStatConstants.FGM, tempStats.getFgm()));
           tempYahooStats.add(createStat(YahooStatConstants.FGYds, tempStats.getFgyds()));
           tempYahooStats.add(createStat(YahooStatConstants.TotpFg, tempStats.getTotpfg()));
           tempYahooStats.add(createStat(YahooStatConstants.XPA, tempStats.getXpa()));
           tempYahooStats.add(createStat(YahooStatConstants.XPB, tempStats.getXpb()));
           tempYahooStats.add(createStat(YahooStatConstants.XPMade, tempStats.getXpmade()));
           tempYahooStats.add(createStat(YahooStatConstants.XPMissed, tempStats.getXpmissed()));
           tempYahooStats.add(createStat(YahooStatConstants.XPTot, tempStats.getXptot()));

           result.put(entry.getKey(), tempYahooStats);
           
       }
       return result;
    }

    private Map<String, List<Stat>> processKickRetMap(Map<String, ReturnStats> kickRetMap) 
    {
         Map<String, List<Stat>> result = new HashMap<String, List<Stat>>();
         for (Map.Entry<String, ReturnStats> entry : kickRetMap.entrySet())
       {
           ReturnStats tempStats = entry.getValue();
           List<Stat> tempYahooStats = new LinkedList<Stat>();
           tempYahooStats.add(createStat(YahooStatConstants.KickRetYdsAvg, tempStats.getAvg()));
           tempYahooStats.add(createStat(YahooStatConstants.KickRetYdsLng, tempStats.getLng()));
           tempYahooStats.add(createStat(YahooStatConstants.KickRetYdsLngTd, tempStats.getLngtd()));
           tempYahooStats.add(createStat(YahooStatConstants.KickRet, tempStats.getRet()));
           tempYahooStats.add(createStat(YahooStatConstants.KickRetTds, tempStats.getTds()));

           result.put(entry.getKey(), tempYahooStats);
           
       }
       return result;
    }

    private Map<String, List<Stat>> processPuntingingMap(Map<String, PuntingStats> puntStatMap) 
    {
                 Map<String, List<Stat>> result = new HashMap<String, List<Stat>>();
        
       for (Map.Entry<String, PuntingStats> entry : puntStatMap.entrySet())
       {
           PuntingStats tempStats = entry.getValue();
           List<Stat> tempYahooStats = new LinkedList<Stat>();
           tempYahooStats.add(createStat(YahooStatConstants.PuntAvg, tempStats.getAvg()));
           tempYahooStats.add(createStat(YahooStatConstants.I20, tempStats.getI20()));
           tempYahooStats.add(createStat(YahooStatConstants.PuntLong, tempStats.getLng()));
           tempYahooStats.add(createStat(YahooStatConstants.Punts, tempStats.getPts()));
           tempYahooStats.add(createStat(YahooStatConstants.PuntYds, tempStats.getYds()));

           result.put(entry.getKey(), tempYahooStats);
           
       }
       return result;
    }

    private Map<String, List<Stat>> processPuntRetMap(Map<String, ReturnStats> puntRetMap) 
    {
       Map<String, List<Stat>> result = new HashMap<String, List<Stat>>();
       for (Map.Entry<String, ReturnStats> entry : puntRetMap.entrySet())
       {
           ReturnStats tempStats = entry.getValue();
           List<Stat> tempYahooStats = new LinkedList<Stat>();
           tempYahooStats.add(createStat(YahooStatConstants.PuntRetYdsAvg, tempStats.getAvg()));
           tempYahooStats.add(createStat(YahooStatConstants.PuntRetYdsLng, tempStats.getLng()));
           tempYahooStats.add(createStat(YahooStatConstants.PuntRetYdsLngTd, tempStats.getLngtd()));
           tempYahooStats.add(createStat(YahooStatConstants.PuntRet, tempStats.getRet()));
           tempYahooStats.add(createStat(YahooStatConstants.PuntRetTds, tempStats.getTds()));

           result.put(entry.getKey(), tempYahooStats);
           
       }
       return result;
    }  
    
    
    
    private Stat createStat(int stat_id, String value)
    {
        Stat temp = new Stat();
        temp.setStat_id(""+stat_id);
        if(value != null)
        {
            temp.setValue(Double.valueOf(value));
        }
        else
        {
            temp.setValue(Double.valueOf("0"));
        }
        return temp;
    }
    private  Map<String, List<Stat>> combineStats(Map<String, List<Stat>> base,  Map<String, List<Stat>> addon)
    {
         Map<String, List<Stat>> result = base;
         for (Map.Entry<String, List<Stat>> entry : addon.entrySet())
         {
             if(result.containsKey(entry.getKey()))
             {
                 List<Stat> combPlayerStats = combineStatList(result.get(entry.getKey()), entry.getValue()); 
                 String player = entry.getKey();
                 result.remove(player);
                 result.put(player, combPlayerStats);
             }
             else
             {
                 result.put(entry.getKey(), entry.getValue());
             }
         }
         return result;
    }

    private List<Stat> combineStatList(List<Stat> statList1, List<Stat> statList2) 
    {
        List<Stat> result = statList1;
        Map<String,Stat> statMap = new HashMap<String, Stat>();
        for(Stat stat : statList1)
        {
           statMap.put(stat.getStat_id(), stat);
        }
        
        for(Stat stat : statList2)
        {
            if(statMap.containsKey(stat.getStat_id()))
            {
                Stat tempStat = statMap.get(stat.getStat_id());
                tempStat.setValue(tempStat.getValue().doubleValue()+stat.getValue().doubleValue());
            }
            else
            {
                result.add(stat);
                statMap.put(stat.getStat_id(), stat);
            }
        }
        return result;
    }





}
