/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cdiddy.objects.league;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author cedric
 */
@Entity
@Table(name = "SavedYahooLeague")
public class YahooLeague 
{
     private int id;
     private String league_key;
     private String league_id;
     private String name;
     private String url;
     private String draft_status;
     private String num_teams;
     private String edit_key;
     private String weekly_deadline;
     private String league_update_timestamp;
     private String scoring_type;
     private String league_type;
     private String is_pro_league;
     private String current_week;
     private String start_week;
     private String start_week_start_date;
     private String end_week;
     private String is_finished;
     private String start_date;
     private String end_date;
     

    @Id
    @GeneratedValue(generator = "generator")
    @GenericGenerator(name = "generator", strategy = "increment")
    @Column(name = "leauge_table_id", nullable=false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "league_key", length=20, nullable=false)
    public String getLeague_key() {
        return league_key;
    }
    public void setLeague_key(String league_key) {
        this.league_key = league_key;
    }

    @Column(name = "league_id", length=20, nullable=false)
    public String getLeague_id() {
        return league_id;
    }

    public void setLeague_id(String league_id) {
        this.league_id = league_id;
    }

     @Column(name = "name", length=1000, nullable=false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

     @Column(name = "url", length=1000, nullable=false)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

     @Column(name = "draft_status", length=20, nullable=false)
    public String getDraft_status() {
        return draft_status;
    }

    public void setDraft_status(String draft_status) {
        this.draft_status = draft_status;
    }

     @Column(name = "num_teams", length=20, nullable=false)
    public String getNum_teams() {
        return num_teams;
    }

    public void setNum_teams(String num_teams) {
        this.num_teams = num_teams;
    }

     @Column(name = "edit_key", length=200, nullable=false)
    public String getEdit_key() {
        return edit_key;
    }

    public void setEdit_key(String edit_key) {
        this.edit_key = edit_key;
    }

     @Column(name = "weekly_deadline", length=200, nullable=false)
    public String getWeekly_deadline() {
        return weekly_deadline;
    }

    public void setWeekly_deadline(String weekly_deadline) {
        this.weekly_deadline = weekly_deadline;
    }

     @Column(name = "league_update_timestamp", length=200, nullable=false)
    public String getLeague_update_timestamp() {
        return league_update_timestamp;
    }

    public void setLeague_update_timestamp(String league_update_timestamp) {
        this.league_update_timestamp = league_update_timestamp;
    }

     @Column(name = "scoring_type", length=20, nullable=false)
    public String getScoring_type() {
        return scoring_type;
    }

    public void setScoring_type(String scoring_type) {
        this.scoring_type = scoring_type;
    }

     @Column(name = "league_type", length=20, nullable=false)
    public String getLeague_type() {
        return league_type;
    }

    public void setLeague_type(String league_type) {
        this.league_type = league_type;
    }

    @Column(name = "is_pro_league", length=20, nullable=false)
    public String getIs_pro_league() {
        return is_pro_league;
    }

    public void setIs_pro_league(String is_pro_league) {
        this.is_pro_league = is_pro_league;
    }

     @Column(name = "current_week", length=200, nullable=false)
    public String getCurrent_week() {
        return current_week;
    }

    public void setCurrent_week(String current_week) {
        this.current_week = current_week;
    }

     @Column(name = "start_week", length=200, nullable=false)
    public String getStart_week() {
        return start_week;
    }

    public void setStart_week(String start_week) {
        this.start_week = start_week;
    }

     @Column(name = "start_week_start_date", length=200, nullable=false)
    public String getStart_week_start_date() {
        return start_week_start_date;
    }

    public void setStart_week_start_date(String start_week_start_date) {
        this.start_week_start_date = start_week_start_date;
    }

     @Column(name = "end_week", length=20, nullable=false)
    public String getEnd_week() {
        return end_week;
    }

    public void setEnd_week(String end_week) {
        this.end_week = end_week;
    }


     @Column(name = "is_finished", length=20, nullable=false)
    public String getIs_finished() {
        return is_finished;
    }

    public void setIs_finished(String is_finished) {
        this.is_finished = is_finished;
    }

     @Column(name = "start_date", length=20, nullable=false)
    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

     @Column(name = "end_date", length=20, nullable=false)
    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + ( this.league_key != null ? this.league_key.hashCode() : 0 );
        hash = 61 * hash + ( this.league_id != null ? this.league_id.hashCode() : 0 );
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final YahooLeague other = (YahooLeague) obj;
        if (( this.league_key == null ) ? ( other.league_key != null ) : !this.league_key.equals(other.league_key)) {
            return false;
        }
        if (( this.league_id == null ) ? ( other.league_id != null ) : !this.league_id.equals(other.league_id)) {
            return false;
        }
        return true;
    }
    
    
     
    @Override
    public String toString()
    {
        return name + "   " + league_id;
    }
     
}
