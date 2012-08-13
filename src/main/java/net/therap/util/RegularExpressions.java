package net.therap.util;

/**
 * Created by
 * User: tahmid
 * Date: 7/23/12
 * Time: 1:12 PM
 */
public interface RegularExpressions {

    public final String LINKED_IN = "[^\n]+(linkedin.com)[^\n]+|()";

    public final String LANGUAGE_PROFICIENCY = "[a-zA-z+#]+(\\,[ a-zA-z+#]+)*";

    public final String YOUTUBE_URL = "https?:\\/\\/(?:[0-9A-Z-]+\\.)?(?:youtu\\.be\\/|youtube\\.com\\S*[^\\w\\-\\s])([\\w\\-]{11})(?=[^\\w\\-]|$)(?![?=&+%\\w]*(?:['\"][^<>]*>|<\\/a>))[?=&+%\\w]*";

    public final String GITHUB_URL = "[^\n]+(github.com)[^\n]+|()";
}
