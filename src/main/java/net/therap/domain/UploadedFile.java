package net.therap.domain;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: sazzadur
 * Date: 8/2/12
 * Time: 1:13 PM
 */

public class UploadedFile implements Serializable{

    private byte [] fileData;
    private String contentType;
    private int size;

    public byte[] getFileData() {
        return fileData;
    }

    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
