/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cdiddy.fantasyfootballapp.fantasyfootballconversion.objects;

/**
 *
 * @author cedric
 */
public class ScoringSummary 
{
    private String type;
    private String desc;
    private int qtr;
    private String team;
    private Object players;
    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getQtr() {
        return qtr;
    }

    public void setQtr(int qtr) {
        this.qtr = qtr;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public Object getPlayers() {
        return players;
    }

    public void setPlayers(Object players) {
        this.players = players;
    }
    
}
