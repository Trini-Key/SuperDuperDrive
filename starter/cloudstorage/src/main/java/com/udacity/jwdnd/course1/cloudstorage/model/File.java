package com.udacity.jwdnd.course1.cloudstorage.model;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Blob;

public class File {

    private Integer fileId;
    private String filename;
    private String contentype;
    private String filesize;
    private Integer userId;
    private byte[] filedata;

    public File(Integer fileId, String filename, String contentype, String filesize, Integer userId, byte[] filedata) {
        this.fileId = fileId;
        this.filename = filename;
        this.contentype = contentype;
        this.filesize = filesize;
        this.userId = userId;
        this.filedata = filedata;
    }

    public File() {
    }

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(MultipartFile mpFile) {
        this.filename = mpFile.getName();
    }

    public String getContentype() {
        return contentype;
    }

    public void setContentype(MultipartFile mpFile) {
        this.contentype = mpFile.getContentType();
    }

    public String getFilesize() {
        return filesize;
    }

    public void setFilesize(String mpFile) {
        this.filesize = filesize;
    }

    public byte[] getFiledata() {
        return filedata;
    }

    public void setFiledata(byte[] filedata) {
        this.filedata = filedata;
    }
}
