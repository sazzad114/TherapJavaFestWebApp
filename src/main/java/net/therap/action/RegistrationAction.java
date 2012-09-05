package net.therap.action;

import net.therap.dao.ContestantDao;
import net.therap.domain.Contestant;
import net.therap.util.ContestantState;
import net.therap.util.FileValidatorUtil;
import net.therap.util.StringGeneratorUtil;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.faces.Redirect;
import org.jboss.seam.log.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by
 * User: tahmid
 * Date: 7/23/12
 * Time: 10:38 AM
 */

@Name("registrationAction")
@Scope(ScopeType.EVENT)
public class RegistrationAction implements Serializable {

    private final int TEMPORARY_PASSWORD_LEN = 10;
    private final float UPLOADED_IMAGE_SIZE = 0.5f;
    private final int UPLOADED_CV_SIZE = 5;

    @In(create = true)
    private ContestantDao contestantDao;

    @In(create = true)
    private EmailAction emailAction;

    @In
    private FacesMessages facesMessages;


    @Logger
    private Log log;


    public String register(Contestant contestant) {

        printRegistrationInfoInLog(contestant);

        List<String> imageFileTypes = new ArrayList<String>();
        imageFileTypes.add("image/gif");
        imageFileTypes.add("image/jpeg");
        imageFileTypes.add("image/png");

        int imageFileSize = (int) (UPLOADED_IMAGE_SIZE * 1024 * 1024);

        List<String> cvFileTypes = new ArrayList<String>();
        cvFileTypes.add("application/pdf");

        int cvFileSize = 5 * 1024 * 1024;

        boolean validationFails = false;

        if (!FileValidatorUtil.validateFileSize(contestant.getImageFileWrapper(), imageFileSize)) {
            facesMessages.addToControl("image", "file size must not exceed 500 KB");
            validationFails = true;
        }
        if (!FileValidatorUtil.validateFileType(contestant.getImageFileWrapper(), imageFileTypes)) {
            facesMessages.addToControl("image", "Only GIF, JPEG, PNG files are allowed");
            validationFails = true;
        }

        if (contestant.getPdfFileWrapper().getSize() != 0) {

            if (!FileValidatorUtil.validateFileSize(contestant.getPdfFileWrapper(), cvFileSize)) {
                facesMessages.addToControl("curriculumVitae", "file size must not exceed " + UPLOADED_CV_SIZE + "MB");
                validationFails = true;
            }
            if (!FileValidatorUtil.validateFileType(contestant.getPdfFileWrapper(), cvFileTypes)) {
                facesMessages.addToControl("curriculumVitae", "Only PDF file is allowed");
                validationFails = true;
            }
        }

        Contestant existingContestant = contestantDao.getContestantByEmail(contestant.getEmail());

        if (existingContestant != null) {
            facesMessages.addToControl("email", "Provided email has already been registered");
            validationFails = true;
        }


        if (!validationFails) {

            log.debug("About to save contestant");
            contestant.setPhoto(contestant.getImageFileWrapper().getFileData());
            contestant.setCurriculumVitae(contestant.getPdfFileWrapper().getFileData());
            String temporaryPassword = StringGeneratorUtil.generateString(TEMPORARY_PASSWORD_LEN);
            contestant.setPassword(temporaryPassword);
            contestant.setState(ContestantState.TEMPORARY_CONTESTANT);
            contestantDao.saveContestant(contestant);
            emailAction.sendMessage("registrationEmail.xhtml");
            log.info("Saved contestant at" + new Date());
            Redirect redirect = Redirect.instance();
            redirect.setViewId("/emailSent.xhtml");
            redirect.execute();
        }

        facesMessages.addToControl("verifyCaptcha", "Please enter captcha again");
        return "failed";
    }

    public void printRegistrationInfoInLog(Contestant contestant) {

        String contestantName = contestant.getContestantName();
        String email = contestant.getEmail();
        String phone = contestant.getPhone();
        String studentId = contestant.getStudentId();
        Double cgpa = contestant.getCgpa();
        String university = contestant.getUniversity();
        Date dateOfBirth = contestant.getDateOfBirth();
        Date expectedGraduationDate = contestant.getExpectedGraduationDate();
        String gender = contestant.getGender();
        String description = contestant.getDescription();
        String linkedInProfile = contestant.getLinkedInProfile();
        String languageProficiency = contestant.getLanguageProficiency();

        log.info("Contestant Name: " + contestantName);
        log.info("Email: " +email);
        log.info("Phone: " + phone);
        log.info("Student Id: " + studentId);
        log.info("CGPA: " + cgpa);
        log.info("University: " + university);
        log.info("Date of Birth: " + dateOfBirth);
        log.info("Expected Graduation Date: " + expectedGraduationDate);
        log.info("Gender: " + gender);
        log.info("Description: " + description);
        log.info("LinkedIn Profile: " + linkedInProfile);
        log.info("Favorite language: " + languageProficiency);

    }


}
