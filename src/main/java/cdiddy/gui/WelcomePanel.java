/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cdiddy.gui;

import cdiddy.objects.draft.EnhancedDraftResults;
import cdiddy.objects.league.YahooLeague;
import cdiddy.objects.util.DraftUtil;
import cdiddy.services.rest.PlayersRESTService;
import cdiddy.utils.application.GameService;
import cdiddy.utils.application.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author DMDD
 */
@Repository("welcomePanel")
public class WelcomePanel extends javax.swing.JPanel {
    @Autowired 
    private TeamService teamservice;
    @Autowired 
    private PlayersRESTService playersRESTService;
    @Autowired 
    private GameService gameService;
    @Autowired
    private UserTeamListPanel userTeamListPanel;
    @Autowired
    private LeaugeTeamListPanel leaugeTeamListPanel;
private int week = 1;
private YahooLeague yl;
    /**
     * Creates new form WelcomePanel
     */
    public WelcomePanel() {
       // initComponents();
    }

     /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = userTeamListPanel;
        jPanel2 = leaugeTeamListPanel;
        rosterPanel = new TeamRosterPanel(teamservice, playersRESTService, gameService, week);
        jPanel3 = new DraftResultsPanel();

        jTabbedPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTabbedPane1.setDoubleBuffered(true);
        jTabbedPane1.addTab("My Teams", jPanel1);
        jTabbedPane1.addTab("League", jPanel2);
        jTabbedPane1.addTab("Roster", rosterPanel);
        jTabbedPane1.addTab("Draft", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jTabbedPane1))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel rosterPanel;
    // End of variables declaration//GEN-END:variables

    public void init() 
    {

        initComponents();
        
    }
    public void populatePanel()
    {
        userTeamListPanel.init();
        leaugeTeamListPanel.init();
    }
    public void loadTableForLeauge(String leaugeid) {
       
//       EnhancedDraftResults edResults = new DraftUtil().createEnhancedDraftResults(gameService.getDraftResults(leaugeid), leaugeid);
       //((DraftResultsPanel) jPanel3).populateDraft(edResults);
      ((LeaugeTeamListPanel) jPanel2).populateLeauge(leaugeid);
       jTabbedPane1.setSelectedComponent(jPanel2); 
   }

    public void loadTableForRoster(String teamId, String leagueId) 
    {
//       EnhancedDraftResults edResults = new DraftUtil().createEnhancedDraftResults(gameService.getDraftResults(leagueId), leagueId);
   //    ((DraftResultsPanel) jPanel3).populateDraft(edResults);
        ((TeamRosterPanel) rosterPanel).populateRoster(teamId, leagueId);
       jTabbedPane1.setSelectedComponent(rosterPanel); 
    }
}
