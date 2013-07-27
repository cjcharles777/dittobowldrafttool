/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cdiddy.objects;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author cedric
 */
@Entity
@Table(name = "GameWeek")

public class GameWeek implements Serializable 
{
    private String year;
    private String week;
    private Date start;
    private Date end;
    private int id;

    @Column(name = "week", length=3, nullable=false)
    public String getWeek() 
    {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
         Integer.parseInt(week);
    }

    @Column(name = "startDate", nullable=false)
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getStart() {
        return start;
    }

    public void setStart(String startStr) 
    {
        try 
        {
            this.start = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH).parse(startStr);
        } 
        catch (ParseException ex) 
        {
            this.start = null;
            Logger.getLogger(GameWeek.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Column(name = "endDate", length=4, nullable=false)
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getEnd() {
        return end;
    }

    
    public void setEnd(String endStr) 
    {
        try 
        {
            this.end = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH).parse(endStr);
        } 
        catch (ParseException ex) 
        {
            Logger.getLogger(GameWeek.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


   @Column(name = "year", length=4, nullable=false)
    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Id
    @GeneratedValue(generator = "generator")
    @GenericGenerator(name = "generator", strategy = "increment")
    @Column(name = "gameweekid", nullable=false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    
    
    
    
}
