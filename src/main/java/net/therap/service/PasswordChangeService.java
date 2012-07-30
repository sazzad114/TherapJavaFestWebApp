package net.therap.service;

import net.therap.dao.ContestantDao;
import net.therap.domain.Contestant;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.*;
import org.jboss.seam.core.Events;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;

/**
 * Created by IntelliJ IDEA.
 * User: sazzadur
 * Date: 7/24/12
 * Time: 2:52 PM
 */
@Name("passwordChange")
@Scope(ScopeType.PAGE)
public class PasswordChangeService {

    @Logger
    private Log log;

    @In
    @Out
    private Contestant loggedInContestant;

    @In
    private ContestantDao contestantDao;


    private String currentPassword;
    private String newPassword;
    private String confirmNewPassword;
    private boolean changed;

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmNewPassword() {
        return confirmNewPassword;
    }

    public void setConfirmNewPassword(String confirmNewPassword) {
        this.confirmNewPassword = confirmNewPassword;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public boolean isChanged() {
        return changed;
    }

    public void setChanged(boolean changed) {
        this.changed = changed;
    }


    public void changePassword() {

        if (loggedInContestant.getPassword().equals(currentPassword)) {

            if(newPassword.equals(confirmNewPassword))
             {
                loggedInContestant.setPassword(newPassword);
                contestantDao.updateContestant(loggedInContestant);
                changed = true;
                Events.instance().raiseEvent("logout");

             }
            else {
                 FacesMessages.instance().addToControl("confirmNewPassword", "New Password mismatch");
                 currentPassword = null;
                 newPassword = null;
                 confirmNewPassword = null;
                 changed = false;
             }


        } else {
            FacesMessages.instance().addToControl("currentPassword", "Current password was incorrect");
            currentPassword = null;
            newPassword = null;
            confirmNewPassword = null;
            changed = false;

        }
    }


}
