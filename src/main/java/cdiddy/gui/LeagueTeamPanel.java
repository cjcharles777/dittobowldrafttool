/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cdiddy.gui;

import cdiddy.gui.FantasyFootballFrame;
import cdiddy.objects.Player;
import cdiddy.objects.Team;
import cdiddy.objects.TeamStandings;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;

/**
 *
 * @author cedric
 */
public class LeagueTeamPanel extends javax.swing.JPanel {

    Team team;
    /**
     * Creates new form UserTeamPanel
     */
    public LeagueTeamPanel() {
        initComponents();
    }

    public LeagueTeamPanel(Team t) 
    {
        initComponents();
        setTeam(t);        

        
    }
    public void setTeam(Team team) 
    {
        this.team = team;
        if(team != null)
        {
            TeamStandings.TeamOutcome outcome = team.getStandings().getOutcome_totals();
            numOfLosses.setText(outcome.getLosses());
            numOfTies.setText(outcome.getTies());
            numOfWins.setText(outcome.getWins());
            teamName.setText(team.getName());
            ranking.setText(team.getStandings().getRank());
            ImageIcon icon = createImageIcon(team.getTeamLogoUrl(),
                                             team.getName());

             teamPhoto.setIcon(icon);
        }
    }
    
    public Object getTeam() 
    {
        return this.team;
    }
    private ImageIcon createImageIcon(String url, String description) 
    {
        java.net.URL imgURL = null;
        try
        {
            imgURL = new URL(url);
        }
        catch(Exception e)
        {
         System.err.println("Couldn't find file: " + url);
        }
         
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            System.err.println("Couldn't find file: " + url);
            return null;
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

        teamPhoto = new javax.swing.JLabel();
        teamName = new javax.swing.JLabel();
        numOfWins = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        numOfLosses = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        numOfTies = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        ranking = new javax.swing.JLabel();

        teamName.setText("Team Name");
        teamName.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                teamNameMouseClicked(evt);
            }
        });

        numOfWins.setText("0");

        jLabel1.setText("Wins");

        numOfLosses.setText("0");

        jLabel3.setText("Losses");

        numOfTies.setText("0");

        jLabel5.setText("Ties");

        ranking.setText("0");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(teamPhoto, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(teamName, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(24, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(numOfWins, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(numOfLosses, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(numOfTies, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ranking, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(teamPhoto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(teamName, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(numOfWins)
                                    .addComponent(jLabel1)
                                    .addComponent(numOfLosses)
                                    .addComponent(jLabel3)
                                    .addComponent(numOfTies)
                                    .addComponent(jLabel5))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(ranking, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                                .addContainerGap())))))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void teamNameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_teamNameMouseClicked
          
       ( (FantasyFootballFrame) (SwingUtilities.getWindowAncestor(this))).prepareLeaugeInfo(team.getLeaugeid());
    }//GEN-LAST:event_teamNameMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel numOfLosses;
    private javax.swing.JLabel numOfTies;
    private javax.swing.JLabel numOfWins;
    private javax.swing.JLabel ranking;
    private javax.swing.JLabel teamName;
    private javax.swing.JLabel teamPhoto;
    // End of variables declaration//GEN-END:variables

 


}