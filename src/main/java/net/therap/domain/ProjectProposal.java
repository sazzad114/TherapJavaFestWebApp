package net.therap.domain;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: sazzadur
 * Date: 7/19/12
 * Time: 3:06 PM
 */
@Name("projectProposal")
@Scope(ScopeType.PAGE)
@Entity
@Table(name = "PROJECT_PROPOSAL")
public class ProjectProposal implements Serializable{

    private long proposalId;
    private Group proposingGroup;
    private Contestant lastModifiedBy;
    private Date lastModificationTime;
    private byte[] proposal;
    private UploadedFile uploadedProposal;
    private long version;

    public ProjectProposal() {
        this.uploadedProposal = new UploadedFile();
    }

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
    public Group getProposingGroup() {
        return proposingGroup;
    }

    public void setProposingGroup(Group proposingGroup) {
        this.proposingGroup = proposingGroup;
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

    @Transient
    public UploadedFile getUploadedProposal() {
        return uploadedProposal;
    }

    public void setUploadedProposal(UploadedFile uploadedProposal) {
        this.uploadedProposal = uploadedProposal;
    }
}
