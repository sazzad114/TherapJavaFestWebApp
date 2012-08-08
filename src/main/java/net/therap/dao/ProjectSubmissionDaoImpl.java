package net.therap.dao;

import net.therap.domain.ProjectSubmission;
import org.hibernate.Session;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Startup;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: sazzadur
 * @since: 8/7/12 1:42 PM
 */
@Name("projectSubmissionDao")
@Scope(ScopeType.APPLICATION)
@Startup
public class ProjectSubmissionDaoImpl implements ProjectSubmissionDao {

    @In
    Session session;

    public void saveProjectSubmission(ProjectSubmission projectSubmission) {
        session.save(projectSubmission);
    }

    public void updateProjectSubmission(ProjectSubmission projectSubmission) {
        session.update(projectSubmission);
    }
}
