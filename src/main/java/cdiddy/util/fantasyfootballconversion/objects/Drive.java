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
public class Drive 
{
    private String posteam;
    private int qtr;
    private boolean redzone;
    private Map<String, Play> plays;
    private int fds;
    private String result;
    private int penyds;
    private int ydsgained;
    private int numplays;
    private String postime;
    private DriveTimeandLocation start;
    private DriveTimeandLocation end;

    public String getPosteam() {
        return posteam;
    }

    public void setPosteam(String posteam) {
        this.posteam = posteam;
    }

    public int getQtr() {
        return qtr;
    }

    public void setQtr(int qtr) {
        this.qtr = qtr;
    }

    public boolean isRedzone() {
        return redzone;
    }

    public void setRedzone(boolean redzone) {
        this.redzone = redzone;
    }

    public Map<String, Play> getPlays() {
        return plays;
    }

    public void setPlays(Map<String, Play> plays) {
        this.plays = plays;
    }

    public int getFds() {
        return fds;
    }

    public void setFds(int fds) {
        this.fds = fds;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getPenyds() {
        return penyds;
    }

    public void setPenyds(int penyds) {
        this.penyds = penyds;
    }

    public int getYdsgained() {
        return ydsgained;
    }

    public void setYdsgained(int ydsgained) {
        this.ydsgained = ydsgained;
    }

    public int getNumplays() {
        return numplays;
    }

    public void setNumplays(int numplays) {
        this.numplays = numplays;
    }

    public String getPostime() {
        return postime;
    }

    public void setPostime(String postime) {
        this.postime = postime;
    }

    public DriveTimeandLocation getStart() {
        return start;
    }

    public void setStart(DriveTimeandLocation start) {
        this.start = start;
    }

    public DriveTimeandLocation getEnd() {
        return end;
    }

    public void setEnd(DriveTimeandLocation end) {
        this.end = end;
    }
    
    
    
}
