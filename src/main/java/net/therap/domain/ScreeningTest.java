package net.therap.domain;

import javax.persistence.*;
import java.io.Serializable;
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
public class ScreeningTest implements Serializable {

    private long screeningTestId;
    private Contestant contestant;
    private Date startingTime;
    private Date endingTime;
    private List<QuestionOrder> questionOrderList;
    private ScreeningTestStateInfo currentQuestionState;
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

    @OneToMany(mappedBy = "screeningTest", cascade = CascadeType.ALL)
    @OrderBy("questionOrderId")
    public List<QuestionOrder> getQuestionOrderList() {
        return questionOrderList;
    }

    public void setQuestionOrderList(List<QuestionOrder> questionOrderList) {
        this.questionOrderList = questionOrderList;
    }

    @Embedded
    public ScreeningTestStateInfo getCurrentQuestionState() {
        return currentQuestionState;
    }

    public void setCurrentQuestionState(ScreeningTestStateInfo currentQuestionState) {
        this.currentQuestionState = currentQuestionState;
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
