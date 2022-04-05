package com.nero.socialmedia.analysis.instagram.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "settings")
@Component
@RefreshScope
public class SettingsConfiguration {
    private SettingField googleDriveFilepath;
    private SettingField instagramAccountsToTrack;

    public SettingField getGoogleDriveFilepath() {
        return googleDriveFilepath;
    }

    public void setGoogleDriveFilepath(SettingField googleDriveFilepath) {
        this.googleDriveFilepath = googleDriveFilepath;
    }

    public SettingField getInstagramAccountsToTrack() {
        return instagramAccountsToTrack;
    }

    public void setInstagramAccountsToTrack(SettingField instagramAccountsToTrack) {
        this.instagramAccountsToTrack = instagramAccountsToTrack;
    }

    public static class SettingField {
        private String fieldName;
        private boolean multiple = false;

        public String getFieldName() {
            return fieldName;
        }

        public void setFieldName(String fieldName) {
            this.fieldName = fieldName;
        }

        public boolean isMultiple() {
            return multiple;
        }

        public void setMultiple(boolean multiple) {
            this.multiple = multiple;
        }
    }
}
