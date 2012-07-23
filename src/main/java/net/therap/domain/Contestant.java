package net.therap.domain;

import org.hibernate.validator.NotEmpty;
import org.hibernate.validator.NotNull;
import org.jboss.seam.annotations.Name;

import javax.persistence.*;
import java.sql.Blob;
import java.util.Date;
import java.util.List;

/**
 * Created by
 * User: tahmid
 * Date: 7/18/12
 * Time: 5:07 PM
 */

@Entity
@Name("contestant")
@Table(name = "Contestant")
public class Contestant {

    private long contestantId;
    private String contestantName;
    private String email;
    private String phone;
    private String studentId;
    private double cgpa;
    private String university;
    private Date dateOfBirth;
    private Date expectedGraduationDate;
    private String gender;
    private String description;
    private String linkedInProfile;
    private String languageProficiency;
    private byte[] photo;
    private byte[] curriculumVitae;
    private String state;
    private Group myGroup;
    private List<AnswerInfo> screeningTestResult;
    private long version;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CONTESTANT_ID")
    public long getContestantId() {
        return contestantId;
    }

    public void setContestantId(long contestantId) {
        this.contestantId = contestantId;
    }

    @NotNull
    @NotEmpty
    @Column(name = "CONTESTANT_NAME", nullable = false)
    public String getContestantName() {
        return contestantName;
    }

    public void setContestantName(String contestantName) {
        this.contestantName = contestantName;
    }

    @Column(name = "EMAIL")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "PHONE")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Column(name = "STUDENT_ID")
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    @Column(name = "UNIVERSITY")
    @Enumerated(EnumType.STRING)
    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    @Column(name = "CGPA")
    public double getCgpa() {
        return cgpa;
    }

    public void setCgpa(double cgpa) {
        this.cgpa = cgpa;
    }

    @Column(name = "DATE_OF_BIRTH")
    @Temporal(value = TemporalType.DATE)
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }


    @Column(name = "EXPECTED_GRADUATION_DATE")
    public Date getExpectedGraduationDate() {
        return expectedGraduationDate;
    }

    public void setExpectedGraduationDate(Date expectedGraduationDate) {
        this.expectedGraduationDate = expectedGraduationDate;
    }

    @Column(name = "GENDER")
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "LINKEDIN_PROFILE")
    public String getLinkedInProfile() {
        return linkedInProfile;
    }

    public void setLinkedInProfile(String linkedInProfile) {
        this.linkedInProfile = linkedInProfile;
    }

    @Column(name = "LANGUAGE_PROFICIENCY")
    public String getLanguageProficiency() {
        return languageProficiency;
    }

    public void setLanguageProficiency(String languageProficiency) {
        this.languageProficiency = languageProficiency;
    }

    @Lob
    @Column(name = "PHOTO")
    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    @Lob
    @Column(name = "CURRICULUM_VITAE")
    public byte[] getCurriculumVitae() {
        return curriculumVitae;
    }

    public void setCurriculumVitae(byte[] curriculumVitae) {
        this.curriculumVitae = curriculumVitae;
    }

    @Column(name = "STATE")
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @ManyToOne
    @JoinColumn(name = "GROUP_ID")
    public Group getMyGroup() {
        return myGroup;
    }

    public void setMyGroup(Group myGroup) {
        this.myGroup = myGroup;
    }

    @OneToMany(mappedBy = "contestant")
    public List<AnswerInfo> getScreeningTestResult() {
        return screeningTestResult;
    }

    public void setScreeningTestResult(List<AnswerInfo> screeningTestResult) {
        this.screeningTestResult = screeningTestResult;
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
