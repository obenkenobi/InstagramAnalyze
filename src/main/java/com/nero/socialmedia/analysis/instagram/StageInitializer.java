package com.nero.socialmedia.analysis.instagram;

import com.nero.socialmedia.analysis.instagram.configuration.StageConfiguration;
import com.nero.socialmedia.analysis.instagram.events.StageReadyEvent;
import com.nero.socialmedia.analysis.instagram.logger.CustomLoggerFactory;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class StageInitializer implements ApplicationListener<StageReadyEvent> {
    private static final Logger log = CustomLoggerFactory.getLogger(StageInitializer.class);

    private final ConfigurableApplicationContext appContext;
    private final StageConfiguration stageConfiguration;

    private Stage stage;

    public StageInitializer(@Autowired ConfigurableApplicationContext appContext,
                            @Autowired StageConfiguration stageConfiguration) {
        this.appContext = appContext;
        this.stageConfiguration = stageConfiguration;
    }

    @Override
    public void onApplicationEvent(StageReadyEvent event) {
//        Parent parent;

        stage = event.getStage();
        stage.setTitle(stageConfiguration.getTitle());
//        stage.setScene(new Scene(parent, 800,800));
        stage.show();
    }

    public Stage getStage() {
        return stage;
    }

}
