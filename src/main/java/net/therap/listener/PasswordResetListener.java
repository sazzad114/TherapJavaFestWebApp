package net.therap.listener;

import net.therap.action.EmailAction;
import net.therap.domain.Contestant;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.*;
import org.jboss.seam.contexts.Context;
import org.jboss.seam.contexts.Contexts;
import org.jboss.seam.faces.Redirect;
import org.jboss.seam.log.Log;

/**
 * Created by
 *
 * @author: tahmid
 * @since: 8/31/12 8:12 PM
 */
@Name("passwordResetListener")
@Scope(ScopeType.EVENT)
public class PasswordResetListener {

    @Logger
    private Log log;

    @In(create = true)
    private EmailAction emailAction;

    @Observer(value = "passwordResetEvent")
    public void listenToPasswordReset() {
        Contestant contestant = (Contestant) Contexts.getPageContext().get("passwordResettingContestant");

        if (contestant!=null) {
            emailAction.sendMessage("passwordReset.xhtml");
        }
        else {
            log.info("Password resetting contestant was not outjected");
        }
        Redirect redirect = Redirect.instance();
        redirect.setViewId("/emailSent.xhtml");
        redirect.execute();
    }
}
