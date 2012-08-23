package net.therap.domain;

import org.hibernate.validator.NotEmpty;
import org.hibernate.validator.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by
 * User: tahmid
 * Date: 7/18/12
 * Time: 6:05 PM
 */

@Entity
@Table(name = "CONTEST_GROUP")
public class Group {

    private long groupId;
    private String groupName;
    private List<Contestant> members;
    private long version;
    private ProjectProposal projectProposal;
    private ProjectSubmission submittedProject;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "GROUP_ID")
    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    @Column(name = "GROUP_NAME", nullable = false, unique = true)
    @NotNull
    @NotEmpty
    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @OneToMany(mappedBy = "myGroup", cascade = CascadeType.ALL)
    public List<Contestant> getMembers() {
        return members;
    }

    public void setMembers(List<Contestant> members) {
        this.members = members;
    }

    @OneToOne(mappedBy = "proposingGroup")
    public ProjectProposal getProjectProposal() {
        return projectProposal;
    }

    public void setProjectProposal(ProjectProposal projectProposal) {
        this.projectProposal = projectProposal;
    }

    @OneToOne(mappedBy = "submittingGroup")
    public ProjectSubmission getSubmittedProject() {
        return submittedProject;
    }

    public void setSubmittedProject(ProjectSubmission submittedProject) {
        this.submittedProject = submittedProject;
    }

    @Column(name = "VERSION")
    @Version
    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

}
