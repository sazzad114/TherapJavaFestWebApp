package net.therap.action;

import net.therap.dao.AuditInfoDao;
import net.therap.dao.ContestantDao;
import net.therap.domain.AuditInfo;
import net.therap.domain.Contestant;
import net.therap.util.ContestantState;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.*;
import org.jboss.seam.core.Events;
import org.jboss.seam.log.Log;
import org.jboss.seam.security.Identity;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Name("authenticator")
@Scope(ScopeType.EVENT)
public class AuthenticatorService {


    @In
    private ContestantDao contestantDao;

    @In
    private AuditInfoDao auditInfoDao;

    @In
    private FacesContext facesContext;

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
                saveAuditInfo(contestant);
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

    public void saveAuditInfo(Contestant contestant) {

        HttpServletRequest request = (HttpServletRequest)facesContext.getExternalContext().getRequest();
        AuditInfo auditInfo = new AuditInfo();
        auditInfo.setIpAddress(request.getRemoteAddr());
        auditInfo.setLoginTime(new Date());
        auditInfo.setContestant(contestant);
        auditInfo.setClientAgent(request.getHeader("User-Agent"));
        auditInfoDao.saveAuditInfo(auditInfo);

    }
}
