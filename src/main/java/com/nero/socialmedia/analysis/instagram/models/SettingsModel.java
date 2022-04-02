package com.nero.socialmedia.analysis.instagram.models;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
public class SettingsModel {
    private String googleDriveUsername;
    private String googleDrivePassword;
    private String googleDriveFilepath;
    private List<String> instagramAccountsToTrack;
}
