 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cdiddy.objects;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

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
    private List<Position> eligiblePositons;
    private List<SeasonStat> seasonStats;
    private List<WeeklyStat> weeklyStats;


  
    
    @Id
    @GeneratedValue(generator = "generator")
    @GenericGenerator(name = "generator", strategy = "increment")
    @Column(name = "playerid", nullable=false)
    public int getId() 
    {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
     @Column(name = "firstname", length=500, nullable=false)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "lastname", length=500, nullable=false)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
 
    @Column(name = "position", length=30, nullable=false)
    public String getDisplayPosition() {
        return displayPosition;
    }

    public void setDisplayPosition(String displayPosition) {
        this.displayPosition = displayPosition;
    }
    
    @Column(name = "headshot", length=1000, nullable=false)
    public String getHeadshotHtml() {
        return headshotHtml;
    }

    public void setHeadshotHtml(String headshotHtml) {
        this.headshotHtml = headshotHtml;
    }


    @Column(name = "yahooid", nullable=false)
    public int getYahooId() {
        return yahooId;
    }

    public void setYahooId(int yahooId) {
        this.yahooId = yahooId;
    }
    
    @Column(name = "team", length=500, nullable=false)
    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }
    
    @Column(name = "number", nullable=false)
    public int getUniformNumber() {
        return uniformNumber;
    }

    public void setUniformNumber(int uniformNumber) {
        this.uniformNumber = uniformNumber;
    }
    
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
    @JoinTable(
            name="PlayerToSeasonStats",
            joinColumns = @JoinColumn( name="playerid"),
            inverseJoinColumns = @JoinColumn( name="season_stat_id")
    )
    @LazyCollection(LazyCollectionOption.FALSE)
    public List<SeasonStat> getSeasonStats() 
    {
        return seasonStats;
    }

    public void setSeasonStats(List<SeasonStat> seasonStats) 
    {
        this.seasonStats = seasonStats;
    }
        @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
    @JoinTable(
            name="PlayerToWeeklyStats",
            joinColumns = @JoinColumn( name="playerid"),
            inverseJoinColumns = @JoinColumn( name="weekly_stat_id")
    )
    @LazyCollection(LazyCollectionOption.FALSE)
    public List<WeeklyStat> getWeeklyStats() 
    {
        return weeklyStats;
    }

    public void setWeeklyStats(List<WeeklyStat> weeklyStats) 
    {
        this.weeklyStats = weeklyStats;
    }

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
    @JoinTable(
            name="PlayerToEligiblePositions",
            joinColumns = @JoinColumn( name="playerid"),
            inverseJoinColumns = @JoinColumn( name="position_id")
    )
    @LazyCollection(LazyCollectionOption.FALSE)
    public List<Position> getEligiblePositons() {
        return eligiblePositons;
    }

    public void setEligiblePositons(List<Position> eligiblePositons) {
        this.eligiblePositons = eligiblePositons;
    }
    
    
}
