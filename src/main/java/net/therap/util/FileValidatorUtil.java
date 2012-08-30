package net.therap.util;

import net.therap.domain.UploadedFile;

import java.io.Serializable;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: sazzadur
 * Date: 8/2/12
 * Time: 2:17 PM
 */
public class FileValidatorUtil implements Serializable {

    public static boolean validateFileSize(UploadedFile uploadedFile, int allowableSize) {

        if (uploadedFile.getSize() > allowableSize) {
            return false;
        }
        return true;
    }

    public static boolean validateFileType(UploadedFile uploadedFile, List<String> acceptableFileTypes) {

        boolean validFile = false;

        for (String allowableFileType : acceptableFileTypes) {

            if (allowableFileType.equals(uploadedFile.getContentType())) {
                validFile = true;
            }
        }

        return validFile;
    }
}
