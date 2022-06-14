package com.nero.socialmedia.analysis.instagram.desktop.tray;

import com.nero.socialmedia.analysis.instagram.configuration.TrayConfiguration;
import com.nero.socialmedia.analysis.instagram.desktop.StageAccessor;
import com.nero.socialmedia.analysis.instagram.services.ExitApplicationService;
import javafx.application.Platform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.*;

@Component
public class TrayMenuComponent {

    private final StageAccessor stageAccessor;
    private final TrayConfiguration trayConfiguration;
    private final ExitApplicationService exitApplicationService;
    private final TrayIconComponent trayIconComponent;

    private final PopupMenu popup;

    public TrayMenuComponent(@Autowired StageAccessor stageAccessor,
                             @Autowired TrayConfiguration trayConfiguration,
                             @Autowired ExitApplicationService exitApplicationService,
                             @Autowired TrayIconComponent trayIconComponent) {
        this.stageAccessor = stageAccessor;
        this.trayConfiguration = trayConfiguration;
        this.exitApplicationService = exitApplicationService;
        this.trayIconComponent = trayIconComponent;
        this.popup = new PopupMenu();
        try {
            setupTray();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    private void setupTray() throws AWTException {
        // Create a pop-up menu components
        MenuItem exitItem = new MenuItem(trayConfiguration.getExitMenuItemLabel());
        exitItem.addActionListener(e -> exitApplicationService.exitApplication());

        MenuItem openItem = new MenuItem(trayConfiguration.getOpenMenuItemLabel());
        openItem.addActionListener(e -> openConfigurationGui());

        popup.add(openItem);
        popup.addSeparator();
        popup.add(exitItem);
        trayIconComponent.setPopupMenu(popup);
    }

    private void openConfigurationGui() {
        Platform.runLater(() -> stageAccessor.getStage().show());
    }
}
