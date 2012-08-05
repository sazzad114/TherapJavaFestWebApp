package net.therap.domain;

import javax.persistence.*;
import java.sql.Blob;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: sazzadur
 * Date: 7/19/12
 * Time: 3:06 PM
 */
@Entity
@Table(name = "PROJECT_PROPOSAL")
public class ProjectProposal {

    private long proposalId;
    private Group group;
    private Contestant lastModifiedBy;
    private Date lastModificationTime;
    private byte[] proposal;
    private long version;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PROPOSAL_ID")
    public long getProposalId() {
        return proposalId;
    }

    public void setProposalId(long proposalId) {
        this.proposalId = proposalId;
    }

    @OneToOne
    @JoinColumn(name = "GROUP_ID")
    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    @OneToOne
    @JoinColumn(name = "CONTESTANT_ID")
    public Contestant getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(Contestant lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "LAST_MODIFICATION_TIME", nullable = false)
    public Date getLastModificationTime() {
        return lastModificationTime;
    }

    public void setLastModificationTime(Date lastModificationTime) {
        this.lastModificationTime = lastModificationTime;
    }

    @Lob
    @Column(name = "PROPOSAL", length = 16777215)
    public byte[] getProposal() {
        return proposal;
    }

    public void setProposal(byte [] proposal) {
        this.proposal = proposal;
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
