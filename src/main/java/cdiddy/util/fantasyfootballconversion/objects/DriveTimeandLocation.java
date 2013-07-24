/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cdiddy.util.fantasyfootballconversion.objects;

/**
 *
 * @author cedric
 */
class DriveTimeandLocation 
{
    private int qtr;
    private String time;
    private String yrdln;
    private String team;

    public int getQtr() {
        return qtr;
    }

    public void setQtr(int qtr) {
        this.qtr = qtr;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getYrdln() {
        return yrdln;
    }

    public void setYrdln(String yrdln) {
        this.yrdln = yrdln;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }
    
    
}
