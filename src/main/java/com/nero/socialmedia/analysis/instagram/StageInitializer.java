package com.nero.socialmedia.analysis.instagram;

import com.nero.socialmedia.analysis.instagram.configuration.GuiConfiguration;
import com.nero.socialmedia.analysis.instagram.events.StageReadyEvent;
import com.nero.socialmedia.analysis.instagram.models.SettingsModel;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StageInitializer implements ApplicationListener<StageReadyEvent> {

    private final ConfigurableApplicationContext appContext;
    private final GuiConfiguration guiConfiguration;

    @Getter
    private Stage stage;

    // State
    @Getter @Setter
    private SettingsModel settingsModel;

    @Override
    public void onApplicationEvent(StageReadyEvent event) {
        stage = event.getStage();
        setupStage(stage);
//        new CustomTrayIcon(appContext, stage);
    }

    private void setupStage(Stage stage) {
        stage.setTitle(guiConfiguration.getTitle());
        stage.show();
    }
}
