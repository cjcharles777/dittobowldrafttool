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
public class TeamStats 
{
    private Map<String , PassingStats> passing;
    private Map<String , RushingStats> rushing;
    private Map<String , ReceivingStats> receiving;
    private Map<String , FumblesStats> fumbles;
    private Map<String , KickingStats> kicking;
    private Map<String , PuntingStats> punting;
    private Map<String , ReturnStats> kickret;
    private Map<String , ReturnStats> puntret;
    private Map<String , DefenseStats> defense;
    private GameTeamStats team;

    public Map<String, PassingStats> getPassing() {
        return passing;
    }

    public void setPassing(Map<String, PassingStats> passing) {
        this.passing = passing;
    }

    public Map<String, RushingStats> getRushing() {
        return rushing;
    }

    public void setRushing(Map<String, RushingStats> rushing) {
        this.rushing = rushing;
    }

    public Map<String, ReceivingStats> getReceiving() {
        return receiving;
    }

    public void setReceiving(Map<String, ReceivingStats> receiving) {
        this.receiving = receiving;
    }

    public Map<String, FumblesStats> getFumbles() {
        return fumbles;
    }

    public void setFumbles(Map<String, FumblesStats> fumbles) {
        this.fumbles = fumbles;
    }

    public Map<String, KickingStats> getKicking() {
        return kicking;
    }

    public void setKicking(Map<String, KickingStats> kicking) {
        this.kicking = kicking;
    }

    public Map<String, PuntingStats> getPunting() {
        return punting;
    }

    public void setPunting(Map<String, PuntingStats> punting) {
        this.punting = punting;
    }

    public Map<String, ReturnStats> getKickret() {
        return kickret;
    }

    public void setKickret(Map<String, ReturnStats> kickret) {
        this.kickret = kickret;
    }

    public Map<String, ReturnStats> getPuntret() {
        return puntret;
    }

    public void setPuntret(Map<String, ReturnStats> puntret) {
        this.puntret = puntret;
    }

    public Map<String, DefenseStats> getDefense() {
        return defense;
    }

    public void setDefense(Map<String, DefenseStats> defense) {
        this.defense = defense;
    }

    public GameTeamStats getTeam() {
        return team;
    }

    public void setTeam(GameTeamStats team) {
        this.team = team;
    }
    
    
    
}
