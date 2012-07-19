package net.therap.domain;

import org.hibernate.annotations.Type;

import javax.persistence.*;

/**
 * Created by IntelliJ IDEA.
 * User: sazzadur
 * Date: 7/19/12
 * Time: 3:56 PM
 */

@Entity
@Table(name = "ANSWER_INFO")
public class AnswerInfo {

    private long answerInfoId;
    private Contestant contestant;
    private long questionId;
    private boolean correct;
    private long version;

    @Id
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

    @Version
    @Column(name = "VERSION")
    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }
}
