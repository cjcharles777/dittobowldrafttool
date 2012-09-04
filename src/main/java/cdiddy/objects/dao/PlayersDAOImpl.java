/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cdiddy.objects.dao;

import cdiddy.objects.Player;
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

@Repository("PlayersDAO")
@Transactional
public class PlayersDAOImpl implements PlayersDAO
{
    private HibernateTemplate hibernateTemplate;
 
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        hibernateTemplate = new HibernateTemplate(sessionFactory);
    }
    
    @Transactional(readOnly = false)
    public void savePlayer(Player player) {
        hibernateTemplate.saveOrUpdate(player);
    }
        
    @Transactional(readOnly = false)
    public void savePlayers(List<Player> players) 
    {
        hibernateTemplate.saveOrUpdateAll(players);
    }

    
    public List<Player> getAllPlayers() {
         return (List<Player>) hibernateTemplate.find("from "
                + Player.class.getName());
    }

    @SuppressWarnings("unchecked")
    public Player getPlayerById(String playerId) {
        return hibernateTemplate.get(Player.class, playerId);
    }

    @Transactional(readOnly = false)
    public void deletePlayer(Player player) {
       hibernateTemplate.delete(player);
    }
    
    @Transactional(readOnly = false)
    public void clearPlayers() 
    {
       hibernateTemplate.deleteAll(hibernateTemplate.loadAll(Player.class));
    }

}
