package net.therap.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by
 * User: tahmid
 * Date: 8/1/12
 * Time: 3:06 PM
 */
@Entity
@Table(name = "QUESTION_ORDER")
public class QuestionOrder  implements Serializable {

    private long questionOrderId;
    private int questionOrder;
    private ScreeningTest screeningTest;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="QUESTION_ORDER_ID")
    public long getQuestionOrderId() {
        return questionOrderId;
    }

    public void setQuestionOrderId(long questionOrderId) {
        this.questionOrderId = questionOrderId;
    }

    @Column(name = "QUESTION_ORDER")
    public int getQuestionOrder() {
        return questionOrder;
    }

    public void setQuestionOrder(int questionOrder) {
        this.questionOrder = questionOrder;
    }

    @ManyToOne
    @JoinColumn(name = "SCREENING_TEST_ID")
    public ScreeningTest getScreeningTest() {
        return screeningTest;
    }

    public void setScreeningTest(ScreeningTest screeningTest) {
        this.screeningTest = screeningTest;
    }
}
