/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cdiddy.objects;

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
@Table(name = "SeasonStat")
public class SeasonStat 
{
    private int id;
    private String season;
    List<Stat> stats;

    
    @Id
    @GeneratedValue(generator = "generator")
    @GenericGenerator(name = "generator", strategy = "increment")
    @Column(name = "season_stat_id", nullable=false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    @Column(name = "SEASON", length=4, nullable=false)
    public String getSeason() 
    {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    @OneToMany( cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name="SeasonStatsToStats",
            joinColumns = @JoinColumn( name="season_stat_id"),
            inverseJoinColumns = @JoinColumn( name="table_stat_id")
    )
    @LazyCollection(LazyCollectionOption.FALSE)
    public List<Stat> getStats() {
        return stats;
    }

    public void setStats(List<Stat> stats) {
        this.stats = stats;
    }
    
}
