package net.therap.action;

import net.therap.dao.ContestantDao;
import net.therap.domain.ContestantPerUniversityCount;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import java.util.List;
import java.util.Map;

/**
 * Created by
 *
 * @author: tahmid
 * @since: 9/2/12 4:56 PM
 */
@Name("statisticsAction")
@Scope(ScopeType.EVENT)
public class StatisticsAction {

    @In(create = true)
    private ContestantDao contestantDao;

    private List<ContestantPerUniversityCount> contestantsGroupedByUniversity;


    public List<ContestantPerUniversityCount> getContestantsGroupedByUniversity() {
        contestantsGroupedByUniversity = contestantDao.getListOfContestantsGroupedByUniversity();
        return contestantsGroupedByUniversity;
    }


}
