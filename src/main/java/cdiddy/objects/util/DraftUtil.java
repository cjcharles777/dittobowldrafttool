/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cdiddy.objects.util;

import cdiddy.objects.draft.DraftPick;
import cdiddy.objects.draft.DraftResults;
import cdiddy.objects.draft.EnhancedDraftPick;
import cdiddy.objects.draft.EnhancedDraftResults;
import java.util.List;

/**
 *
 * @author cedric
 */
public class DraftUtil 
{
    public EnhancedDraftResults createEnhancedDraftResults(DraftResults draft)
    {
        List<DraftPick> picks = draft.getDraft_result();
        
        for(DraftPick pick : picks)
        {
            EnhancedDraftPick tempEnhancedPick = new EnhancedDraftPick();
            tempEnhancedPick.setPick(Integer.parseInt(pick.getPick()));
            pick.getPlayer_key();
        }
    }
    
}
