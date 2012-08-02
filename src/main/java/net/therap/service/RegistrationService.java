package net.therap.service;

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
import org.jboss.seam.log.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by
 * User: tahmid
 * Date: 7/23/12
 * Time: 10:38 AM
 */

@Name("registrationService")
@Scope(ScopeType.EVENT)
public class RegistrationService {

    private final int TEMPORARY_PASSWORD_LEN = 10;
    private final int UPLOADED_IMAGE_SIZE = 5;
    private final int UPLOADED_CV_SIZE = 5;

    @In(create = true)
    private ContestantDao contestantDao;

    @In
    private EmailService emailService;

    @In
    private FacesMessages facesMessages;


    @Logger
    private Log log;


    public String register(Contestant contestant) {

        List<String> imageFileTypes = new ArrayList<String>();
        imageFileTypes.add("image/gif");
        imageFileTypes.add("image/jpeg");
        imageFileTypes.add("image/png");

        int imageFileSize = 5 * 1024 * 1024;

        List<String> cvFileTypes = new ArrayList<String>();
        cvFileTypes.add("application/pdf");

        int cvFileSize = 5 * 1024 * 1024;

        boolean validationFails = false;

        if (!FileValidatorUtil.validateFileSize(contestant.getImageFileWrapper(), imageFileSize)) {
            facesMessages.addToControl("image", "file size must not exceed " + UPLOADED_IMAGE_SIZE + "MB");
            validationFails = true;
        }
        if (!FileValidatorUtil.validateFileType(contestant.getImageFileWrapper(), imageFileTypes)) {
            facesMessages.addToControl("image", "only gif,jpeg,png images are allowed");
            validationFails = true;
        }

        if (!FileValidatorUtil.validateFileSize(contestant.getPdfFileWrapper(), cvFileSize)) {
            facesMessages.addToControl("curriculumVitae", "file size must not exceed " + UPLOADED_CV_SIZE + "MB");
            validationFails = true;
        }
        if (!FileValidatorUtil.validateFileType(contestant.getPdfFileWrapper(), cvFileTypes)) {
            facesMessages.addToControl("curriculumVitae", "only pdf file is allowed");
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
            emailService.sendMessage();
            log.debug("Saved contestant");
            return "success";
        }

        return "failed";
    }


}
