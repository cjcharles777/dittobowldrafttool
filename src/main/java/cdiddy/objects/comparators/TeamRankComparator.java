/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cdiddy.objects.comparators;

import cdiddy.objects.Team;
import java.util.Comparator;

/**
 *
 * @author cedric
 */
public class TeamRankComparator implements Comparator<Team> 
{

    public int compare(Team o1, Team o2) 
    {
         return Integer.parseInt(o1.getStandings().getRank()) - Integer.parseInt(o2.getStandings().getRank());

    }

    public boolean equals(Object obj) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
