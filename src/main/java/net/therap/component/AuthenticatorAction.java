package net.therap.component;

import net.therap.domain.Contestant;
import org.hibernate.Session;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;

import java.util.List;

import static org.jboss.seam.ScopeType.SESSION;

@Name("authenticator")
public class AuthenticatorAction {
    @In
    Session session;

    @Out(required = false, scope = SESSION)
    private Contestant loggedInContestant;

    public boolean authenticate() {
        List results = session.createQuery("select contestant from Contestant contestant where contestant.email=#{identity.username} and contestant.password=#{identity.password}")
                .list();

        if (results.size() == 0) {
            return false;
        } else {
            loggedInContestant = (Contestant) results.get(0);
            return true;
        }
    }
}
