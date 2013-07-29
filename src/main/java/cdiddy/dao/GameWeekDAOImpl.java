/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cdiddy.dao;

import cdiddy.objects.GameWeek;
import java.util.Date;
import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Cedric
 */
@Repository("gameWeekDAO")
@Transactional
public class GameWeekDAOImpl implements GameWeekDAO
{
     private HibernateTemplate hibernateTemplate;
     
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) 
    {
        hibernateTemplate = new HibernateTemplate(sessionFactory);
    }

    @Transactional(readOnly = false)
    @Override
    public void saveGameWeek(GameWeek gw) {
         hibernateTemplate.saveOrUpdate(gw);
    }

    @Transactional(readOnly = false)
    @Override
    public void saveGameWeek(List<GameWeek> listGW) 
    {
         hibernateTemplate.saveOrUpdateAll(listGW);
    }

    @Override
    public List<GameWeek> getAllGameWeek() {
                  return (List<GameWeek>) hibernateTemplate.find("from "
                + GameWeek.class.getName());
    }

    @Override
    public GameWeek getGameWeekById(int gwId) 
    {
        return hibernateTemplate.get(GameWeek.class, gwId);
    }
    
    @Override
    public List<GameWeek> retrieveContainingGameWeek(Date d)
    {
         return (List<GameWeek>) hibernateTemplate.findByCriteria(
        DetachedCriteria.forClass(GameWeek.class)
                .add(Restrictions.le("startDate",d ))
                 .add(Restrictions.ge("endDate",d )));
    
    }
    @Transactional(readOnly = false)
    @Override
    public void deleteGameWeek(GameWeek gw) 
    {
        hibernateTemplate.delete(gw);
    }

    @Transactional(readOnly = false)
    @Override
    public void clearGameWeek() 
    {
        hibernateTemplate.deleteAll(hibernateTemplate.loadAll(GameWeek.class));
    }
    
}
