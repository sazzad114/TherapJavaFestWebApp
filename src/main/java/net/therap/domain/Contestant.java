package net.therap.domain;

import net.therap.util.RegularExpressions;
import org.hibernate.validator.*;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Role;
import org.jboss.seam.annotations.Roles;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.core.Events;

import javax.persistence.*;
import javax.persistence.Version;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by
 * User: tahmid
 * Date: 7/18/12
 * Time: 5:07 PM
 */


@Name("loggedInContestant")
@Scope(ScopeType.SESSION)
@Role(name = "newContestant", scope = ScopeType.PAGE)
@Entity
@Table(name = "CONTESTANT")
public class Contestant implements Serializable{

    private long contestantId;
    private String contestantName;
    private String email;
    private String password;
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
    private int state;
    private Group myGroup;
    private ScreeningTest screeningTest;
    private List<AnswerInfo> screeningTestResult;
    private long version;
    private UploadedFile pdfFileWrapper;
    private UploadedFile imageFileWrapper;
    private boolean agreeWithTermsAndCondition;

    public Contestant() {
        pdfFileWrapper = new UploadedFile();
        imageFileWrapper = new UploadedFile();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CONTESTANT_ID")
    public long getContestantId() {
        return contestantId;
    }

    public void setContestantId(long contestantId) {
        this.contestantId = contestantId;
    }


    @Length(max = 50, min = 3)
    @Column(name = "CONTESTANT_NAME", nullable = false)
    public String getContestantName() {
        return contestantName;
    }

    public void setContestantName(String contestantName) {
        this.contestantName = contestantName;
    }

    @Email
    @Column(name = "EMAIL", nullable = false, unique = true)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Length(min = 7, max = 15)
    @Column(name = "PHONE")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Length(max = 15)
    @Column(name = "STUDENT_ID")
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    @NotNull
    @Column(name = "UNIVERSITY")
    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    @Range(max = 5,min = 0, message = "CGPA must be within 0 to 5.0")
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
    @Temporal(value = TemporalType.DATE)
    public Date getExpectedGraduationDate() {
        return expectedGraduationDate;
    }

    public void setExpectedGraduationDate(Date expectedGraduationDate) {
        this.expectedGraduationDate = expectedGraduationDate;
    }

    @Column(name = "GENDER")
    @NotNull
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Length(min = 20, max=300)
    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "LINKEDIN_PROFILE",nullable = true)

    @Pattern(regex = RegularExpressions.LINKED_IN, message = "Invalid LinkedIn profile url")
    public String getLinkedInProfile() {
        return linkedInProfile;
    }

    public void setLinkedInProfile(String linkedInProfile) {
        this.linkedInProfile = linkedInProfile;
    }

    @Column(name = "LANGUAGE_PROFICIENCY")
    /*@Pattern(regex = RegularExpressions.LANGUAGE_PROFICIENCY, message = "Should be comma separated")*/
    @NotEmpty
    @NotNull
    public String getLanguageProficiency() {
        return languageProficiency;
    }

    public void setLanguageProficiency(String languageProficiency) {
        this.languageProficiency = languageProficiency;
    }

    @Lob
    @Column(name = "PHOTO",length = 16777215)
    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    @Lob
    @Column(name = "CURRICULUM_VITAE", length = 16777215)
    public byte[] getCurriculumVitae() {
        return curriculumVitae;
    }

    public void setCurriculumVitae(byte[] curriculumVitae) {
        this.curriculumVitae = curriculumVitae;
    }

    @Column(name = "STATE")
    public int getState() {
        return state;
    }

    public void setState(int state) {
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

    @Column(name = "PASSWORD",nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @OneToOne
    @JoinColumn(name = "SCREENING_TEST_ID")
    public ScreeningTest getScreeningTest() {
        return screeningTest;
    }

    public void setScreeningTest(ScreeningTest screeningTest) {
        this.screeningTest = screeningTest;
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
    public UploadedFile getPdfFileWrapper() {
        return pdfFileWrapper;
    }

    public void setPdfFileWrapper(UploadedFile pdfFileWrapper) {
        this.pdfFileWrapper = pdfFileWrapper;
    }

    @Transient
    public UploadedFile getImageFileWrapper() {
        return imageFileWrapper;
    }

    public void setImageFileWrapper(UploadedFile imageFileWrapper) {
        this.imageFileWrapper = imageFileWrapper;
    }

    @Transient
    public boolean isAgreeWithTermsAndCondition() {
        return agreeWithTermsAndCondition;
    }

    public void setAgreeWithTermsAndCondition(boolean agreeWithTermsAndCondition) {
        this.agreeWithTermsAndCondition = agreeWithTermsAndCondition;
    }
}
