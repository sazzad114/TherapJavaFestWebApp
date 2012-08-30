package net.therap.action;

import net.therap.dao.ContestantDao;
import net.therap.dao.ScreeningTestDao;
import net.therap.domain.Contestant;
import net.therap.domain.QuestionOrder;
import net.therap.domain.ScreeningTest;
import net.therap.domain.ScreeningTestStateInfo;
import net.therap.domain.question.Question;
import net.therap.domain.question.QuestionBank;
import net.therap.util.ContestantState;
import net.therap.util.OrderGenerator;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.*;
import org.jboss.seam.faces.Redirect;
import org.jboss.seam.log.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by
 * User: tahmid
 * Date: 7/30/12
 * Time: 1:14 PM
 */

@Name("questionGeneratorAction")
@Scope(ScopeType.EVENT)
public class QuestionGeneratorAction implements Serializable {

    @Logger
    Log log;

    @In(create = true)
    private OrderGenerator orderGenerator;

    @In
    private Contestant loggedInContestant;

    @In
    private ScreeningTestDao screeningTestDao;

    @In
    private ContestantDao contestantDao;

    @In(create = true)
    private QuestionBank questionBank;

     public void generateQuestion() {
        if (loggedInContestant.getState() == ContestantState.READY_FOR_TEST && loggedInContestant.getScreeningTest()==null) {
            ScreeningTest screeningTest = new ScreeningTest();
            screeningTest.setContestant(loggedInContestant);
            screeningTest.setStartingTime(new Date());

            List<Integer> generatedQuestionOrder = orderGenerator.generateOrder(questionBank.getQuestions().size(), 5); // order generation temporarily

            List<QuestionOrder> questionOrderList = new ArrayList<QuestionOrder>();

            for (int id : generatedQuestionOrder) {
                QuestionOrder questionOrder = new QuestionOrder();
                questionOrder.setQuestionOrder(id);
                questionOrder.setScreeningTest(screeningTest);
                questionOrderList.add(questionOrder);
            }
            screeningTest.setQuestionOrderList(questionOrderList);
            Question firstQuestion = questionBank.getQuestions().get(questionOrderList.get(0).getQuestionOrder());
            log.info("Setting first question to" + firstQuestion.getQuestionId());
            ScreeningTestStateInfo screeningTestStateInfo = new ScreeningTestStateInfo();
            screeningTestStateInfo.setCurrentQuestionId(firstQuestion.getQuestionId());
            screeningTestStateInfo.setLastLoadingTime(new Date());
            screeningTestStateInfo.setTimeLeft(firstQuestion.getAllottedTime());
            screeningTest.setCurrentQuestionState(screeningTestStateInfo);


            loggedInContestant.setScreeningTest(screeningTest);
            loggedInContestant.setState(ContestantState.AT_TEST);

            screeningTestDao.saveScreeningTest(screeningTest);
            contestantDao.updateContestant(loggedInContestant);
        }

        Redirect redirect = Redirect.instance();
        redirect.setViewId("/screeningTest.xhtml");
        redirect.execute();


    }
}
