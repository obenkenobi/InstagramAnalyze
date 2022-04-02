package com.nero.socialmedia.analysis.instagram;

import javafx.application.Application;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@Slf4j
@SpringBootApplication
public class InstagramAnalyzeSpringApplication {

    public static ConfigurableApplicationContext context;

    public static void main(String[] args) {
        Application.launch(InstagramAnalyzeGuiApplication.class, args);
    }

}
