package net.therap.service;

import net.therap.dao.ContestantDao;
import net.therap.domain.Contestant;
import net.therap.util.ContestantState;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.core.Events;
import org.jboss.seam.log.Log;
import org.jboss.seam.security.Identity;

import static org.jboss.seam.ScopeType.SESSION;

@Name("authenticator")
public class AuthenticatorService {


    @In
    private ContestantDao contestantDao;

    @In
    Identity identity;

    @Logger
    Log log;

    @Out(required = false, scope = SESSION)
    private Contestant loggedInContestant;

    public boolean authenticate() {

        Contestant contestant = contestantDao.getContestantByEmail(identity.getUsername());

        if (contestant != null) {

            if (contestant.getPassword().equals(identity.getPassword())) {

                loggedInContestant = contestant;
                if (loggedInContestant.getState() == ContestantState.TEMPORARY_CONTESTANT) {
                    loggedInContestant.setState(ContestantState.NEW_CONTESTANT);
                    contestantDao.updateContestant(loggedInContestant);
                    Events.instance().raiseEvent("firstLogin");
                }
                return true;
            }
            return false;
        }
        return false;
    }
}
