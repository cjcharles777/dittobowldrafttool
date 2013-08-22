/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cdiddy.gui;

import cdiddy.objects.Team;
import cdiddy.utils.application.TeamService;
import java.awt.Component;
import java.util.List;
import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author cedric
 */
@Repository("userTeamListPanel")
public class UserTeamListPanel extends javax.swing.JPanel {
    @Autowired
    private TeamService teamService;
    private  List<Team> listTeam;
    private TeamTableModel teamModel = new TeamTableModel();
    /**
     * Creates new form UserTeamListPanel
     */
    public UserTeamListPanel() 
    {
        initComponents();
       
    }
    
    private void populateTeamTable()
    {
        for(Team team : listTeam)
        {
            team = teamService.loadTeamStandings(team);
            teamModel.addRow(team);
        }
        
    }
    public void init()
    {
        initComponents();
        //listTeam = teamService.loadUserTeams();
        //populateTeamTable();
        
    }
    public void populatePanel()
    {
        listTeam = teamService.loadUserTeams();
        populateTeamTable();
    }
            

    
    public class TeamCellEditorRenderer extends AbstractCellEditor implements TableCellRenderer, TableCellEditor 
    {
         private static final long serialVersionUID = 1L;
        private UserTeamPanel renderer = new UserTeamPanel();
        private UserTeamPanel editor = new UserTeamPanel();

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            renderer.setTeam((Team) value);
            return renderer;
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            editor.setTeam((Team) value);
            return editor;
        }

        @Override
        public Object getCellEditorValue() {
            return editor.getTeam();
        }

    }
    public class TeamTableModel extends DefaultTableModel 
    {
        private static final long serialVersionUID = 1L;

           @Override
           public int getColumnCount() {
               return 1;
           }

           public void addRow(Team team) {
               super.addRow(new Object[]{team});
               //super.fireTableDataChanged();
           }
       }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setMaximumSize(new java.awt.Dimension(3276700, 3276700));
        setPreferredSize(new java.awt.Dimension(800, 400));

        jTable1.setModel(teamModel);
        jTable1.setFillsViewportHeight(true);
        jScrollPane1.setViewportView(jTable1);
        jTable1.setRowHeight(new UserTeamPanel().getPreferredSize().height);
        jTable1.setTableHeader(null);
        TeamCellEditorRenderer teamCellEditorRenderer = new TeamCellEditorRenderer();
        jTable1.setDefaultRenderer(Object.class, teamCellEditorRenderer);
        jTable1.setDefaultEditor(Object.class, teamCellEditorRenderer);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
