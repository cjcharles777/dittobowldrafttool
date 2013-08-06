/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cdiddy.gui.table.model;

import cdiddy.objects.WeeklyStat;
import cdiddy.objects.StatCategory;
import cdiddy.utils.application.StatsService;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author cedric
 */
public class PlayerWeeklyInfoPanelTableModel extends AbstractTableModel
{
    private String[] columnNames;
    private List<WeeklyStat> listSS;
    private StatsService statsService;
    public PlayerWeeklyInfoPanelTableModel(List<WeeklyStat> listSS)
    {
        this.listSS = listSS;
        
    }

    public PlayerWeeklyInfoPanelTableModel() 
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
        
        if(listSS != null && listSS.size()>0)
        {
            return listSS.get(0).getStats().size()+1;
        }
        else
        {   
            return 0;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) 
    {
        
        WeeklyStat si = listSS.get(rowIndex);
        if (columnIndex > 0)
        {
            return si.getStats().get(columnIndex-1).getValue();
        }
        else
        {
            return si.getWeek();
        }
    }
    
    @Override
    public String getColumnName(int columnIndex)
    {
        if (columnIndex > 0)
        {
         int statCatId = Integer.parseInt(listSS.get(0).getStats().get(columnIndex - 1).getStat_id());
         StatCategory sc = statsService.getStatCategory(statCatId);
         if(sc != null)
         {
            return sc.getDisplay_name();
         }
         else
         {
             return ""+statCatId;
         }
        }
        else
        {
            return "Week";
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

