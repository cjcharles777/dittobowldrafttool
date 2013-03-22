/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cdiddy.fantasyfootballapp.fantasyfootballconversion.objects;

import java.util.Map;

/**
 *
 * @author cedric
 */
public class Game 
{
    private Team home;
    private Team away;
    private Map<String, Object> drives;
    private Map<String, ScoringSummary> scrsummary;
    private String weather;
    private String media;
    private String yl;
    private String qtr;
    private int down;
    private int togo;
    private boolean redzone;
    private String clock;
    private String posteam;
    private String stadium;
    private String note;

    public Team getHome() {
        return home;
    }

    public void setHome(Team home) {
        this.home = home;
    }

    public Team getAway() {
        return away;
    }

    public void setAway(Team away) {
        this.away = away;
    }

    public Map<String, Object> getDrives() {
        return drives;
    }

    public void setDrives(Map<String, Object> drives) {
        this.drives = drives;
    }

    public Map<String, ScoringSummary> getScrsummary() {
        return scrsummary;
    }

    public void setScrsummary(Map<String, ScoringSummary> scrsummary) {
        this.scrsummary = scrsummary;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public String getYl() {
        return yl;
    }

    public void setYl(String yl) {
        this.yl = yl;
    }

    public String getQtr() {
        return qtr;
    }

    public void setQtr(String qtr) {
        this.qtr = qtr;
    }

    
    public int getDown() {
        return down;
    }

    public void setDown(int down) {
        this.down = down;
    }

    public int getTogo() {
        return togo;
    }

    public void setTogo(int togo) {
        this.togo = togo;
    }

    public boolean isRedzone() {
        return redzone;
    }

    public void setRedzone(boolean redzone) {
        this.redzone = redzone;
    }

    public String getClock() {
        return clock;
    }

    public void setClock(String clock) {
        this.clock = clock;
    }

    public String getPosteam() {
        return posteam;
    }

    public void setPosteam(String posteam) {
        this.posteam = posteam;
    }

    public String getStadium() {
        return stadium;
    }

    public void setStadium(String stadium) {
        this.stadium = stadium;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
    
    
}
