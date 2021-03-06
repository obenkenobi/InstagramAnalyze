package com.nero.socialmedia.analysis.instagram.desktop;

import com.nero.socialmedia.analysis.instagram.InstagramAnalyzeDesktopApplication;
import com.nero.socialmedia.analysis.instagram.events.StageReadyEvent;
import com.nero.socialmedia.analysis.instagram.logger.CustomLoggerFactory;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

public class InstagramAnalyzeFXApplication extends Application {
    private static final Logger log = CustomLoggerFactory.getLogger(InstagramAnalyzeFXApplication.class);

    private ConfigurableApplicationContext applicationContext;

    @Override
    public void init() {
        List<String> argsList = super.getParameters().getRaw();
        SpringApplicationBuilder ctxBuilder = new SpringApplicationBuilder(InstagramAnalyzeDesktopApplication.class);
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
        log.info("User interface stopped");
    }

}
