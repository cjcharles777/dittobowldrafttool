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
 * @author DMDD
 */
public class PlayersTableModel extends AbstractTableModel{
 

    private List<Player> li = new ArrayList();
    private String[] columnNames = { "ID", "Postion", "First Name", "Last Name",
                "Team", "Uniform Number"};

    public PlayersTableModel(List<Player> list){
        if(list !=null)
        {
            this.li = list;
        }
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
        return 6; 
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
            case 5:
                return si.getUniform_number();

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
