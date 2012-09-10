/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cdiddy.objects.dao;

import cdiddy.objects.Stat;
import java.util.List;

/**
 *
 * @author cedric
 */
public interface StatDAO 
{
    public void saveStat(Stat sc);
    public void saveStats(List<Stat> listS);
    public List<Stat> getAllStat();
    public Stat getStatById(int statId);
    public void deleteStat(Stat s);
    public void clearStats();
}
