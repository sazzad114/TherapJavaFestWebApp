package net.therap.service;

import net.therap.dao.ContestantDao;
import net.therap.dao.GroupDao;
import net.therap.dao.ProjectProposalDao;
import net.therap.domain.Contestant;
import net.therap.domain.ProjectProposal;
import net.therap.util.ContestantState;
import net.therap.util.FileValidatorUtil;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.faces.FacesMessages;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: sazzadur
 * Date: 8/5/12
 * Time: 6:04 PM
 */
@Name("proposalSubmissionService")
@Scope(ScopeType.EVENT)
public class ProposalSubmissionService {

    private final int UPLOADED_PROPOSAL_SIZE = 5;

    @In
    private FacesMessages facesMessages;

    @In
    @Out
    private Contestant loggedInContestant;

    @In
    private ProjectProposalDao projectProposalDao;

    @In
    private ContestantDao contestantDao;

    @In
    private GroupDao groupDao;

    public String submitProposal(ProjectProposal projectProposal) {
        List<String> proposalFileType = new ArrayList<String>();
        proposalFileType.add("application/pdf");

        int proposalFileSize = UPLOADED_PROPOSAL_SIZE * 1024 * 1024;


        boolean validationFails = false;

        if (!FileValidatorUtil.validateFileSize(projectProposal.getUploadedProposal(), proposalFileSize)) {
            facesMessages.addToControl("proposalUpload", "file size must not exceed " + UPLOADED_PROPOSAL_SIZE + "MB");
            validationFails = true;
        }
        if (!FileValidatorUtil.validateFileType(projectProposal.getUploadedProposal(), proposalFileType)) {
            facesMessages.addToControl("proposalUpload", "only pdf files are allowed");
            validationFails = true;
        }

        if (!validationFails) {

            if (loggedInContestant.getState() == ContestantState.AT_BUSINESS_PROPOSAL) {
                loggedInContestant = contestantDao.getContestantById(loggedInContestant.getContestantId());
                projectProposal.setLastModifiedBy(loggedInContestant);
                projectProposal.setProposingGroup(loggedInContestant.getMyGroup());
                projectProposal.setLastModificationTime(new Date());
                projectProposal.setProposal(projectProposal.getUploadedProposal().getFileData());
                loggedInContestant.getMyGroup().setProjectProposal(projectProposal);

                loggedInContestant.getMyGroup().getMembers().get(0).setState(ContestantState.PENDING_PROPOSAL_RESULT);
                loggedInContestant.getMyGroup().getMembers().get(1).setState(ContestantState.PENDING_PROPOSAL_RESULT);
                loggedInContestant.setState(ContestantState.PENDING_PROPOSAL_RESULT);

                projectProposalDao.saveProjectProposal(projectProposal);
                contestantDao.updateContestant(loggedInContestant);

                return "success";
            }

            return "failure";
        }

        return "failure";
    }


    public String updateProposal(ProjectProposal existingProposal) {
        List<String> proposalFileType = new ArrayList<String>();
        proposalFileType.add("application/pdf");

        int proposalFileSize = UPLOADED_PROPOSAL_SIZE * 1024 * 1024;


        boolean validationFails = false;

        if (!FileValidatorUtil.validateFileSize(existingProposal.getUploadedProposal(), proposalFileSize)) {
            facesMessages.addToControl("proposalUpload", "file size must not exceed " + UPLOADED_PROPOSAL_SIZE + "MB");
            validationFails = true;
        }
        if (!FileValidatorUtil.validateFileType(existingProposal.getUploadedProposal(), proposalFileType)) {
            facesMessages.addToControl("proposalUpload", "only pdf files are allowed");
            validationFails = true;
        }

        if (!validationFails) {

            if (loggedInContestant.getState() == ContestantState.PENDING_PROPOSAL_RESULT) {

                existingProposal.setLastModifiedBy(loggedInContestant);
                existingProposal.setLastModificationTime(new Date());
                existingProposal.setProposal(existingProposal.getUploadedProposal().getFileData());

                projectProposalDao.updateProjectProposal(existingProposal);

                return "success";
            }

            return "failure";
        }

        return "failure";
    }

}
