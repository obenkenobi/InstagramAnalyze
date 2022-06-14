package com.nero.socialmedia.analysis.instagram.models;

import java.util.ArrayList;
import java.util.List;

public class SettingsModel {
    private String localDirectoryPath = "";
    private List<String> instagramAccountsToTrack = new ArrayList<>();

    public List<String> getInstagramAccountsToTrack() {
        return instagramAccountsToTrack;
    }

    public void setInstagramAccountsToTrack(List<String> instagramAccountsToTrack) {
        this.instagramAccountsToTrack = instagramAccountsToTrack;
    }

    public String getLocalDirectoryPath() {
        return localDirectoryPath;
    }

    public void setLocalDirectoryPath(String localDirectoryPath) {
        this.localDirectoryPath = localDirectoryPath;
    }

    public static SettingModelBuilder builder() {
        return new SettingsModel.SettingModelBuilder();
    }

    public static class SettingModelBuilder {
        private final SettingsModel settingsModel = new SettingsModel();

        private SettingModelBuilder() {}

        public SettingModelBuilder localDirectoryPath(String localDirectoryPath) {
            settingsModel.setLocalDirectoryPath(localDirectoryPath);
            return this;
        }

        public SettingModelBuilder instagramAccountsToTrack(List<String> instagramAccountsToTrack) {
            settingsModel.setInstagramAccountsToTrack(instagramAccountsToTrack);
            return this;
        }

        public SettingsModel build() {
            return settingsModel;
        }
    }
}
