package com.nero.socialmedia.analysis.instagram.configuration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "settings")
@Component
@RefreshScope
@Data
public class SettingsConfiguration {
    private SettingField googleDriveUsername;
    private SettingField googleDrivePassword;
    private SettingField googleDriveFilepath;
    private SettingField instagramAccountsToTrack;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SettingField {
        private String fieldName;
        private boolean multiple = false;
    }
}
