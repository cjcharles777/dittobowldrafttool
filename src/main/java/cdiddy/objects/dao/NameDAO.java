/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cdiddy.objects.dao;

import cdiddy.objects.Name;
import cdiddy.objects.WeeklyStat;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author cedric
 */
@Repository("nameDAO")
public interface NameDAO 
{
    public void saveName(Name n);
    public void saveNames(List<Name> listN);
    public List<Name> getNames();
    public Name geNameById(int nameId);
    public void deleteName(Name n);
    public void clearNames();
}
