package net.therap.util;

import net.therap.dao.ContestantDao;
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
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: sazzadur
 * Date: 7/22/12
 * Time: 3:46 PM
 */
@Name("factory")
public class FactoryUtil {

    private static final String QUESTIONBANK_XML = "/WEB-INF/classes/questionbank.xml";

    @In(required = false)
    private Contestant loggedInContestant;

    @In(create = true)
    private ContestantDao contestantDao;

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
        FileReader fileReader = new FileReader(ServletLifecycle.getServletContext().getRealPath(QUESTIONBANK_XML));
        return (QuestionBank) unmarshaller.unmarshal(fileReader);

    }

    @Factory(value = "notFirstLoad", scope = ScopeType.CONVERSATION)
    public Boolean getFirstLoad() {
        return false;
    }

    @Factory(value = "existingProposal", scope = ScopeType.PAGE)
    public ProjectProposal getProjectProposal() {
        return loggedInContestant.getMyGroup().getProjectProposal();
    }

    @Factory(value = "existingProjectSubmission", scope = ScopeType.PAGE)
    public ProjectSubmission getExistingProjectSubmission() {
        return loggedInContestant.getMyGroup().getSubmittedProject();
    }

    @Factory(value = "myProfile", scope = ScopeType.EVENT)
    public Contestant getMyProfile() {
        return contestantDao.getContestantById(loggedInContestant.getContestantId());
    }

    @Factory(value = "editableContestant", scope = ScopeType.PAGE)
    public Contestant getEditableContestant() {
        return contestantDao.getContestantById(loggedInContestant.getContestantId());
    }

}
