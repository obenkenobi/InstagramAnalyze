package com.nero.socialmedia.analysis.instagram.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "settings")
@Component
@RefreshScope
public class SettingsConfiguration {
    private SettingField localDirectoryPath;
    private SettingField instagramAccountsToTrack;
    private SettingField calculationFrequency;

    public SettingField getCalculationFrequency() {
        return calculationFrequency;
    }

    public void setCalculationFrequency(SettingField calculationFrequency) {
        this.calculationFrequency = calculationFrequency;
    }

    public SettingField getLocalDirectoryPath() {
        return localDirectoryPath;
    }

    public void setLocalDirectoryPath(SettingField localDirectoryPath) {
        this.localDirectoryPath = localDirectoryPath;
    }

    public SettingField getInstagramAccountsToTrack() {
        return instagramAccountsToTrack;
    }

    public void setInstagramAccountsToTrack(SettingField instagramAccountsToTrack) {
        this.instagramAccountsToTrack = instagramAccountsToTrack;
    }

    public static class SettingField {
        private String singleDefault;
        private String fieldName;
        private boolean multiple = false;

        public String getSingleDefault() {
            return singleDefault;
        }

        public void setSingleDefault(String singleDefault) {
            this.singleDefault = singleDefault;
        }

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
