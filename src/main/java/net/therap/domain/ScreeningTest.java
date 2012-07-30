package net.therap.domain;

import org.hibernate.annotations.CollectionOfElements;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: sazzadur
 * Date: 7/19/12
 * Time: 4:17 PM
 */
@Entity
@Table(name = "SCREENING_TEST")
public class ScreeningTest {

    private long screeningTestId;
    private Contestant contestant;
    private Date startingTime;
    private Date endingTime;
    private List<Integer> questionOrder;
    private int currentQuestion;
    private long version;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "SCREENING_TEST_ID")
    public long getScreeningTestId() {
        return screeningTestId;
    }

    public void setScreeningTestId(long screeningTestId) {
        this.screeningTestId = screeningTestId;
    }

    @Column(name = "STARTING_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getStartingTime() {
        return startingTime;
    }

    public void setStartingTime(Date startingTime) {
        this.startingTime = startingTime;
    }

    @Column(name = "ENDING_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getEndingTime() {
        return endingTime;
    }

    public void setEndingTime(Date endingTime) {
        this.endingTime = endingTime;
    }

    @OneToOne(mappedBy = "screeningTest")
    public Contestant getContestant() {
        return contestant;
    }

    public void setContestant(Contestant contestant) {
        this.contestant = contestant;
    }

    @CollectionOfElements
    @JoinTable(name = "QUESTION_ORDER", joinColumns = @JoinColumn(name = "SCREENING_TEST_ID"))
    @Column(name = "Q_ORDER")
    public List<Integer> getQuestionOrder() {
        return questionOrder;
    }

    public void setQuestionOrder(List<Integer> questionOrder) {
        this.questionOrder = questionOrder;
    }

    @Column(name = "CURRENT_QUESTION")
    public int getCurrentQuestion() {
        return currentQuestion;
    }

    public void setCurrentQuestion(int currentQuestion) {
        this.currentQuestion = currentQuestion;
    }

    @Version
    @Column(name = "VERSION")
    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }
}
