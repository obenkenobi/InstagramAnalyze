package com.nero.socialmedia.analysis.instagram;

import com.nero.socialmedia.analysis.instagram.logger.CustomLoggerFactory;
import javafx.application.Application;
import org.slf4j.Logger;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class InstagramAnalyzeSpringApplication {
    private static final Logger log = CustomLoggerFactory.getLogger(InstagramAnalyzeSpringApplication.class);

    public static ConfigurableApplicationContext context;

    public static void main(String[] args) {
        Application.launch(InstagramAnalyzeGuiApplication.class, args);
    }

}
