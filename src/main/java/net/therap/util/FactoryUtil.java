package net.therap.util;

import net.therap.dao.ContestantDao;
import net.therap.dao.ProjectProposalDao;
import net.therap.dao.ProjectSubmissionDao;
import net.therap.domain.Contestant;
import net.therap.domain.ProjectProposal;
import net.therap.domain.ProjectSubmission;
import net.therap.domain.University;
import net.therap.domain.question.QuestionBank;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.contexts.ServletLifecycle;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: sazzadur
 * Date: 7/22/12
 * Time: 3:46 PM
 */
@Name("factory")
public class FactoryUtil implements Serializable {

    private static final String QUESTIONBANK_XML = "/WEB-INF/classes/questionbank.xml";

    @In(required = false)
    private Contestant loggedInContestant;

    @In(create = true)
    private ContestantDao contestantDao;

    @In(create = true)
    private ProjectProposalDao projectProposalDao;

    @In(create = true)
    private ProjectSubmissionDao projectSubmissionDao;

    @Factory("universities")
    public List<University> getUniversities() {
        return Arrays.asList(University.values());
    }

    @Factory("Questiontimemer")
    public long getTimes() {
        return 10;
    }


    @Factory(value = "questionBank", scope = ScopeType.APPLICATION)
    public QuestionBank getQuestionBank() throws Exception {

        JAXBContext questionBankContext = JAXBContext.newInstance(QuestionBank.class);
        Unmarshaller unmarshaller = questionBankContext.createUnmarshaller();
        InputStream inputStream = new FileInputStream(ServletLifecycle.getServletContext().getRealPath(QUESTIONBANK_XML));
        return (QuestionBank) unmarshaller.unmarshal(inputStream);

    }

    @Factory(value = "notFirstLoad", scope = ScopeType.CONVERSATION)
    public Boolean getFirstLoad() {
        return false;
    }

    @Factory(value = "existingProposal", scope = ScopeType.PAGE)
    public ProjectProposal getProjectProposal() {
        ProjectProposal projectProposal =  projectProposalDao.getProjectProposalByContestant(loggedInContestant);
        loggedInContestant.getMyGroup().setProjectProposal(projectProposal);
        return projectProposal;
    }

    @Factory(value = "existingProjectSubmission", scope = ScopeType.PAGE)
    public ProjectSubmission getExistingProjectSubmission() {
        ProjectSubmission projectSubmission = projectSubmissionDao.getProjectSubmissionByContestant(loggedInContestant);
        loggedInContestant.getMyGroup().setSubmittedProject(projectSubmission);
        return projectSubmission;
    }

    @Factory(value = "myProfile", scope = ScopeType.EVENT)
    public Contestant getMyProfile() {
        return contestantDao.getContestantById(loggedInContestant.getContestantId());
    }

    @Factory(value = "editableContestant", scope = ScopeType.PAGE)
    public Contestant getEditableContestant() {
        return contestantDao.getContestantById(loggedInContestant.getContestantId());
    }

    @Factory(value = "loginFailed",scope = ScopeType.CONVERSATION)
    public Boolean loginFailed(){
        return false;
    }

    @Factory(value = "passwordResettingContestant", scope = ScopeType.PAGE)
    public Contestant getPassWordResettingContestant() {
        return new Contestant();
    }

    @Factory(value = "contestantProposalInfo", scope = ScopeType.PAGE)
    public ProjectProposal getContestantProposalInfo() {
          return projectProposalDao.getProjectProposalByContestant(loggedInContestant);
    }

     @Factory(value = "contestantProjectSubmissionInfo", scope = ScopeType.PAGE)
    public ProjectSubmission getSubmittedProjectSourceInfo() {
          return projectSubmissionDao.getProjectSubmissionByContestant(loggedInContestant);
    }

}
