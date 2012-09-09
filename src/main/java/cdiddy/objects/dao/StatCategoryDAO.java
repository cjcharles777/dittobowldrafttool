/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cdiddy.objects.dao;

import cdiddy.objects.StatCategory;
import java.util.List;

/**
 *
 * @author DMDD
 */
public interface StatCategoryDAO 
{
    public void saveStatCategory(StatCategory sc);
    public void saveStatCategories(List<StatCategory> listPT);
    public List<StatCategory> getAllStatCategories();
    public StatCategory getStatCategoryById(int statCategoriesId);
    public void deleteStatCategory(StatCategory player);
    public void clearStatCategory();
    
}
