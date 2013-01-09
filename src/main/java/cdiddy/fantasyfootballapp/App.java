package cdiddy.fantasyfootballapp;


import cdiddy.gui.FantasyFootballFrame;
import cdiddy.utils.application.PlayerService;
import cdiddy.utils.application.StatsService;
import cdiddy.utils.system.OAuthConnection;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
      private static final ApplicationContext applicationContext = new ClassPathXmlApplicationContext("app-config.xml");
        private static OAuthConnection conn = applicationContext.getBean(OAuthConnection.class);
        private static PlayerService playerUtil = applicationContext.getBean(PlayerService.class);
       private static StatsService statsService = applicationContext.getBean(StatsService.class);
       private static FantasyFootballFrame fantasyFootballFrame = applicationContext.getBean(FantasyFootballFrame.class);
    
       
       public static void main( String[] args )
    {
        if(args.length > 0 && args[0].equals("load"))
        {
            //loadFreshData();
        }
        else if(args.length > 0 && args[0].equals("loadWeek") && args[1] !=null)
        {
            loadWeekData(Integer.parseInt(args[1]));
        }
        else
        {
                try 
                {
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
             java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                fantasyFootballFrame.init();
                fantasyFootballFrame.setVisible(true);
            }
        });
        }
         
    }
    public static void loadFreshData()
    {
        conn.connect();
        playerUtil.primePlayersDatabase();
        statsService.primeStatCategories();
        statsService.primeStats();
        playerUtil.loadPlayers();
        statsService.loadStatCategories();
    }
        public static void loadWeekData(int week)
    {
        conn.connect();
        playerUtil.yahooWeeklyStatsLoad(week);
    }
}
