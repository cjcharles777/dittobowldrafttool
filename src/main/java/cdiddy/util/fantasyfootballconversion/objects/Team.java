/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cdiddy.util.fantasyfootballconversion.objects;

import java.util.Map;

/**
 *
 * @author cedric
 */
public class Team 
{
    private Map<String, Integer> score;
    private String abbr;
    private int to;
    private TeamStats stats;
    private Object players;

    public Map<String, Integer> getScore() {
        return score;
    }

    public void setScore(Map<String, Integer> score) {
        this.score = score;
    }

    public String getAbbr() {
        return abbr;
    }

    public void setAbbr(String abbr) {
        this.abbr = abbr;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public TeamStats getStats() {
        return stats;
    }

    public void setStats(TeamStats stats) {
        this.stats = stats;
    }

    public Object getPlayers() {
        return players;
    }

    public void setPlayers(Object players) {
        this.players = players;
    }
    
    
    
    
}
