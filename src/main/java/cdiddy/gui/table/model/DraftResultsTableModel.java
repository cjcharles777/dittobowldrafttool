/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cdiddy.gui.table.model;

import cdiddy.objects.Player;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author cedric
 */
public class DraftResultsTableModel  extends AbstractTableModel
{
    private List<Player> li = new ArrayList();
    private String[] columnNames = { "ID", "Player",  "Drafted By", "Pick Number","Actual Rank(Amonst Draftees)"};
    
    public DraftResultsTableModel(List<Player> list)
    {
         this.li = list;
    }
    
    @Override
    public String getColumnName(int columnIndex){
         return columnNames[columnIndex];
    }

    @Override     
    public int getRowCount() {
        return li.size();
    }

    @Override        
    public int getColumnCount() {
        return 5; 
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Player si = li.get(rowIndex);
        switch (columnIndex) {
            case 0: 
                return si.getId();
            case 1:
                return si.getDisplay_position();
            case 2:
                return si.getName().getFirst();
            case 3:
                return si.getName().getLast();
            case 4:
                return si.getEditorial_team_full_name();

           }
           return null;
   }

   @Override
   public Class<?> getColumnClass(int columnIndex){
          switch (columnIndex){
             case 0:
                return Integer.class;
             case 1:
                return String.class;
             case 2:
               return String.class;
             case 3:
                return String.class;
             case 4:
                return String.class;
             case 5:
              return Integer.class;
             }
             return null;
      }
   
    
}