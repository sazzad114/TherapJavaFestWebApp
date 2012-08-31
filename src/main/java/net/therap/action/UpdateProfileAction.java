package net.therap.action;

import net.therap.dao.ContestantDao;
import net.therap.domain.Contestant;
import net.therap.util.FileValidatorUtil;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.*;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: sazzadur
 * @since: 8/22/12 10:50 AM
 */
@Name("updateProfileAction")
@Scope(ScopeType.EVENT)
public class UpdateProfileAction implements Serializable {

    private final int UPLOADED_IMAGE_SIZE = 5;
    private final int UPLOADED_CV_SIZE = 5;

    @In (create = true)
    private ContestantDao contestantDao;

    @In
    private FacesMessages facesMessages;

    @Out(required = false)
    private Contestant loggedInContestant;


    @Logger
    private Log log;


    public String updateProfile(Contestant contestant) {

        List<String> imageFileTypes = new ArrayList<String>();
        imageFileTypes.add("image/gif");
        imageFileTypes.add("image/jpeg");
        imageFileTypes.add("image/png");

        int imageFileSize = 5 * 1024 * 1024;

        List<String> cvFileTypes = new ArrayList<String>();
        cvFileTypes.add("application/pdf");

        int cvFileSize = 5 * 1024 * 1024;

        boolean validationFails = false;

        if (contestant.getImageFileWrapper().getSize() != 0) {

            if (!FileValidatorUtil.validateFileSize(contestant.getImageFileWrapper(), imageFileSize)) {
                facesMessages.addToControl("image", "File size must not exceed " + UPLOADED_IMAGE_SIZE + "MB");
                validationFails = true;
            }

            if (!FileValidatorUtil.validateFileType(contestant.getImageFileWrapper(), imageFileTypes)) {
                facesMessages.addToControl("image", "Only GIF, JPEG, PNG files are allowed");
                validationFails = true;
            }
        }

        if (contestant.getPdfFileWrapper().getSize() != 0) {

            if (!FileValidatorUtil.validateFileSize(contestant.getPdfFileWrapper(), cvFileSize)) {
                facesMessages.addToControl("curriculumVitae", "File size must not exceed " + UPLOADED_CV_SIZE + "MB");
                validationFails = true;
            }
            if (!FileValidatorUtil.validateFileType(contestant.getPdfFileWrapper(), cvFileTypes)) {
                facesMessages.addToControl("curriculumVitae", "Only PDF file is allowed");
                validationFails = true;
            }
        }


        if (!validationFails) {

            log.debug("About to save contestant");

            if (contestant.getImageFileWrapper().getSize() != 0) {
                contestant.setPhoto(contestant.getImageFileWrapper().getFileData());
            }

            if (contestant.getPdfFileWrapper().getSize() != 0) {
                contestant.setCurriculumVitae(contestant.getPdfFileWrapper().getFileData());
            }

            contestantDao.updateContestant(contestant);
            loggedInContestant = contestant;
            log.debug("Saved contestant");
            return "success";
        }

        facesMessages.addToControl("verifyCaptcha", "Please enter captcha again");
        return "failed";
    }

}
