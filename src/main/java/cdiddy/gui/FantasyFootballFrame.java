/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cdiddy.gui;

import cdiddy.gui.table.model.PlayersTableModel;
import cdiddy.objects.Player;
import cdiddy.utils.application.PlayerUtil;
import cdiddy.utils.system.OAuthConnection;
import java.awt.CardLayout;
import java.util.List;
import javax.swing.JPanel;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author cedric
 */
public class FantasyFootballFrame extends javax.swing.JFrame {
      private static final ApplicationContext applicationContext = 
        new ClassPathXmlApplicationContext("app-config.xml");
        OAuthConnection conn = applicationContext.getBean(OAuthConnection.class);
        PlayerUtil playerUtil = applicationContext.getBean(PlayerUtil.class);
        Player playerInContext = null;
    /**
     * Creates new form FantasyFootballFrame
     */
    public FantasyFootballFrame() {
        initComponents();
;
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        jPanel1 = new javax.swing.JPanel();
        welcomePanel = new cdiddy.gui.WelcomePanel();
        playerPanel = new cdiddy.gui.PlayerPanel(playerUtil);
        playerInfoPanel = new PlayerInfoPanel(playerUtil);
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new java.awt.CardLayout());
        jPanel1.add(welcomePanel, "card3");
        jPanel1.add(playerPanel, "card2");

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jPanel1, org.jdesktop.beansbinding.ELProperty.create("${maximumSize}"), playerInfoPanel, org.jdesktop.beansbinding.BeanProperty.create("maximumSize"));
        bindingGroup.addBinding(binding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jPanel1, org.jdesktop.beansbinding.ELProperty.create("${minimumSize}"), playerInfoPanel, org.jdesktop.beansbinding.BeanProperty.create("minimumSize"));
        bindingGroup.addBinding(binding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jPanel1, org.jdesktop.beansbinding.ELProperty.create("${preferredSize}"), playerInfoPanel, org.jdesktop.beansbinding.BeanProperty.create("preferredSize"));
        bindingGroup.addBinding(binding);

        javax.swing.GroupLayout playerInfoPanelLayout = new javax.swing.GroupLayout(playerInfoPanel);
        playerInfoPanel.setLayout(playerInfoPanelLayout);
        playerInfoPanelLayout.setHorizontalGroup(
            playerInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 794, Short.MAX_VALUE)
        );
        playerInfoPanelLayout.setVerticalGroup(
            playerInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 337, Short.MAX_VALUE)
        );

        jPanel1.add(playerInfoPanel, "playerInfo");

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        jMenu3.setText("Players");

        jMenuItem1.setText("View All Players");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem1);

        jMenuItem2.setText("Player Info");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem2);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 69, Short.MAX_VALUE))
        );

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents
    public static void preparePlayerInfo(Player tempPlayer)
    {
        ((PlayerInfoPanel) playerInfoPanel).populatePanel(tempPlayer);
        CardLayout cl = (CardLayout)(jPanel1.getLayout());
        cl.show(jPanel1, "playerInfo");
    }
    
    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
   CardLayout cl = (CardLayout)(jPanel1.getLayout());
   cl.show(jPanel1, "card2");
        
        
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        CardLayout cl = (CardLayout)(jPanel1.getLayout());
        cl.show(jPanel1, "playerInfo");
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        OAuthConnection conn = applicationContext.getBean(OAuthConnection.class);
        PlayerUtil playerUtil = applicationContext.getBean(PlayerUtil.class);
        conn.connect();
        playerUtil.primePlayersDatabase();
        playerUtil.loadPlayers();
        
        
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FantasyFootballFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FantasyFootballFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FantasyFootballFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FantasyFootballFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FantasyFootballFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private static javax.swing.JPanel jPanel1;
    private static javax.swing.JPanel playerInfoPanel;
    private javax.swing.JPanel playerPanel;
    private javax.swing.JPanel welcomePanel;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
