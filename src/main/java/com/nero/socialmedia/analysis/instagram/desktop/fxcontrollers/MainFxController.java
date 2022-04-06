package com.nero.socialmedia.analysis.instagram.desktop.fxcontrollers;

import com.nero.socialmedia.analysis.instagram.logger.CustomLoggerFactory;
import com.nero.socialmedia.analysis.instagram.models.SettingsModel;
import com.nero.socialmedia.analysis.instagram.services.SettingsService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class MainFxController {
    private static final Logger log = CustomLoggerFactory.getLogger(MainFxController.class);

    private final SettingsService settingsService;

    @FXML
    public AnchorPane googleAccountPane;
    @FXML
    public AnchorPane settingsPane;
    @FXML
    public TextField googleEmailPhoneTextField;
    @FXML
    public PasswordField googlePasswordTextField;
    @FXML
    public Button googleSignInButton;

    public MainFxController(@Autowired SettingsService settingsService) {
        this.settingsService = settingsService;
    }

    @FXML
    public void initialize() {
        settingsService.updateSettings(new SettingsModel());
        loadSettings();
    }

    public void loadSettings() {
        log.info("load settings");
        log.info("{}", settingsService.getSettings());
//        testText.setText("Accounts count:" + settingsService.getSettings().getInstagramAccountsToTrack().size());
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

    public void signInGoogle(ActionEvent actionEvent) {
        String googleAccountName = googleEmailPhoneTextField.getText();
        String googlePassword = googlePasswordTextField.getText();
        // Todo sign in
        log.info("Username {} Password {}", googleAccountName, googlePassword);
        updateSettings(actionEvent);
    }
}
