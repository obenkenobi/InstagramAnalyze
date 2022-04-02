package com.nero.socialmedia.analysis.instagram.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "gui")
@Component
@RefreshScope
@Data
public class GuiConfiguration {
    private String title;
}
