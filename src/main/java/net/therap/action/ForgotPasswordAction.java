package net.therap.action;

import net.therap.dao.ContestantDao;
import net.therap.domain.Contestant;
import net.therap.util.StringGeneratorUtil;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.contexts.Contexts;
import org.jboss.seam.faces.Redirect;
import org.jboss.seam.log.Log;

import java.io.Serializable;

/**
 * Created by
 *
 * @author: tahmid
 * @since: 8/31/12 6:09 PM
 */
@Name("forgotPasswordAction")
@Scope(ScopeType.EVENT)
public class ForgotPasswordAction implements Serializable {
    private final int TEMPORARY_PASSWORD_LEN = 10;

    @Logger
    private Log log;

    @In(create = true)
    private ContestantDao contestantDao;

    @In(create = true)
    private EmailAction emailAction;

    private Contestant passwordResettingContestant;

    private String email;

    public void resetPassword() {
        String tempPass = StringGeneratorUtil.generateString(TEMPORARY_PASSWORD_LEN);
        passwordResettingContestant = contestantDao.getContestantByEmail(email);
        if (passwordResettingContestant != null) {
            passwordResettingContestant.setPassword(tempPass);
            contestantDao.updateContestant(passwordResettingContestant);
            Contexts.getSessionContext().set("passwordResettingContestant", passwordResettingContestant);
            emailAction.sendMessage("passwordReset.xhtml");
            Contexts.getSessionContext().remove("passwordResettingContestant");

        }
        Redirect redirect = Redirect.instance();
        redirect.setViewId("/emailSent.xhtml");
        redirect.execute();

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
