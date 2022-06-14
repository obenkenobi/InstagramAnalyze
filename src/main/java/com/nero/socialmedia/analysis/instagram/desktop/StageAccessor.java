package com.nero.socialmedia.analysis.instagram.desktop;

import javafx.stage.Stage;
import org.springframework.stereotype.Component;

@Component
public class StageAccessor {

    private Stage stage;

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
