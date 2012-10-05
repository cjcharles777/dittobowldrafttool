/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cdiddy.objects;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author cedric
 */
public class GameWeek 
{
    private String week;
    private String start;
    private String end;


    public String getWeek() 
    {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
         Integer.parseInt(week);
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) 
    {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public int getWeekInt() 
    {
        return Integer.parseInt(week);
    }

    public Date getStartDate() throws ParseException  
    {
        return new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH).parse(start);
    }


    public Date getEndDate() throws ParseException 
    {
        return new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH).parse(end);
    }


    
    
    
    
}
