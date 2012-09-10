/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cdiddy.gui;

import cdiddy.objects.Player;
import cdiddy.utils.application.PlayerService;
import cdiddy.utils.application.StatsService;

/**
 *
 * @author cedric
 */
public class PlayerInfoPanel extends javax.swing.JPanel {
private PlayerService playerUtil;
private StatsService statsService;
    /**
     * Creates new form PlayerInfoPanel
     */
    public PlayerInfoPanel() {
        initComponents();
        jTextArea1.setVisible(true);
    }
    public PlayerInfoPanel(PlayerService playerUtil, StatsService statsService) 
    {
        this.playerUtil = playerUtil;
        this.statsService = statsService; 
        initComponents();
        jTextArea1.setVisible(true);
    }
    public void populatePanel(Player tempPlayer)
    {
      jTextArea1.setText( playerUtil.getStatsCategories() + "\n" + playerUtil.getPlayerStats(tempPlayer));
      //System.out.println(playerUtil.getStatsCategories());
      statsService.retrieveStatCategories();
      jTextArea1.setVisible(true);
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
        jTextArea1 = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();

        jScrollPane1.setOpaque(false);
        jScrollPane1.setViewport(null);

        jTextArea1.setColumns(20);
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jLabel1.setText("YES!");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 663, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(320, 320, 320)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
