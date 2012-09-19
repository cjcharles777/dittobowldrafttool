/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cdiddy.objects;

import java.util.List;

/**
 *
 * @author cedric
 */
public class Team 
{
    private String teamKey;
    private String teamId;
    private String name;
    private String teamurl;
    private String teamLogoUrl;
    private int numberOfMoves;
    private List<Player> teamPlayers;

    public String getTeamKey() {
        return teamKey;
    }

    public void setTeamKey(String teamKey) {
        this.teamKey = teamKey;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeamurl() {
        return teamurl;
    }

    public void setTeamurl(String teamurl) {
        this.teamurl = teamurl;
    }

    public String getTeamLogoUrl() {
        return teamLogoUrl;
    }

    public void setTeamLogoUrl(String teamLogoUrl) {
        this.teamLogoUrl = teamLogoUrl;
    }

    public int getNumberOfMoves() {
        return numberOfMoves;
    }

    public void setNumberOfMoves(int numberOfMoves) {
        this.numberOfMoves = numberOfMoves;
    }

    public List<Player> getTeamPlayers() {
        return teamPlayers;
    }

    public void setTeamPlayers(List<Player> teamPlayers) {
        this.teamPlayers = teamPlayers;
    }
    
        @Override
    public String toString()
    {
        return name;
    }
}
