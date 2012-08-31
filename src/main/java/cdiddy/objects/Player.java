/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cdiddy.objects;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 *
 * @author DMDD
 */
public class Player 
{

    private int id;
    private String playerKey;
    private String fullName;
    private String team;
    private int uniformNumber;
    private char displayPosition;
    private String headshotHtml;

    public Player (ArrayList<LinkedHashMap> lhm)
    {
        this.playerKey = (String) lhm.get(0).get("player_key");
        this.id = (Integer) lhm.get(0).get("player_id");
    }
    
    public char getDisplayPosition() {
        return displayPosition;
    }

    public void setDisplayPosition(char displayPosition) {
        this.displayPosition = displayPosition;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getHeadshotHtml() {
        return headshotHtml;
    }

    public void setHeadshotHtml(String headshotHtml) {
        this.headshotHtml = headshotHtml;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlayerKey() {
        return playerKey;
    }

    public void setPlayerKey(String playerKey) {
        this.playerKey = playerKey;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public int getUniformNumber() {
        return uniformNumber;
    }

    public void setUniformNumber(int uniformNumber) {
        this.uniformNumber = uniformNumber;
    }
    
    
    
    
}
