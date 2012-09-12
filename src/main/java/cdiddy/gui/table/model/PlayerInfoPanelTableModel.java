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
        return listSS.get(0).getStats().size()+1;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) 
    {
        
        SeasonStat si = listSS.get(rowIndex);
        if (columnIndex > 0)
        {
            return si.getStats().get(columnIndex-1).getValue();
        }
        else
        {
            return si.getSeason();
        }
    }
    
    @Override
    public String getColumnName(int columnIndex)
    {
        if (columnIndex > 0)
        {
         int statCatId = Integer.parseInt(listSS.get(0).getStats().get(columnIndex - 1).getStat_id());
         StatCategory sc = statsService.getStatCategory(statCatId);
         return sc.getDisplay_name();
        }
        else
        {
            return "Year";
        }
    }
    @Override
   public Class<?> getColumnClass(int columnIndex)
    {
          if (columnIndex > 0)
           {
            return Double.class;
           }
          else
          {
            return String.class;
          }
    }
    
    public void setStatsService(StatsService ss)
    {
        this.statsService = ss;
    }
    
}
