/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cdiddy.gui.table.model;

import cdiddy.objects.draft.EnhancedDraftPick;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author cedric
 */
public class DraftResultsTableModel  extends AbstractTableModel
{
    private List<EnhancedDraftPick> li = new ArrayList();
    private String[] columnNames = { "Pick", "Player",  "Drafted By", "Round","Actual Rank(Amonst Draftees)"};
    
    public DraftResultsTableModel(List<EnhancedDraftPick> list)
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
        EnhancedDraftPick pick = li.get(rowIndex);
        switch (columnIndex) {
            case 0: 
                return pick.getPick();
            case 1:
                return pick.getPlayer().getName().getFull();
            case 2:
                return pick.getTeam().getName();
            case 3:
                return pick.getRound();
            case 4:
                return new Integer(0);

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
                 return Integer.class;
             case 4:
                 return Integer.class;
             
             }
             return null;
      }
   
    
}
