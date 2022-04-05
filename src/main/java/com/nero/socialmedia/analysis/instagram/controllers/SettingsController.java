package com.nero.socialmedia.analysis.instagram.controllers;

import com.nero.socialmedia.analysis.instagram.logger.CustomLoggerFactory;
import com.nero.socialmedia.analysis.instagram.models.SettingsModel;
import com.nero.socialmedia.analysis.instagram.services.SettingsService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class SettingsController {
    private static final Logger log = CustomLoggerFactory.getLogger(SettingsController.class);

    private final SettingsService settingsService;

    @FXML
    public Button testButton;
    @FXML
    public TextField testText;

    public SettingsController(@Autowired SettingsService settingsService) {
        this.settingsService = settingsService;
    }

    @FXML
    public void initialize() {
        settingsService.updateSettings(new SettingsModel());
        loadSettings();
    }

    public void loadSettings() {
        log.info("load settings");
        testText.setText("Accounts count:" + settingsService.getSettings().getInstagramAccountsToTrack().size());
        log.info("settings loaded");
    }

    public void updateSettings(ActionEvent actionEvent) {
        log.info("Updating settings");
        SettingsModel settingsModel = new SettingsModel();
        settingsModel.setInstagramAccountsToTrack(Arrays.asList("t1", "t2"));
        settingsModel.setGoogleDriveFilepath("folder/subfolder");
        settingsService.updateSettings(settingsModel);
        loadSettings();
    }
}
