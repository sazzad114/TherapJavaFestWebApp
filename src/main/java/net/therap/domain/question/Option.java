package net.therap.domain.question;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by
 * User: tahmid
 * Date: 7/17/12
 * Time: 11:23 AM
 */

@XmlRootElement(name = "option")
public class Option {

    private int optionId;
    private String optionString;

    public int getOptionId() {
        return optionId;
    }

    public void setOptionId(int optionId) {
        this.optionId = optionId;
    }

    public String getOptionString() {
        return optionString;
    }

    public void setOptionString(String optionString) {
        this.optionString = optionString;
    }
}
