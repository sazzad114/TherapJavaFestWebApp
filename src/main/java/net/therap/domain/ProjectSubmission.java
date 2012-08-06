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
 * Time: 3:20 PM
 */
@Name("projectSubmission")
@Scope(ScopeType.PAGE)
@Entity
@Table(name = "PROJECT_SUBMISSION")
public class ProjectSubmission implements Serializable{

    private long projectId;
    private Group submittingGroup;
    private Contestant lastSubmittedBy;
    private Date lastSubmissionTime;
    private String gitHubUrl;
    private String youTubeUrl;
    private byte[] sourceCode;
    private UploadedFile uploadedSourceCode;
    private long version;

     public ProjectSubmission() {
        this.uploadedSourceCode = new UploadedFile();
    }


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PROJECT_ID")
    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    @OneToOne
    @JoinColumn(name = "GROUP_ID")
    public Group getSubmittingGroup() {
        return submittingGroup;
    }

    public void setSubmittingGroup(Group submittingGroup) {
        this.submittingGroup = submittingGroup;
    }

    @OneToOne
    @JoinColumn(name = "CONTESTANT_ID")
    public Contestant getLastSubmittedBy() {
        return lastSubmittedBy;
    }

    public void setLastSubmittedBy(Contestant lastSubmittedBy) {
        this.lastSubmittedBy = lastSubmittedBy;
    }

    @Column(name = "LAST_SUBMISSION_TIME")
    public Date getLastSubmissionTime() {
        return lastSubmissionTime;
    }

    public void setLastSubmissionTime(Date lastSubmissionTime) {
        this.lastSubmissionTime = lastSubmissionTime;
    }

    @Column(name = "GITHUB_URL")
    public String getGitHubUrl() {
        return gitHubUrl;
    }

    public void setGitHubUrl(String gitHubUrl) {
        this.gitHubUrl = gitHubUrl;
    }

    @Column(name = "YOUTUBE_URL")
    public String getYouTubeUrl() {
        return youTubeUrl;
    }

    public void setYouTubeUrl(String youTubeUrl) {
        this.youTubeUrl = youTubeUrl;
    }

    @Lob
    @Column(name = "SOURCE_CODE", length = 16777215)
    public byte[] getSourceCode() {
        return sourceCode;
    }

    public void setSourceCode(byte[] sourceCode) {
        this.sourceCode = sourceCode;
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
    public UploadedFile getUploadedSourceCode() {
        return uploadedSourceCode;
    }

    public void setUploadedSourceCode(UploadedFile uploadedSourceCode) {
        this.uploadedSourceCode = uploadedSourceCode;
    }
}
