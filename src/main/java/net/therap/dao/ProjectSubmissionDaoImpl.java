package net.therap.dao;

import net.therap.domain.Contestant;
import net.therap.domain.ProjectSubmission;
import org.hibernate.Query;
import org.hibernate.Session;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import java.io.Serializable;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: sazzadur
 * @since: 8/7/12 1:42 PM
 */
@Name("projectSubmissionDao")
@Scope(ScopeType.EVENT)

public class ProjectSubmissionDaoImpl implements ProjectSubmissionDao, Serializable {

    @In
    Session session;

    public void saveProjectSubmission(ProjectSubmission projectSubmission) {
        session.save(projectSubmission);
    }

    public void updateProjectSubmission(ProjectSubmission projectSubmission) {
        session.update(projectSubmission);
    }

    public ProjectSubmission getProjectSubmissionByContestant(Contestant contestant) {
        Query query = session.createQuery("from ProjectSubmission projectSubmission where projectSubmission.submittingGroup.groupId = :groupId");
        query.setLong("groupId", contestant.getMyGroup().getGroupId());
        List<ProjectSubmission> projectSubmissionList = (List<ProjectSubmission>) query.list();

        if (projectSubmissionList.size() != 0) {
            return projectSubmissionList.get(0);
        }

        return null;

    }
}
