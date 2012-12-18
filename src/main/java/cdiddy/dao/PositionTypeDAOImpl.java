/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cdiddy.dao;

import cdiddy.objects.PositionType;
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
@Repository("PositionTypeDAO")
@Transactional
public class PositionTypeDAOImpl implements PositionTypeDAO
{
    
    private HibernateTemplate hibernateTemplate;
    
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) 
    {
        hibernateTemplate = new HibernateTemplate(sessionFactory);
    }
    
    @Transactional(readOnly = false)
    public void savePositionType(PositionType pt) 
    {
         hibernateTemplate.saveOrUpdate(pt);
    }
    
    @Transactional(readOnly = false)
    public void savePositionTypes(List<PositionType> listPT) 
    {
          hibernateTemplate.saveOrUpdateAll(listPT);
    }

    public List<PositionType> getAllPositionTypes() {
               return (List<PositionType>) hibernateTemplate.find("from "
                + PositionType.class.getName());
    }
    
    @SuppressWarnings("unchecked")
    public PositionType getPositionTypeById(String positionTypeId) 
    {
        return hibernateTemplate.get(PositionType.class, positionTypeId);
    }
    
    @Transactional(readOnly = false)
    public void deletePositionTypes(PositionType pt) 
    {
       hibernateTemplate.delete(pt);
    }

    @Transactional(readOnly = false)
    public void clearPositionTypes() {
        hibernateTemplate.deleteAll(hibernateTemplate.loadAll(PositionType.class));
    }
    
}
