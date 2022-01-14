package com.udacity.jwdnd.course1.cloudstorage.model;

public class HomeForm {

    private String filename;
    private String noteTitle;
    private String noteDescription;
    private String credURL;
    private String credUsername;
    private String credPassword;

    public HomeForm(String filename) {
        this.filename = filename;
    }

    public HomeForm(String noteTitle, String noteDescription) {
        this.noteTitle = noteTitle;
        this.noteDescription = noteDescription;
    }

    public HomeForm(String credURL, String credUsername, String credPassword) {
        this.credURL = credURL;
        this.credUsername = credUsername;
        this.credPassword = credPassword;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public String getNoteDescription() {
        return noteDescription;
    }

    public void setNoteDescription(String noteDescription) {
        this.noteDescription = noteDescription;
    }

    public String getCredURL() {
        return credURL;
    }

    public void setCredURL(String credURL) {
        this.credURL = credURL;
    }

    public String getCredUsername() {
        return credUsername;
    }

    public void setCredUsername(String credUsername) {
        this.credUsername = credUsername;
    }

    public String getCredPassword() {
        return credPassword;
    }

    public void setCredPassword(String credPassword) {
        this.credPassword = credPassword;
    }
}
