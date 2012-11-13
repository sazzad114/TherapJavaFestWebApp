package net.therap.action;

import net.therap.dao.ContestantDao;
import net.therap.dao.ProjectSubmissionDao;
import net.therap.domain.Contestant;
import net.therap.domain.ProjectSubmission;
import net.therap.util.ContestantState;
import net.therap.util.FileValidatorUtil;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.*;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: sazzadur
 * Date: 8/5/12
 * Time: 6:04 PM
 */

@Name("projectSubmissionAction")
@Scope(ScopeType.EVENT)
public class ProjectSubmissionAction implements Serializable {

    @Logger
    Log log;

    @In
    private FacesMessages facesMessages;

    @In
    @Out
    private Contestant loggedInContestant;

    @In(create = true)
    private ProjectSubmissionDao projectSubmissionDao;

    @In(create = true)
    private ContestantDao contestantDao;


    private final int UPLOADED_SOURCE_CODE_SIZE = 15;

    public String submitProjectSource(ProjectSubmission projectSubmission) {
        List<String> sourceCodeFileType = new ArrayList<String>();
        sourceCodeFileType.add("application/zip");
        sourceCodeFileType.add("application/x-zip");
        sourceCodeFileType.add("application/x-zip-compressed");
        sourceCodeFileType.add("application/octet-stream");
        sourceCodeFileType.add("application/x-compress");
        sourceCodeFileType.add("application/x-compressed");
        sourceCodeFileType.add("multipart/x-zip");



        int sourceCodeFileSize = UPLOADED_SOURCE_CODE_SIZE * 1024 * 1024;
        boolean validationFails = false;

        if (projectSubmission.getGitHubUrl().equals("") && projectSubmission.getUploadedSourceCode().getSize() == 0) {
            facesMessages.add("You should either upload your source code or provide a github url to your project source code");
            validationFails = true;
        }

        if (projectSubmission.getGitHubUrl().equals("") || projectSubmission.getUploadedSourceCode().getSize() != 0) {

            if (!FileValidatorUtil.validateFileSize(projectSubmission.getUploadedSourceCode(), sourceCodeFileSize)) {
                facesMessages.addToControl("sourceCodeUpload", "file size must not exceed " + UPLOADED_SOURCE_CODE_SIZE + "MB");
                validationFails = true;
            }
            if (!FileValidatorUtil.validateFileType(projectSubmission.getUploadedSourceCode(), sourceCodeFileType)) {
                facesMessages.addToControl("sourceCodeUpload", "only zipped archive files are allowed");
                validationFails = true;
            }
        }

        if (!validationFails) {

            if (loggedInContestant.getState() == ContestantState.AT_FINAL_SUBMISSION) {

                loggedInContestant = contestantDao.getContestantById(loggedInContestant.getContestantId());
                projectSubmission.setLastSubmissionTime(new Date());
                projectSubmission.setLastSubmittedBy(loggedInContestant);
                projectSubmission.setSubmittingGroup(loggedInContestant.getMyGroup());
                projectSubmission.setSourceCode(projectSubmission.getUploadedSourceCode().getFileData());
                loggedInContestant.getMyGroup().setSubmittedProject(projectSubmission);

                loggedInContestant.getMyGroup().getMembers().get(0).setState(ContestantState.PENDING_SUBMISSION_RESULT);
                loggedInContestant.getMyGroup().getMembers().get(1).setState(ContestantState.PENDING_SUBMISSION_RESULT);
                loggedInContestant.setState(ContestantState.PENDING_SUBMISSION_RESULT);

                projectSubmissionDao.saveProjectSubmission(projectSubmission);
                contestantDao.updateContestant(loggedInContestant);

                return "success";
            }


            return "failure";
        }

        return "failure";
    }


    public String updateProjectSource(ProjectSubmission projectSubmission) {
        List<String> sourceCodeFileType = new ArrayList<String>();
        sourceCodeFileType.add("application/zip");
        sourceCodeFileType.add("application/x-zip");
        sourceCodeFileType.add("application/x-zip-compressed");
        sourceCodeFileType.add("application/octet-stream");
        sourceCodeFileType.add("application/x-compress");
        sourceCodeFileType.add("application/x-compressed");
        sourceCodeFileType.add("multipart/x-zip");

        int sourceCodeFileSize = UPLOADED_SOURCE_CODE_SIZE * 1024 * 1024;
        boolean validationFails = false;

        if (projectSubmission.getGitHubUrl().equals("") && projectSubmission.getUploadedSourceCode().getSize() == 0) {
            facesMessages.add("You should either upload your source code or provide a github url to your project source code");
            validationFails = true;
        }

        if (projectSubmission.getGitHubUrl().equals("") || projectSubmission.getUploadedSourceCode().getSize() != 0) {

            if (!FileValidatorUtil.validateFileSize(projectSubmission.getUploadedSourceCode(), sourceCodeFileSize)) {
                facesMessages.addToControl("sourceCodeUpload", "file size must not exceed " + UPLOADED_SOURCE_CODE_SIZE + "MB");
                validationFails = true;
            }
            if (!FileValidatorUtil.validateFileType(projectSubmission.getUploadedSourceCode(), sourceCodeFileType)) {
                facesMessages.addToControl("sourceCodeUpload", "only zipped archive files are allowed");
                validationFails = true;
            }
        }

        if (!validationFails) {
            if (loggedInContestant.getState() == ContestantState.PENDING_SUBMISSION_RESULT) {

                projectSubmission.setLastSubmittedBy(loggedInContestant);
                projectSubmission.setLastSubmissionTime(new Date());
                projectSubmission.setSourceCode(projectSubmission.getUploadedSourceCode().getFileData());

                projectSubmissionDao.updateProjectSubmission(projectSubmission);
                return "success";
            }
            return "failure";

        }

        return "failure";
    }
}
