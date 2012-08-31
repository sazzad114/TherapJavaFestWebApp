package net.therap.listener;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.*;
import org.jboss.seam.faces.Redirect;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: sazzadur
 * @since: 8/31/12 6:16 PM
 */
@Name("loginFailedListener")
@Scope(ScopeType.EVENT)
@AutoCreate
public class LoginFailedListener {

    @Out(value = "loginFailed",scope = ScopeType.CONVERSATION)
    private boolean loginFailed;

    @Observer("org.jboss.seam.security.loginFailed")
     public void RedirectToLoginView() {
        loginFailed = true;
        Redirect redirect = Redirect.instance();
        redirect.setViewId("/loginPage.xhtml");
        redirect.execute();
    }

}
