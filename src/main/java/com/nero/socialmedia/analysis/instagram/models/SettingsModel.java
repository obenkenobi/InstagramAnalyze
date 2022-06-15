package com.nero.socialmedia.analysis.instagram.models;

import com.nero.socialmedia.analysis.instagram.constants.CalcFrequency;

import java.util.HashSet;
import java.util.Set;

public class SettingsModel {
    private String localDirectoryPath = "";

    private Set<String> instagramAccountsToTrack = new HashSet<>();

    private Set<CalcFrequency> calculationFrequencies = new HashSet<>();

    public Set<CalcFrequency> getCalculationFrequencies() {
        return calculationFrequencies;
    }

    public void setCalculationFrequencies(Set<CalcFrequency> calculationFrequencies) {
        this.calculationFrequencies = calculationFrequencies;
    }

    public Set<String> getInstagramAccountsToTrack() {
        return instagramAccountsToTrack;
    }

    public void setInstagramAccountsToTrack(Set<String> instagramAccountsToTrack) {
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

        public SettingModelBuilder instagramAccountsToTrack(Set<String> instagramAccountsToTrack) {
            settingsModel.setInstagramAccountsToTrack(instagramAccountsToTrack);
            return this;
        }

        public SettingModelBuilder calculationFrequencies(Set<CalcFrequency> instagramAccountsToTrack) {
            settingsModel.setCalculationFrequencies(instagramAccountsToTrack);
            return this;
        }

        public SettingsModel build() {
            return settingsModel;
        }
    }
}
