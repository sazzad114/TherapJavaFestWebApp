package net.therap.service;

import net.therap.dao.ContestantDao;
import net.therap.domain.Contestant;
import net.therap.util.ContestantState;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.*;
import org.jboss.seam.core.Events;
import org.jboss.seam.log.Log;
import org.jboss.seam.security.Identity;

@Name("authenticator")
@Scope(ScopeType.EVENT)
public class AuthenticatorService {


    @In(create = true)
    private ContestantDao contestantDao;

    @In
    Identity identity;

    @Logger
    Log log;

    @Out(required = false,scope = ScopeType.SESSION)
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
