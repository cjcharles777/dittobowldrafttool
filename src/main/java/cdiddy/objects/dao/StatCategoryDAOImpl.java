/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cdiddy.objects.dao;

import cdiddy.objects.PositionType;
import cdiddy.objects.StatCategory;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author DMDD
 */
@Repository("StatCategoryDAO")
@Transactional
public class StatCategoryDAOImpl implements StatCategoryDAO
{
    private HibernateTemplate hibernateTemplate;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) 
    {
        hibernateTemplate = new HibernateTemplate(sessionFactory);
    }
    
    @Transactional(readOnly = false)
    public void saveStatCategory(StatCategory sc) 
    {
        hibernateTemplate.saveOrUpdate(sc);
    }
    
    @Transactional(readOnly = false)
    public void saveStatCategories(List<StatCategory> listSC) 
    {
         hibernateTemplate.saveOrUpdateAll(listSC);
    }

    public List<StatCategory> getAllStatCategories() 
    {
                   return (List<StatCategory>) hibernateTemplate.find("from "
                + StatCategory.class.getName());
    }
    
    @SuppressWarnings("unchecked")
    public StatCategory getStatCategoryById(int statCategoriesId) 
    {
        return hibernateTemplate.get(StatCategory.class, statCategoriesId);
    }
    
    @Transactional(readOnly = false)
    public void deleteStatCategory(StatCategory sc) {
        hibernateTemplate.delete(sc);
    }
    
    @Transactional(readOnly = false)
    public void clearStatCategory() 
    {
         hibernateTemplate.deleteAll(hibernateTemplate.loadAll(StatCategory.class));
    }
    
}
