/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cdiddy.fantasyfootballapp.fantasyfootballconversion.objects;

import java.util.List;
import java.util.Map;

/**
 *
 * @author cedric
 */
public class Play 
{
    private int sp;
    private int qtr;
    private int down;
    private String time;
    private String yrdln;
    private int ydstogo;
    private int ydsnet;
    private String posteam;
    private String desc;
    private String note;
    private Map <String , List<Map<String,Sequence>>> players;

    public int getSp() {
        return sp;
    }

    public void setSp(int sp) {
        this.sp = sp;
    }

    public int getQtr() {
        return qtr;
    }

    public void setQtr(int qtr) {
        this.qtr = qtr;
    }

    public int getDown() {
        return down;
    }

    public void setDown(int down) {
        this.down = down;
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

    public int getYdstogo() {
        return ydstogo;
    }

    public void setYdstogo(int ydstogo) {
        this.ydstogo = ydstogo;
    }

    public int getYdsnet() {
        return ydsnet;
    }

    public void setYdsnet(int ydsnet) {
        this.ydsnet = ydsnet;
    }

    public String getPosteam() {
        return posteam;
    }

    public void setPosteam(String posteam) {
        this.posteam = posteam;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Map<String, List<Map<String, Sequence>>> getPlayers() {
        return players;
    }

    public void setPlayers(Map<String, List<Map<String, Sequence>>> players) {
        this.players = players;
    }
    
}
