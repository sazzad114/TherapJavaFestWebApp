package net.therap.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: sazzadur
 * Date: 7/31/12
 * Time: 11:45 AM
 */
@Embeddable
public class ScreeningTestStateInfo implements Serializable {

    private int currentQuestionId;
    private Date lastLoadingTime;
    private long timeLeft;

    @Column(name = "CURRENT_QUESTION_ID")
    public int getCurrentQuestionId() {
        return currentQuestionId;
    }

    public void setCurrentQuestionId(int currentQuestionId) {
        this.currentQuestionId = currentQuestionId;
    }

    @Column(name = "LAST_LOADING_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getLastLoadingTime() {
        return lastLoadingTime;
    }

    public void setLastLoadingTime(Date lastLoadingTime) {
        this.lastLoadingTime = lastLoadingTime;
    }

    @Column(name = "TIME_LEFT")
    public long getTimeLeft() {
        return timeLeft;
    }

    public void setTimeLeft(long timeLeft) {
        this.timeLeft = timeLeft;
    }
}
