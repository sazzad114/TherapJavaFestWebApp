package net.therap.service;

import net.therap.domain.Contestant;
import net.therap.util.ContestantState;
import org.hibernate.Session;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.core.Events;

import java.util.List;

import static org.jboss.seam.ScopeType.SESSION;

@Name("authenticator")
public class AuthenticatorService {
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
            if (loggedInContestant.getState() == ContestantState.TEMPORARY_CONTESTANT) {
                loggedInContestant.setState(ContestantState.NEW_CONTESTANT);
                session.update(loggedInContestant);
                Events.instance().raiseEvent("firstLogin");
            }
            return true;
        }
    }
}
