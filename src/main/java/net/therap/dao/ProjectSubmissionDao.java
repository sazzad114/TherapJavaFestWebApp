package net.therap.dao;

import net.therap.domain.ProjectSubmission;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: sazzadur
 * @since: 8/7/12 1:42 PM
 */
public interface ProjectSubmissionDao {

    public void saveProjectSubmission(ProjectSubmission projectSubmission);

    public void updateProjectSubmission(ProjectSubmission projectSubmission);
}
