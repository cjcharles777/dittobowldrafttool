/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cdiddy.objects.dao;

import cdiddy.objects.OAuthToken;
import cdiddy.objects.Player;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 *
 * @author DMDD
 */
public class OAuthDAOImpl implements OAuthDAO
{
      private HibernateTemplate hibernateTemplate;
 
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        hibernateTemplate = new HibernateTemplate(sessionFactory);
    }

    public void savePlayer(OAuthToken oat) {
         hibernateTemplate.saveOrUpdate(oat);
    }

    public OAuthToken getOAuthTokenById(int id) {
        return hibernateTemplate.get(OAuthToken.class, id);
    }

    public void deleteOAuthToken(OAuthToken oat) {
         hibernateTemplate.delete(oat);
    }

    public List<OAuthToken> getAllOAuth() {
        return (List<OAuthToken>) hibernateTemplate.find("from "
                + OAuthToken.class.getName());
    }
    
}
