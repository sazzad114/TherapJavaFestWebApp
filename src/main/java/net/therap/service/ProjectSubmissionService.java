package net.therap.service;

import net.therap.domain.ProjectSubmission;
import net.therap.util.FileValidatorUtil;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.faces.FacesMessages;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: sazzadur
 * Date: 8/5/12
 * Time: 6:04 PM
 */
@Name("projectSubmissionService")
public class ProjectSubmissionService {

    @In
    FacesMessages facesMessages;

    private final int UPLOADED_SOURCE_CODE_SIZE = 10;

    public String submitProjectSource(ProjectSubmission projectSubmission) {
        List<String> sourceCodeFileType = new ArrayList<String>();
        sourceCodeFileType.add("application/zip");

        int sourceCodeFileSize = UPLOADED_SOURCE_CODE_SIZE * 1024 * 1024;
        boolean validationFails = false;

        if (!FileValidatorUtil.validateFileSize(projectSubmission.getUploadedSourceCode(), sourceCodeFileSize)) {
            facesMessages.addToControl("sourceCodeUpload", "file size must not exceed " + UPLOADED_SOURCE_CODE_SIZE + "MB");
            validationFails = true;
        }
        if (!FileValidatorUtil.validateFileType(projectSubmission.getUploadedSourceCode(), sourceCodeFileType)) {
            facesMessages.addToControl("sourceCodeUpload", "only zipped archive files are allowed");
            validationFails = true;
        }

        if (!validationFails) {

            return "success";
        }

        return "failure";
    }


   /* public String updateProjectSource(ProjectSubmission projectSubmission) {
        List<String> sourceCodeFileType = new ArrayList<String>();
        sourceCodeFileType.add("application/zip");

        int sourceCodeFileSize = UPLOADED_SOURCE_CODE_SIZE * 1024 * 1024;
        boolean validationFails = false;

        if (!FileValidatorUtil.validateFileSize(projectSubmission.getUploadedSourceCode(), sourceCodeFileSize)) {
            facesMessages.addToControl("sourceCodeUpload", "file size must not exceed " + UPLOADED_SOURCE_CODE_SIZE + "MB");
            validationFails = true;
        }
        if (!FileValidatorUtil.validateFileType(projectSubmission.getUploadedSourceCode(), sourceCodeFileType)) {
            facesMessages.addToControl("sourceCodeUpload", "only zipped archive files are allowed");
            validationFails = true;
        }

        if (!validationFails) {

            return "success";
        }

        return "failure";
    }*/
}
