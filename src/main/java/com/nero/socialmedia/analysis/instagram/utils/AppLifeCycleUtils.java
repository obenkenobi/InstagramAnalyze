package com.nero.socialmedia.analysis.instagram.utils;

import com.nero.socialmedia.analysis.instagram.tray.CustomTrayIcon;
import javafx.application.Platform;
import org.springframework.context.ConfigurableApplicationContext;

public class AppLifeCycleUtils {
    public static void exit(ConfigurableApplicationContext applicationContext, CustomTrayIcon customTrayIcon) {
        final int exitCode = 0;
        customTrayIcon.getTray().remove(customTrayIcon);
        applicationContext.close();
        Platform.exit();
    }
}
