package com.nero.socialmedia.analysis.instagram;

import com.nero.socialmedia.analysis.instagram.desktop.InstagramAnalyzeFXApplication;
import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class InstagramAnalyzeDesktopApplication {
    public static ConfigurableApplicationContext context;

    public static void main(String[] args) {
        Application.launch(InstagramAnalyzeFXApplication.class, args);
    }

}
