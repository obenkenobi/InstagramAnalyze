package com.nero.socialmedia.analysis.instagram.tray;

import com.nero.socialmedia.analysis.instagram.utils.AppLifeCycleUtils;
import javafx.application.Platform;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ConfigurableApplicationContext;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.awt.*;
import java.net.URL;

@Slf4j
public class CustomTrayIcon extends TrayIcon {
    private static final String IMAGE_PATH = "/icons/tray.png";
    private static final String TOOLTIP = "Running instagram analyze application";
    private static final String CONFIG_OPEN_LINK = "http://localhost:4269/configuration";

    private final PopupMenu popup;
    @Getter
    private final SystemTray tray;
    private final ConfigurableApplicationContext applicationContext;
    private final Stage stage;

    public CustomTrayIcon(ConfigurableApplicationContext applicationContext, Stage stage) {
        super(createImage(IMAGE_PATH, TOOLTIP), TOOLTIP);
        popup = new PopupMenu();
        tray = SystemTray.getSystemTray();
        this.applicationContext = applicationContext;
        this.stage = stage;
        try {
            setup();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    @PostConstruct
    private void setup() throws AWTException {
        // Create a pop-up menu components
        MenuItem exitItem = new MenuItem("Exit");
        exitItem.addActionListener(e -> AppLifeCycleUtils.exit(applicationContext, this));

        MenuItem linkItem = new MenuItem("Open");
        linkItem.addActionListener(e -> openConfigurationGui());

        popup.add(linkItem);
        popup.addSeparator();
        popup.add(exitItem);
        setPopupMenu(popup);
        tray.add(this);
    }

    private static Image createImage(String path, String description) {
        URL imageURL = CustomTrayIcon.class.getResource(path);
        if (imageURL == null) {
            String errorMsg = "Failed Creating Image. Resource not found: " + path;
            log.error(errorMsg);
            throw new IllegalArgumentException(errorMsg);
        } else {
            return new ImageIcon(imageURL, description).getImage();
        }
    }

    private void openConfigurationGui() {
        try {
            Platform.runLater(this.stage::show);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
