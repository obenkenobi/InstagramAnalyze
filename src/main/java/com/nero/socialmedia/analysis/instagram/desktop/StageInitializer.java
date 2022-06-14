package com.nero.socialmedia.analysis.instagram.desktop;

import com.nero.socialmedia.analysis.instagram.configuration.StageConfiguration;
import com.nero.socialmedia.analysis.instagram.events.StageReadyEvent;
import com.nero.socialmedia.analysis.instagram.logger.CustomLoggerFactory;
import com.nero.socialmedia.analysis.instagram.services.ResourceService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class StageInitializer implements ApplicationListener<StageReadyEvent> {
    private static final Logger log = CustomLoggerFactory.getLogger(StageInitializer.class);

    private final StageConfiguration stageConfiguration;
    private final ResourceService resourceService;
    private final ApplicationContext applicationContext;
    private final StageAccessor stageAccessor;

    private Stage stage;

    public StageInitializer(@Autowired StageConfiguration stageConfiguration,
                            @Autowired ResourceService resourceService,
                            @Autowired ApplicationContext applicationContext,
                            @Autowired StageAccessor stageAccessor) {
        this.stageConfiguration = stageConfiguration;
        this.resourceService = resourceService;
        this.applicationContext = applicationContext;
        this.stageAccessor = stageAccessor;
    }

    @Override
    public void onApplicationEvent(StageReadyEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(resourceService.getFxmlMainResource().getURL());
            fxmlLoader.setControllerFactory(applicationContext::getBean);
            Parent parent = fxmlLoader.load();
            stage = event.getStage();
            stage.setTitle(stageConfiguration.getTitle());
            stage.setScene(new Scene(parent, 800,800));
            stage.show();
            stageAccessor.setStage(stage);
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage(), e);
        }
    }

}
