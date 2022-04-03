package com.nero.socialmedia.analysis.instagram.tray;

import com.nero.socialmedia.analysis.instagram.StageInitializer;
import com.nero.socialmedia.analysis.instagram.configuration.TrayConfiguration;
import com.nero.socialmedia.analysis.instagram.services.ResourceService;
import com.nero.socialmedia.analysis.instagram.utils.AppLifeCycleUtils;
import javafx.application.Platform;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

@Slf4j
@Component
public class CustomTrayIcon extends TrayIcon {
    private final ConfigurableApplicationContext applicationContext;
    private final ResourceService resourceService;
    private final StageInitializer stageInitializer;
    private final TrayConfiguration trayConfiguration;

    private final PopupMenu popup;
    @Getter
    private final SystemTray tray;

    public CustomTrayIcon(@Autowired ConfigurableApplicationContext applicationContext,
                          @Autowired ResourceService resourceService,
                          @Autowired StageInitializer stageInitializer,
                          @Autowired TrayConfiguration trayConfiguration) {
        super(createImage(resourceService.getTrayIconResource(), trayConfiguration.getTooltip()),
                trayConfiguration.getTooltip());
        this.resourceService = resourceService;
        this.trayConfiguration = trayConfiguration;
        this.applicationContext = applicationContext;
        this.stageInitializer = stageInitializer;

        this.popup = new PopupMenu();
        this.tray = SystemTray.getSystemTray();
        try {
            setupTray();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    private void setupTray() throws AWTException {
        // Create a pop-up menu components
        MenuItem exitItem = new MenuItem(trayConfiguration.getExitMenuItemLabel());
        exitItem.addActionListener(e -> AppLifeCycleUtils.exit(applicationContext, this));

        MenuItem openItem = new MenuItem(trayConfiguration.getOpenMenuItemLabel());
        openItem.addActionListener(e -> openConfigurationGui());

        popup.add(openItem);
        popup.addSeparator();
        popup.add(exitItem);
        setPopupMenu(popup);
        tray.add(this);
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

    private void openConfigurationGui() {
        Platform.runLater(() -> stageInitializer.getStage().show());
    }
}
