package com.nero.socialmedia.analysis.instagram.desktop.tray;

import com.nero.socialmedia.analysis.instagram.configuration.TrayConfiguration;
import com.nero.socialmedia.analysis.instagram.logger.CustomLoggerFactory;
import com.nero.socialmedia.analysis.instagram.services.ResourceService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

@Component
public class TrayIconComponent extends TrayIcon {
    private static final Logger log = CustomLoggerFactory.getLogger(TrayIconComponent.class);

    private final SystemTray tray;

    public TrayIconComponent(@Autowired ResourceService resourceService,
                             @Autowired TrayConfiguration trayConfiguration) {
        super(createImage(resourceService.getIconsTrayResource(), trayConfiguration.getTooltip()),
                trayConfiguration.getTooltip());
        this.tray = SystemTray.getSystemTray();
        try {
            this.tray.add(this);
        } catch (AWTException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public SystemTray getTray() {
        return tray;
    }

    private static Image createImage(Resource imageResource, String description) {
        try {
            URL imageURL = imageResource.getURL();
            return new ImageIcon(imageURL, description).getImage();
        } catch (IOException e) {
            String errorMsg = "Failed Creating Image. Resource not found: " + imageResource.getFilename();
            log.error(errorMsg);
            throw new IllegalArgumentException(errorMsg);
        }
    }
}
