package com.nero.socialmedia.analysis.instagram.models;

import java.util.ArrayList;
import java.util.List;

public class SettingsModel {
    private String googleDriveUsername = "";
    private String googleDrivePassword = "";
    private String googleDriveFilepath = "";
    private List<String> instagramAccountsToTrack = new ArrayList<>();

    public String getGoogleDriveUsername() {
        return googleDriveUsername;
    }

    public void setGoogleDriveUsername(String googleDriveUsername) {
        this.googleDriveUsername = googleDriveUsername;
    }

    public String getGoogleDrivePassword() {
        return googleDrivePassword;
    }

    public void setGoogleDrivePassword(String googleDrivePassword) {
        this.googleDrivePassword = googleDrivePassword;
    }

    public String getGoogleDriveFilepath() {
        return googleDriveFilepath;
    }

    public void setGoogleDriveFilepath(String googleDriveFilepath) {
        this.googleDriveFilepath = googleDriveFilepath;
    }

    public List<String> getInstagramAccountsToTrack() {
        return instagramAccountsToTrack;
    }

    public void setInstagramAccountsToTrack(List<String> instagramAccountsToTrack) {
        this.instagramAccountsToTrack = instagramAccountsToTrack;
    }
}
