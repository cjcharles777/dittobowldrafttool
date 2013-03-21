/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cdiddy.fantasyfootballapp.fantasyfootballconversion.objects;

/**
 *
 * @author cedric
 */
public class Sequence 
{
    private int sequence;
    private String clubcode;
    private String playerName;
    private int statId;
    private int yards;

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public String getClubcode() {
        return clubcode;
    }

    public void setClubcode(String clubcode) {
        this.clubcode = clubcode;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getStatId() {
        return statId;
    }

    public void setStatId(int statId) {
        this.statId = statId;
    }


    public int getYards() {
        return yards;
    }

    public void setYards(int yards) {
        this.yards = yards;
    }
    
    
}
