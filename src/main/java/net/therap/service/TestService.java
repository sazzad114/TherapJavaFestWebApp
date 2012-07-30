package net.therap.service;

import net.therap.dao.AnswerInfoDao;
import net.therap.dao.ContestantDao;
import net.therap.dao.ScreeningTestDao;
import net.therap.domain.AnswerInfo;
import net.therap.domain.Contestant;
import net.therap.domain.ScreeningTest;
import net.therap.domain.question.Question;
import net.therap.domain.question.QuestionBank;
import net.therap.util.ContestantState;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.*;
import org.jboss.seam.faces.Redirect;

/**
 * Created by
 * User: tahmid
 * Date: 7/30/12
 * Time: 10:46 AM
 */

@Name("testService")
@Scope(ScopeType.CONVERSATION)
public class TestService {

    @In(create = true)
    QuestionBank questionBank;

    @In
    Contestant loggedInContestant;

    @In
    AnswerInfoDao answerInfoDao;

    @In
    ContestantDao contestantDao;

    @In
    ScreeningTestDao screeningTestDao;

    @Out
    Question currentQuestion;

    private Integer selectedOptionId;

    public int getSelectedOptionId() {
        return selectedOptionId;
    }

    public void setSelectedOptionId(int selectedOptionId) {
        this.selectedOptionId = selectedOptionId;
    }

    private static int index = 0;

    @Begin
    public void startTest() {

        ScreeningTest screeningTest = loggedInContestant.getScreeningTest();

        currentQuestion = questionBank.getQuestions().get(screeningTest.getCurrentQuestion());

        selectedOptionId = -1;

    }

    public void getNextQuestion() {

        AnswerInfo answerInfo = new AnswerInfo();

        answerInfo.setContestant(loggedInContestant);
        answerInfo.setQuestionId(currentQuestion.getQuestionId());
        if (currentQuestion.getCorrectOption().getOptionId() == selectedOptionId) {
            answerInfo.setCorrect(true);
        } else {
            answerInfo.setCorrect(false);
        }

        answerInfoDao.saveAnswerInfo(answerInfo);

        ScreeningTest screeningTest = loggedInContestant.getScreeningTest();

        int currentIndex = 0;

        for (int i = 0; i < screeningTest.getQuestionOrder().size(); i++) {
            if (currentQuestion.getQuestionId() == screeningTest.getQuestionOrder().get(i)) {
                currentIndex = i + 1;
                break;
            }
        }




        if (currentIndex == screeningTest.getQuestionOrder().size() - 1) {
            loggedInContestant.setState(ContestantState.PENDING_TEST_RESULT);
            contestantDao.updateContestant(loggedInContestant);
            Redirect redirect = Redirect.instance();
            redirect.setViewId("/greetings/greeting.xhtml");
            redirect.execute();
        }
        else {


            currentQuestion = questionBank.getQuestions().get(screeningTest.getQuestionOrder().get(currentIndex));
            selectedOptionId = -1;
            screeningTest.setCurrentQuestion(currentQuestion.getQuestionId());
            screeningTestDao.updateScreeningTest(screeningTest);
        }


    }

    @End
    public void endTest() {

    }


}
