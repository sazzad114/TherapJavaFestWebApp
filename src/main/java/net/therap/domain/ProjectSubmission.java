package net.therap.domain;

import javax.persistence.*;
import java.sql.Blob;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: sazzadur
 * Date: 7/19/12
 * Time: 3:20 PM
 */

@Entity
@Table(name = "PROJECT_SUBMISSION")
public class ProjectSubmission {

    private long projectId;
    private Group group;
    private Contestant lastSubmittedBy;
    private Date lastSubmissionTime;
    private String gitHubUrl;
    private String youTubeUrl;
    private byte [] sourceCode;
    private long version;

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
    @PrimaryKeyJoinColumn
    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    @OneToOne
    @PrimaryKeyJoinColumn
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
}
