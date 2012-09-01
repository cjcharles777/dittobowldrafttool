/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cdiddy.objects.dao;

import cdiddy.objects.OAuthToken;
import cdiddy.objects.Player;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author DMDD
 */
@Repository("OAuthDAO")
@Transactional
public interface OAuthDAO 
{
    public void savePlayer(OAuthToken oat);
    public OAuthToken getOAuthTokenById(int id);
    public void deleteOAuthToken(OAuthToken oat);
    public List<OAuthToken> getAllOAuth();
}
