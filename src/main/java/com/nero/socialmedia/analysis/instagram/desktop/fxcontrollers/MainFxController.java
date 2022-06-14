package com.nero.socialmedia.analysis.instagram.desktop.fxcontrollers;

import com.nero.socialmedia.analysis.instagram.desktop.StageAccessor;
import com.nero.socialmedia.analysis.instagram.logger.CustomLoggerFactory;
import com.nero.socialmedia.analysis.instagram.models.SettingsModel;
import com.nero.socialmedia.analysis.instagram.services.SettingsService;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.stream.Collectors;

@Component
public class MainFxController {
    private static final Logger log = CustomLoggerFactory.getLogger(MainFxController.class);

    // Autowired
    private final SettingsService settingsService;
    private final StageAccessor stageAccessor;

    // UI components
    @FXML
    public TextField directorySaveLocationTextField;
    @FXML
    public TextField newInstagramAccountTextField;
    @FXML
    public ListView<String> instagramAccountsListView;

    // State
    private SettingsModel settingsModel = new SettingsModel();

    public MainFxController(@Autowired SettingsService settingsService, @Autowired StageAccessor stageAccessor) {
        this.settingsService = settingsService;
        this.stageAccessor = stageAccessor;
    }

    @FXML
    public void initialize() {
        loadSettings();
    }

    private void loadSettings() {
        settingsModel = settingsService.getSettings();

        // State bindings
        setFileLocationState(settingsModel.getLocalDirectoryPath());
        setInstagramAccountsState(settingsModel.getInstagramAccountsToTrack());

        log.info("load settings");
        log.info("{}", settingsModel);
        log.info("settings loaded");
    }

    public void onClickDirectorySaveLocationBtn(ActionEvent event) {
        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle("Directory to save data");
        log.info("Current location state");
        File defaultDirectory = new File(getFileLocationState());
        if (!defaultDirectory.exists())  {
            defaultDirectory = FileSystemView.getFileSystemView().getDefaultDirectory();
        }
        chooser.setInitialDirectory(defaultDirectory);
        File selectedDirectory = chooser.showDialog(stageAccessor.getStage());
        setFileLocationState(selectedDirectory.getAbsolutePath());
    }

    public void onAddInstagramAccount(ActionEvent event) {
        String account = newInstagramAccountTextField.getText();
        List<String> accounts = getInstagramAccountsState();
        if (!StringUtils.hasText(account) || accounts.contains(account)) {
            return;
        }
        accounts.add(account);
        setInstagramAccountsState(accounts);
    }

    public void onSaveSettings(ActionEvent actionEvent) {
        log.info("Updating settings");
        settingsService.updateSettings(settingsModel);
        loadSettings();
    }

    // State management

    private void setFileLocationState(String fileLocation) {
        settingsModel.setLocalDirectoryPath(fileLocation);
        directorySaveLocationTextField.setText(fileLocation);
    }

    private String getFileLocationState() {
        String fileLocation = directorySaveLocationTextField.getText();
        settingsModel.setLocalDirectoryPath(fileLocation);
        return fileLocation;
    }

    private void setInstagramAccountsState(List<String> instagramAccounts) {
        settingsModel.setInstagramAccountsToTrack(instagramAccounts);
        ObservableList<String> items = instagramAccountsListView.getItems();
        items.setAll(instagramAccounts);
        instagramAccountsListView.setItems(items);
    }

    private List<String> getInstagramAccountsState() {
        return new ArrayList<>(instagramAccountsListView.getItems());
    }
}
