/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cdiddy.objects.dao;

import cdiddy.objects.PlayerPic;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author cedric
 */
@Repository("PlayerPicDAO")
public interface PlayerPicDAO 
{
    public void savePlayerPic(PlayerPic pp);
    public void savePlayerPics(List<PlayerPic> listPP);
    public List<PlayerPic> getPlayerPics();
    public PlayerPic gePlayerPicById(int PlayerPicId);
    public void deletePlayerPic(PlayerPic pp);
    public void clearPlayerPics();
    
}
