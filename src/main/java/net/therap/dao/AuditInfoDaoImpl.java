package net.therap.dao;

import net.therap.domain.AuditInfo;
import org.hibernate.Session;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Startup;

import java.io.Serializable;

/**
 * Created by
 *
 * @author: tahmid
 * @since: 8/12/12 3:10 PM
 */

@Name("auditInfoDao")
@Scope(ScopeType.APPLICATION)
@Startup
public class AuditInfoDaoImpl implements AuditInfoDao, Serializable {

    @In
    Session session;

    public void saveAuditInfo(AuditInfo auditInfo) {
        session.save(auditInfo);
    }
}
