package com.nero.socialmedia.analysis.instagram;

import com.nero.socialmedia.analysis.instagram.events.StageReadyEvent;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

public class InstagramAnalyzeGuiApplication extends Application {

    private ConfigurableApplicationContext applicationContext;

    @Override
    public void init() {
        List<String> argsList = super.getParameters().getRaw();
        SpringApplicationBuilder ctxBuilder = new SpringApplicationBuilder(InstagramAnalyzeSpringApplication.class);
        ctxBuilder.headless(false);
        applicationContext = ctxBuilder.run(argsList.toArray(new String[argsList.size()]));
    }

    @Override
    public void start(Stage stage) {
        Platform.setImplicitExit(false);
        applicationContext.publishEvent(new StageReadyEvent(stage));
    }

    @Override
    public void stop() {
    }

}
