package net.therap.listener;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Observer;
import org.jboss.seam.faces.Redirect;

import java.io.Serializable;

/**
 * Created by
 * User: tahmid
 * Date: 7/25/12
 * Time: 1:45 PM
 */

@Name("firstLoginListener")
public class LoginListener implements Serializable {

    @Observer(value = "firstLogin")
    public void RedirectToChangePassword() {
        Redirect redirect = Redirect.instance();
        redirect.setViewId("/changePassword.xhtml");
        redirect.execute();
    }

}
