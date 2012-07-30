package net.therap.util;

/**
 * Created by
 * User: tahmid
 * Date: 7/23/12
 * Time: 1:38 PM
 */
public class StringGeneratorUtil {


    private static String elegibleChars = "ABDEFGHJKLMRSTUVWXYabdefhjkmnrstuvwxy23456789";

    public static String generateString(int stringLength) {

        char[] chars = elegibleChars.toCharArray();
        StringBuffer finalString = new StringBuffer();

        for (int i = 0; i < stringLength; i++) {
            double randomValue = Math.random();
            int randomIndex = (int) Math.round(randomValue * (chars.length - 1));
            char characterToShow = chars[randomIndex];
            finalString.append(characterToShow);
        }

        return finalString.toString();
    }

}
