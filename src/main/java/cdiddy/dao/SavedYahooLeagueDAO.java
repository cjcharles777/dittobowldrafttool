/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cdiddy.dao;

import cdiddy.objects.league.YahooLeague;
import java.util.List;

/**
 *
 * @author cedric
 */
public interface SavedYahooLeagueDAO 
{
    public void saveSavedYahooLeague(YahooLeague yl);
    public void saveSavedYahooLeague(List<YahooLeague> listYl);
    public List<YahooLeague> getAllSavedYahooLeagues();
    public YahooLeague getSavedYahooLeagueById(int weeklyStatId);
    public void deleteSavedYahooLeague(YahooLeague yl);
    public void clearSavedYahooLeagueTable();
}
