package net.therap.domain;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: sazzadur
 * Date: 7/19/12
 * Time: 3:56 PM
 */

@Entity
@Table(name = "ANSWER_INFO")
public class AnswerInfo implements Serializable {

    private long answerInfoId;
    private Contestant contestant;
    private long questionId;
    private boolean correct;
    private int selectedOptionId;
    private Date timeOfAnswer;
    private long version;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ANSWER_INFO_ID")
    public long getAnswerInfoId() {
        return answerInfoId;
    }

    public void setAnswerInfoId(long answerInfoId) {
        this.answerInfoId = answerInfoId;
    }

    @ManyToOne
    @JoinColumn(name = "CONTESTANT_ID")
    public Contestant getContestant() {
        return contestant;
    }

    public void setContestant(Contestant contestant) {
        this.contestant = contestant;
    }

    @Column(name = "QUESTION_ID")
    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    @Column(name = "CORRECT")
    @Type(type = "true_false")
    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    @Column(name = "SELECTED_OPTION_ID")
    public int getSelectedOptionId() {
        return selectedOptionId;
    }

    public void setSelectedOptionId(int selectedOptionId) {
        this.selectedOptionId = selectedOptionId;
    }

    @Column(name = "TIME_OF_ANSWER")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getTimeOfAnswer() {
        return timeOfAnswer;
    }

    public void setTimeOfAnswer(Date timeOfAnswer) {
        this.timeOfAnswer = timeOfAnswer;
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
