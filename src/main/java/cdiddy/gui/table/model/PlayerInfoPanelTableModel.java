/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cdiddy.gui.table.model;

import cdiddy.objects.SeasonStat;
import cdiddy.objects.StatCategory;
import cdiddy.utils.application.StatsService;
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
    private StatsService statsService;
    public PlayerInfoPanelTableModel(List<SeasonStat> listSS)
    {
        this.listSS = listSS;
        
    }

    public PlayerInfoPanelTableModel() 
    {
        
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
        SeasonStat si = listSS.get(rowIndex);
        return si.getStats().get(columnIndex).getValue();
    }
    
    @Override
    public String getColumnName(int columnIndex)
    {
         int statCatId = Integer.parseInt(listSS.get(0).getStats().get(columnIndex).getStat_id());
         StatCategory sc = statsService.getStatCategory(statCatId);
         return sc.getDisplay_name();
         
    }
    @Override
   public Class<?> getColumnClass(int columnIndex)
    {
            return Double.class;
    }
    
    public void setStatsService(StatsService ss)
    {
        this.statsService = ss;
    }
    
}
