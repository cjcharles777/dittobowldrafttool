/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cdiddy.dao;

import cdiddy.objects.league.YahooLeague;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author cedric
 */
@Repository("SavedYahooLeagueDAO")
@Transactional
public class SavedYahooLeagueDAOImpl implements SavedYahooLeagueDAO
{

    private HibernateTemplate hibernateTemplate;
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) 
    {
        hibernateTemplate = new HibernateTemplate(sessionFactory);
    }
    
    @Transactional(readOnly = false)
    public void saveSavedYahooLeague(YahooLeague yl) 
    {
        hibernateTemplate.saveOrUpdate(yl);
    }

    public void saveSavedYahooLeague(List<YahooLeague> listYl) 
    {
         hibernateTemplate.saveOrUpdateAll(listYl);
    }

    public List<YahooLeague> getAllSavedYahooLeagues() 
    {
         return (List<YahooLeague>) hibernateTemplate.find("from "
                + YahooLeague.class.getName());
    }

    public YahooLeague getSavedYahooLeagueById(int leaugeTableId) 
    {
        return hibernateTemplate.get(YahooLeague.class, leaugeTableId);
    }

    public void deleteSavedYahooLeague(YahooLeague yl) 
    {
        hibernateTemplate.delete(yl);
    }

    public void clearSavedYahooLeagueTable() 
    {
        hibernateTemplate.deleteAll(hibernateTemplate.loadAll(YahooLeague.class));
    }
    
}
