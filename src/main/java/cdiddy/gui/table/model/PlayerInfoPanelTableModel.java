/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cdiddy.gui.table.model;

import cdiddy.objects.SeasonStat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author DMDD
 */
public class PlayerInfoPanelTableModel extends AbstractTableModel
{
    private String[] columnNames;
    private List<SeasonStat> listSS;
    public PlayerInfoPanelTableModel(List<SeasonStat> listSS)
    {
        this.listSS = listSS;
        
    }

    @Override
    public int getRowCount() 
    {
        return listSS.size();
    }
    
    @Override
    public int getColumnCount() 
    {
        return listSS.get(0).getStats().size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) 
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public String getColumnName(int columnIndex){
         return columnNames[columnIndex];
    }
    
}
