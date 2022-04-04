package com.nero.socialmedia.analysis.instagram.services;

import com.nero.socialmedia.analysis.instagram.tray.TrayIconComponent;
import javafx.application.Platform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class ExitApplicationServiceImpl implements ExitApplicationService {

    private final ConfigurableApplicationContext applicationContext;
    private final TrayIconComponent trayIconComponent;

    public ExitApplicationServiceImpl(@Autowired ConfigurableApplicationContext applicationContext,
                                      @Autowired TrayIconComponent trayIconComponent) {
        this.applicationContext = applicationContext;
        this.trayIconComponent = trayIconComponent;
    }

    @Override
    public void exitApplication() {
        final int exitCode = 0;
        trayIconComponent.getTray().remove(trayIconComponent);
        applicationContext.close();
        Platform.exit();
    }
}
