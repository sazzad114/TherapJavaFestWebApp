package net.therap.domain.question;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

/**
 * Created by
 * User: tahmid
 * Date: 7/17/12
 * Time: 11:28 AM
 */
@XmlRootElement(name = "question")
@XmlType(propOrder = {"questionId", "questionString", "options","correctOption","allottedTime"})
public class Question {

    private int questionId;


    private List<Option> options;
    private String questionString;
    private Option correctOption;
    private long allottedTime;

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    @XmlElementWrapper(name = "options")
    @XmlElement(name = "option")
    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    public String getQuestionString() {
        return questionString;
    }

    public void setQuestionString(String questionString) {
        this.questionString = questionString;
    }

    @XmlElement(name = "correctOption")
    public Option getCorrectOption() {
        return correctOption;
    }

    public void setCorrectOption(Option correctOption) {
        this.correctOption = correctOption;
    }

    public long getAllottedTime() {
        return allottedTime;
    }

    public void setAllottedTime(long allottedTime) {
        this.allottedTime = allottedTime;
    }
}
