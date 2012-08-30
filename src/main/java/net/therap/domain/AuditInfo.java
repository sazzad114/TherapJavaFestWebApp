package net.therap.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: sazzadur
 * Date: 7/19/12
 * Time: 3:41 PM
 */
@Entity
@Table(name = "AUDIT_INFO")
public class AuditInfo implements Serializable {

    private long auditInfoId;
    private Contestant contestant;
    private Date loginTime;
    private String ipAddress;
    private String clientAgent;
    private long version;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "AUDIT_INFO_ID")
    public long getAuditInfoId() {
        return auditInfoId;
    }

    public void setAuditInfoId(long auditInfoId) {
        this.auditInfoId = auditInfoId;
    }

    @ManyToOne
    @JoinColumn(name = "CONTESTANT_ID")
    public Contestant getContestant() {
        return contestant;
    }

    public void setContestant(Contestant contestant) {
        this.contestant = contestant;
    }

    @Column(name = "LOGIN_TIME")
    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    @Column(name = "IP_ADDRESS")
    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    @Column(name = "CLIENT_AGENT")
    public String getClientAgent() {
        return clientAgent;
    }

    public void setClientAgent(String clientAgent) {
        this.clientAgent = clientAgent;
    }

    @Version
    @Column(name = "VERSION")
    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }
}
