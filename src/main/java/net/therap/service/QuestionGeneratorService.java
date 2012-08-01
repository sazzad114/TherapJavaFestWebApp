package net.therap.service;

import net.therap.dao.ContestantDao;
import net.therap.dao.ScreeningTestDao;
import net.therap.domain.Contestant;
import net.therap.domain.ScreeningTest;
import net.therap.domain.ScreeningTestStateInfo;
import net.therap.domain.question.Question;
import net.therap.domain.question.QuestionBank;
import net.therap.util.ContestantState;
import net.therap.util.OrderGenerator;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.faces.Redirect;

import java.util.Date;
import java.util.List;

/**
 * Created by
 * User: tahmid
 * Date: 7/30/12
 * Time: 1:14 PM
 */

@Name("questionGeneratorService")
@Scope(ScopeType.EVENT)
public class QuestionGeneratorService {

    @In(create = true)
    OrderGenerator orderGenerator;

    @In
    Contestant loggedInContestant;

    @In(create = true)
    ScreeningTestDao screeningTestDao;

    @In(create = true)
    ContestantDao contestantDao;

    @In(create = true)
    QuestionBank questionBank;


    public void generateQuestion() {
        if (loggedInContestant.getState() == ContestantState.READY_FOR_TEST) {
            ScreeningTest screeningTest = new ScreeningTest();
            screeningTest.setContestant(loggedInContestant);
            screeningTest.setStartingTime(new Date());

            List<Integer> questionOrder = orderGenerator.generateOrder(questionBank.getQuestions().size(), 5); // order generation temporarily

            screeningTest.setQuestionOrder(questionOrder);
            Question firstQuestion = questionBank.getQuestions().get(questionOrder.get(0));

            ScreeningTestStateInfo screeningTestStateInfo = new ScreeningTestStateInfo();
            screeningTestStateInfo.setCurrentQuestionId(firstQuestion.getQuestionId());
            screeningTestStateInfo.setLastLoadingTime(new Date());
            screeningTestStateInfo.setTimeLeft(firstQuestion.getAllottedTime());
            screeningTest.setCurrentQuestionState(screeningTestStateInfo);

            loggedInContestant.setScreeningTest(screeningTest);
            loggedInContestant.setState(ContestantState.AT_TEST);

            screeningTestDao.saveScreeningTest(screeningTest);
            contestantDao.updateContestant(loggedInContestant);

            Redirect redirect = Redirect.instance();
            redirect.setViewId("/screeningTest.xhtml");
            redirect.execute();

        }


    }
}
