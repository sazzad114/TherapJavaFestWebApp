package net.therap.component;

import net.therap.domain.Contestant;
import org.hibernate.Session;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.faces.FacesMessages;

/**
 * Created by IntelliJ IDEA.
 * User: sazzadur
 * Date: 7/24/12
 * Time: 2:52 PM
 */
@Name("passwordChange")
@Scope(ScopeType.PAGE)
public class PasswordChangeService {

    @In
    @Out
    private Contestant loggedInContestant;

    @In
    private Session session;


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
                session.update(loggedInContestant);
                changed = true;
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
