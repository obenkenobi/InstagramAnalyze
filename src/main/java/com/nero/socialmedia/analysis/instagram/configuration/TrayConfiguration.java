package com.nero.socialmedia.analysis.instagram.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "ui.tray")
@Configuration
@RefreshScope
public class TrayConfiguration {
    private String openMenuItemLabel;
    private String exitMenuItemLabel;
    private String tooltip;

    public String getOpenMenuItemLabel() {
        return openMenuItemLabel;
    }

    public void setOpenMenuItemLabel(String openMenuItemLabel) {
        this.openMenuItemLabel = openMenuItemLabel;
    }

    public String getExitMenuItemLabel() {
        return exitMenuItemLabel;
    }

    public void setExitMenuItemLabel(String exitMenuItemLabel) {
        this.exitMenuItemLabel = exitMenuItemLabel;
    }

    public String getTooltip() {
        return tooltip;
    }

    public void setTooltip(String tooltip) {
        this.tooltip = tooltip;
    }
}
