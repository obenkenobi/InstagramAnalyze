package com.nero.socialmedia.analysis.instagram.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "tray")
@Component
@RefreshScope
@Data
public class TrayConfiguration {
    private String openMenuItemLabel;
    private String exitMenuItemLabel;
    private String tooltip;
}
