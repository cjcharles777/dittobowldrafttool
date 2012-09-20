/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cdiddy.objects;

import java.util.List;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author cedric
 */
public class Team 
{
    private String gameKey;
    private String leaugeid;
    private String teamKey;
    private String teamId;
    private String name;
    private String teamurl;
    private String teamLogoUrl;
    private int numberOfMoves;
    private List<Player> teamPlayers;
    private TeamStandings standings;

    public String getTeamKey() {
        return teamKey;
    }

    public void setTeamKey(String teamKey) {
        this.teamKey = teamKey;
        String[] temp = StringUtils.split(teamKey, ".");
        setLeaugeid(temp[2]);
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

    public String getGameKey() {
        return gameKey;
    }

    public void setGameKey(String gameKey) {
        this.gameKey = gameKey;
    }

    public String getLeaugeid() {
        return leaugeid;
    }

    public void setLeaugeid(String leaugeid) {
        this.leaugeid = leaugeid;
    }

    public TeamStandings getStandings() {
        return standings;
    }

    public void setStandings(TeamStandings standings) {
        this.standings = standings;
    }
    
    
    
        @Override
    public String toString()
    {
        return name;
    }
}
