package com.nero.socialmedia.analysis.instagram.models;

import java.util.ArrayList;
import java.util.List;

public class SettingsModel {
    private String localFilePath = "";
    private String googleDriveFilepath = "";
    private List<String> instagramAccountsToTrack = new ArrayList<>();

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

    public String getLocalFilePath() {
        return localFilePath;
    }

    public void setLocalFilePath(String localFilePath) {
        this.localFilePath = localFilePath;
    }
}
