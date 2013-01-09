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
import cdiddy.utils.application.TeamService;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author cedric
 */
public class DraftUtil
{
    
    @Autowired
    private PlayersRESTService playersRESTService;
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
            mapTeam.put(t.getTeamKey(), t);
        }
        for(DraftPick pick : picks)
        {
            EnhancedDraftPick tempEnhancedPick = new EnhancedDraftPick();
            tempEnhancedPick.setRound(Integer.parseInt(pick.getRound()));
            tempEnhancedPick.setPick(Integer.parseInt(pick.getPick()));
            Player tempPlayer = playersRESTService.retrivePlayerWithYahooKey(pick.getPlayer_key());
            tempEnhancedPick.setPlayer(tempPlayer);
            tempEnhancedPick.setTeam(mapTeam.get(pick.getTeam_key()));
            enhancedPicks.add(tempEnhancedPick);
                   
        }
        EnhancedDraftResults result = new EnhancedDraftResults();
        result.setPicks(enhancedPicks);
        return result;
        
    }
    
}
