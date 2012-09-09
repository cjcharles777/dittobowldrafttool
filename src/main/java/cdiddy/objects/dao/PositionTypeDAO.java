/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cdiddy.objects.dao;

import cdiddy.objects.PositionType;
import java.util.List;

/**
 *
 * @author DMDD
 */
public interface PositionTypeDAO 
{
    
    public void savePositionType(PositionType pt);
    public void savePositionTypes(List<PositionType> listPT);
    public List<PositionType> getAllPositionTypes();
    public PositionType getPositionTypeById(String positionTypeId);
    public void deletePositionTypes(PositionType player);
    public void clearPositionTypes();
    
}
