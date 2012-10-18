/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cdiddy.objects.dao;

import cdiddy.objects.ByeWeek;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author cedric
 */
@Repository("byeWeekDAO")
public interface ByeWeekDAO 
{
    public void saveByeWeek(ByeWeek bw);
    public void saveByeWeeks(List<ByeWeek> listN);
    public List<ByeWeek> getByeWeeks();
    public ByeWeek geByeWeekById(int byeWeekId);
    public void deleteByeWeek(ByeWeek bw);
    public void clearByeWeeks();
}
