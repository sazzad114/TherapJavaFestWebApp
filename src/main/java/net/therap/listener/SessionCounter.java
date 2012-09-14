package net.therap.listener;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: sazzadur
 * @since: 9/2/12 3:45 PM
 */

import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.Name;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@Name("sessionCounter")
public class SessionCounter implements HttpSessionListener {

	private static int activeSessions = 0;

	public void sessionCreated(HttpSessionEvent se) {
		activeSessions++;
	}

	public void sessionDestroyed(HttpSessionEvent se) {
		if(activeSessions > 0)
			activeSessions--;
	}
    @Factory("activeSessions")
	public static int getActiveSessions() {
		return activeSessions;
	}
}