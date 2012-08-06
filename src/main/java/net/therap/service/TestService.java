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
import org.jboss.seam.log.Log;

import java.util.Date;

/**
 * Created by
 * User: tahmid
 * Date: 7/30/12
 * Time: 10:46 AM
 */

@Name("testService")
@Scope(ScopeType.CONVERSATION)
public class TestService {

    @Logger
    Log log;

    @In(create = true)
    private QuestionBank questionBank;

    @In
    @Out
    private Contestant loggedInContestant;

    @In(create = true)
    private AnswerInfoDao answerInfoDao;

    @In(create = true)
    private ContestantDao contestantDao;

    @In(create = true)
    private ScreeningTestDao screeningTestDao;

    @In(create = true)
    @Out
    private Boolean notFirstLoad;


    @Out
    private Question currentQuestion;


    private long timeLeft;

    private Integer selectedOptionId;

    public int getSelectedOptionId() {
        return selectedOptionId;
    }

    public void setSelectedOptionId(int selectedOptionId) {
        this.selectedOptionId = selectedOptionId;
    }

    public long getTimeLeft() {
        return timeLeft;
    }

    @Begin(join = true)
    public void loadFirstQuestion() {
        log.info("Invoking loadFirstQuestion");
        selectedOptionId = -1;
        ScreeningTest screeningTest = screeningTestDao.getScreeningTestForContestant(loggedInContestant);
        for (int i= 0; i<screeningTest.getQuestionOrderList().size();i++) {
            log.info("Question Id: " + screeningTest.getQuestionOrderList().get(i).getQuestionOrder());
        }
        Question question = questionBank.getQuestions().get(screeningTest.getCurrentQuestionState().getCurrentQuestionId());
        Date currentDateTime = new Date();
        long timeElapsed = currentDateTime.getTime() - screeningTest.getCurrentQuestionState().getLastLoadingTime().getTime();

        timeElapsed = timeElapsed / 1000;
        log.info("value of time elapsed" + timeElapsed);

        log.info("get current question");
        screeningTest.getCurrentQuestionState().setLastLoadingTime(currentDateTime);
        timeLeft = screeningTest.getCurrentQuestionState().getTimeLeft() - timeElapsed;
        screeningTest.getCurrentQuestionState().setTimeLeft(timeLeft);
        log.info("Setting current question to: " + question.getQuestionId());
        currentQuestion = question;
        loggedInContestant.setScreeningTest(screeningTest);
        screeningTestDao.updateScreeningTest(screeningTest);
        notFirstLoad = true;

    }


    public void loadCurrentQuestion() {
        log.info("Invoking loadCurrentQuestion");


        ScreeningTest screeningTest = loggedInContestant.getScreeningTest();
        Question question = questionBank.getQuestions().get(screeningTest.getCurrentQuestionState().getCurrentQuestionId());
        Date currentDateTime = new Date();
        long timeElapsed = currentDateTime.getTime() - screeningTest.getCurrentQuestionState().getLastLoadingTime().getTime();

        timeElapsed = timeElapsed / 1000;
        log.info("value of time elapsed" + timeElapsed);
        log.info("value of time left" + timeLeft);
        log.info("value of question's time" + screeningTest.getCurrentQuestionState().getTimeLeft());
        log.info("Selected Option Id: " + selectedOptionId);

        if (timeElapsed + 2 >= screeningTest.getCurrentQuestionState().getTimeLeft() || selectedOptionId != -1) {
            log.info("into next question");
            if (selectedOptionId != -1) {
                log.info("came from postback");
            }
            getNextQuestion();
            selectedOptionId = -1;

        } else {
            log.info("get current question");
            screeningTest.getCurrentQuestionState().setLastLoadingTime(currentDateTime);
            timeLeft = screeningTest.getCurrentQuestionState().getTimeLeft() - timeElapsed;
            screeningTest.getCurrentQuestionState().setTimeLeft(timeLeft);
            log.info("Setting current question to: " + question.getQuestionId());
            currentQuestion = question;
            screeningTestDao.updateScreeningTest(screeningTest);
            selectedOptionId = -1;
        }


    }

    @Begin(join = true)
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

        for (int i = 0; i < screeningTest.getQuestionOrderList().size(); i++) {
            if (currentQuestion.getQuestionId() == screeningTest.getQuestionOrderList().get(i).getQuestionOrder()) {
                currentIndex = i + 1;
                break;
            }
        }


        if (currentIndex == screeningTest.getQuestionOrderList().size() - 1) {
            loggedInContestant = contestantDao.getContestantById(loggedInContestant.getContestantId());
            loggedInContestant.setState(ContestantState.PENDING_TEST_RESULT);
            contestantDao.updateContestant(loggedInContestant);
            endTest();
            Redirect redirect = Redirect.instance();
            redirect.setViewId("/greetings/greeting.xhtml");
            redirect.execute();
        } else {

            log.info("Setting current question to: " + questionBank.getQuestions().get(screeningTest.getQuestionOrderList().get(currentIndex).getQuestionOrder()).getQuestionId());
            currentQuestion = questionBank.getQuestions().get(screeningTest.getQuestionOrderList().get(currentIndex).getQuestionOrder());
            selectedOptionId = -1;

            screeningTest.getCurrentQuestionState().setCurrentQuestionId(currentQuestion.getQuestionId());
            screeningTest.getCurrentQuestionState().setLastLoadingTime(new Date());
            timeLeft = currentQuestion.getAllottedTime();
            screeningTest.getCurrentQuestionState().setTimeLeft(timeLeft);
            screeningTestDao.updateScreeningTest(screeningTest);
        }


    }

    public void postAnswer() {
        getNextQuestion();

        Redirect redirect = Redirect.instance();
        redirect.setViewId("/screeningTest.xhtml");
        redirect.execute();
    }

    @End
    public void endTest() {

    }


}
