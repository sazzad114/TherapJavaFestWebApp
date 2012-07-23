package net.therap.component;

/**
 * Created by
 * User: tahmid
 * Date: 7/23/12
 * Time: 1:38 PM
 */
public class StringGenerator {

    private int charsToPrint;
    private String elegibleChars = "ABDEFGHJKLMRSTUVWXYabdefhjkmnrstuvwxy23456789";
    private char[] chars = elegibleChars.toCharArray();

    public StringGenerator(int charsToPrint) {
        this.charsToPrint = charsToPrint;
    }

    public String createString() {
        StringBuffer finalString = new StringBuffer();
        for (int i = 0; i < charsToPrint; i++) {
            double randomValue = Math.random();
            int randomIndex = (int) Math.round(randomValue * (chars.length - 1));
            char characterToShow = chars[randomIndex];
            finalString.append(characterToShow);
        }
        return finalString.toString();
    }

}
