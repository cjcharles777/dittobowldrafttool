/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cdiddy.objects;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author DMDD
 */
@Entity
@Table(name = "Players")
public class Player implements Serializable
{

    private int id;
    private String firstName;
    private String lastName;
    private String team;
    private int uniformNumber;
    private String displayPosition;
    private String headshotHtml;
    private int yahooId;
    private List<SeasonStat> seasonStats;


  
    
    @Id
    @GeneratedValue(generator = "generator")
    @GenericGenerator(name = "generator", strategy = "increment")
    @Column(name = "player_id", nullable=false)
    public int getId() 
    {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
     @Column(name = "FIRSTNAME", length=500, nullable=false)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "LASTNAME", length=500, nullable=false)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
 
    @Column(name = "DISPLAYPOSITION", length=30, nullable=false)
    public String getDisplayPosition() {
        return displayPosition;
    }

    public void setDisplayPosition(String displayPosition) {
        this.displayPosition = displayPosition;
    }
    
    @Column(name = "HEADSHOTHTML", length=1000, nullable=false)
    public String getHeadshotHtml() {
        return headshotHtml;
    }

    public void setHeadshotHtml(String headshotHtml) {
        this.headshotHtml = headshotHtml;
    }


    @Column(name = "YAHOOID", nullable=false)
    public int getYahooId() {
        return yahooId;
    }

    public void setYahooId(int yahooId) {
        this.yahooId = yahooId;
    }
    
    @Column(name = "TEAM", length=500, nullable=false)
    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }
    
    @Column(name = "UNIFORMNUMBER", nullable=false)
    public int getUniformNumber() {
        return uniformNumber;
    }

    public void setUniformNumber(int uniformNumber) {
        this.uniformNumber = uniformNumber;
    }
    
    @OneToMany( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
    @JoinTable(
            name="PlayerToSeasonStats",
            joinColumns = @JoinColumn( name="player_id"),
            inverseJoinColumns = @JoinColumn( name="season_stat_id")
    )
    public List<SeasonStat> getSeasonStats() 
    {
        return seasonStats;
    }

    public void setSeasonStats(List<SeasonStat> seasonStats) 
    {
        this.seasonStats = seasonStats;
    }
    
    
    
}
