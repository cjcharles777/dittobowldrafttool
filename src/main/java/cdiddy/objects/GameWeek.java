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
    private String start;
    private String end;
    private Date startDate;
    private Date endDate;
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
    public Date getStartDate() {
        return startDate;
    }

    public void setStart(String startStr) 
    {
        try 
        {
            this.startDate = new SimpleDateFormat("yyyy-MM-dd").parse(startStr);
        } 
        catch (ParseException ex) 
        {
            this.startDate = null;
            Logger.getLogger(GameWeek.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  

    @Column(name = "endDate",  nullable=false)
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getEndDate() {
        return endDate;
    }

    
    public void setEnd(String endStr) 
    {
        try 
        {
            this.endDate = new SimpleDateFormat("yyyy-MM-dd").parse(endStr);
            System.out.println(this.endDate.toString());
        } 
        catch (ParseException ex) 
        {
            Logger.getLogger(GameWeek.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  

   @Column(name = "season", length=4, nullable=false)
    public String getYear() 
   {
        return year;
    }

    public void setYear(String year) 
    {
        this.year = year;
    }

    @Id
    @GeneratedValue(generator = "generator")
    @GenericGenerator(name = "generator", strategy = "increment")
    @Column(name = "gameweekid", nullable=false)
    public int getId() 
    {
        return id;
    }

    public void setId(int id) 
    {
        this.id = id;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }


    
    
    
    
}
