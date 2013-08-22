/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cdiddy.objects.util;

import cdiddy.objects.Player;
import cdiddy.objects.Team;
import cdiddy.objects.draft.DraftPick;
import cdiddy.objects.draft.DraftResults;
import cdiddy.objects.draft.EnhancedDraftPick;
import cdiddy.objects.draft.EnhancedDraftResults;
import cdiddy.services.rest.PlayersRESTService;
import cdiddy.utils.application.PlayerService;
import cdiddy.utils.application.TeamService;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author cedric
 */

@Repository("draftUtil")
public class DraftUtil
{
    
    @Autowired
    private PlayerService playerService;
    @Autowired
    private TeamService teamservice;
    
    public EnhancedDraftResults createEnhancedDraftResults(DraftResults draft, String leagueid)
    {
        List<EnhancedDraftPick> enhancedPicks = new LinkedList<EnhancedDraftPick>();
        List<DraftPick> picks = draft.getDraft_result();
        List<Team> listTeam = teamservice.loadLeaugeTeams(leagueid);
        HashMap<String, Team> mapTeam = new HashMap<String, Team>();
        for (Team t : listTeam)
        {
            mapTeam.put(t.getTeam_key(), t);
        }
        for(DraftPick pick : picks)
        {
            EnhancedDraftPick tempEnhancedPick = new EnhancedDraftPick();
            tempEnhancedPick.setRound(Integer.parseInt(pick.getRound()));
            tempEnhancedPick.setPick(Integer.parseInt(pick.getPick()));
            String[] temp = StringUtils.split(pick.getPlayer_key(), ".");
            Player tempPlayer = playerService.retrivePlayer(Integer.parseInt(temp[2]));
            tempEnhancedPick.setPlayer(tempPlayer);
            tempEnhancedPick.setTeam(mapTeam.get(pick.getTeam_key()));
            enhancedPicks.add(tempEnhancedPick);
                   
        }
        EnhancedDraftResults result = new EnhancedDraftResults();
        result.setPicks(enhancedPicks);
        return result;
        
    }
    
}
