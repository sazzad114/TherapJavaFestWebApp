package net.therap.service;

import net.therap.domain.AuditInfo;
import net.therap.domain.Contestant;
import org.hibernate.Session;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Observer;


import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by
 * User: tahmid
 * Date: 7/29/12
 * Time: 4:42 PM
 */

@Name("auditService")
public class AuditService {

    @In
    Session session;

    @In
    FacesContext facesContext;

    @In
    Contestant loggedInContestant;


    public void saveAuditInfo() {

        HttpServletRequest request = (HttpServletRequest)facesContext.getExternalContext().getRequest();
        AuditInfo auditInfo = new AuditInfo();
        auditInfo.setIpAddress(request.getRemoteAddr());
        auditInfo.setLoginTime(new Date());
        auditInfo.setContestant(loggedInContestant);
        auditInfo.setClientAgent(request.getHeader("User-Agent"));
        session.save(auditInfo);

    }

}
