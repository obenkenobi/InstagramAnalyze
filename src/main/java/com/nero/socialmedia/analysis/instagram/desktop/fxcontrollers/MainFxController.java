package com.nero.socialmedia.analysis.instagram.desktop.fxcontrollers;

import com.nero.socialmedia.analysis.instagram.constants.CalcFrequency;
import com.nero.socialmedia.analysis.instagram.constants.ProfileNames;
import com.nero.socialmedia.analysis.instagram.desktop.StageAccessor;
import com.nero.socialmedia.analysis.instagram.logger.CustomLoggerFactory;
import com.nero.socialmedia.analysis.instagram.models.SettingsModel;
import com.nero.socialmedia.analysis.instagram.services.SettingsService;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class MainFxController {
    private static final Logger log = CustomLoggerFactory.getLogger(MainFxController.class);

    // Autowired
    private final SettingsService settingsService;
    private final StageAccessor stageAccessor;
    private final Environment environment;

    // UI components
    @FXML public TextField directorySaveLocationTextField;
    @FXML public TextField newInstagramAccountTextField;
    @FXML public ListView<String> instagramAccountsListView;
    @FXML public CheckBox calcFreqMonthlyCheckBox;
    @FXML public CheckBox calcFreqWeeklyCheckBox;
    @FXML public CheckBox calcFreqDailyCheckBox;
    @FXML public CheckBox calcFreqHourlyCheckBox;
    @FXML public CheckBox calcFreqMinutelyCheckBox;
    @FXML public CheckBox calcFreqTenSecondsCheckBox;

    // State
    private SettingsModel settingsModel = new SettingsModel();

    public MainFxController(@Autowired SettingsService settingsService, @Autowired StageAccessor stageAccessor,
                            @Autowired Environment environment) {
        this.settingsService = settingsService;
        this.stageAccessor = stageAccessor;
        this.environment = environment;
    }

    @FXML
    public void initialize() {
        Set<String> profiles = Arrays.stream(environment.getActiveProfiles()).collect(Collectors.toSet());
        boolean isDev = profiles.contains(ProfileNames.DEV);
        calcFreqTenSecondsCheckBox.setVisible(isDev && profiles.contains(ProfileNames.SCHEDULE_TEN_SECONDS));
        calcFreqMinutelyCheckBox.setVisible(isDev && profiles.contains(ProfileNames.SCHEDULE_MINUTELY));
        loadSettings();
    }

    private void loadSettings() {
        settingsModel = settingsService.getSettings();

        // State bindings
        setFileLocationState(settingsModel.getLocalDirectoryPath());
        setInstagramAccountsState(settingsModel.getInstagramAccountsToTrack());
        setCalculationFrequenciesState(settingsModel.getCalculationFrequencies());
        log.info("load settings");
        log.info("{}", settingsModel);
        log.info("settings loaded");
    }

    public void onDirectorySaveLocation() {
        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle("Directory to save data");
        log.info("Current location state");
        File defaultDirectory = new File(directorySaveLocationTextField.getText());
        if (!defaultDirectory.exists())  {
            defaultDirectory = FileSystemView.getFileSystemView().getDefaultDirectory();
        }
        chooser.setInitialDirectory(defaultDirectory);
        File selectedDirectory = chooser.showDialog(stageAccessor.getStage());
        setFileLocationState(selectedDirectory.getAbsolutePath());
    }

    public void onAddInstagramAccount() {
        String account = newInstagramAccountTextField.getText();
        newInstagramAccountTextField.clear();
        Set<String> accounts = getInstagramAccountsState();
        if (!StringUtils.hasText(account) || accounts.contains(account)) {
            return;
        }
        accounts.add(account);
        setInstagramAccountsState(accounts);
    }

    public void onDeleteSelectedInstagramAccount() {
        Set<String> accountsToRemove = new HashSet<>(instagramAccountsListView.getSelectionModel().getSelectedItems());
        Set<String> accountsAfterRemoval = getInstagramAccountsState().stream()
                .filter(a -> !accountsToRemove.contains(a))
                .collect(Collectors.toSet());
        setInstagramAccountsState(accountsAfterRemoval);
    }

    public void onClearInstagramAccounts() {
        setInstagramAccountsState(new HashSet<>());
    }

    public void onToggleMonthly() {
        updateCalculateFrequencies(calcFreqMonthlyCheckBox.isSelected(), CalcFrequency.MONTHLY);
    }

    public void onToggleWeekly() {
        updateCalculateFrequencies(calcFreqWeeklyCheckBox.isSelected(), CalcFrequency.WEEKLY);
    }

    public void onToggleDaily() {
        updateCalculateFrequencies(calcFreqDailyCheckBox.isSelected(), CalcFrequency.DAILY);
    }

    public void onToggleHourly() {
        updateCalculateFrequencies(calcFreqHourlyCheckBox.isSelected(), CalcFrequency.HOURLY);
    }

    public void onToggleMinutely() {
        updateCalculateFrequencies(calcFreqMinutelyCheckBox.isSelected(), CalcFrequency.MINUTELY);
    }

    public void onToggleTenSeconds() {
        updateCalculateFrequencies(calcFreqTenSecondsCheckBox.isSelected(), CalcFrequency.TEN_SECONDS);
    }

    public void onUndoChanges() {
        loadSettings();
    }

    public void onSaveChanges() {
        log.info("Updating settings");
        settingsService.updateSettings(settingsModel);
        loadSettings();
    }

    private void updateCalculateFrequencies(boolean selected, CalcFrequency frequency) {
        Set<CalcFrequency> calcFrequencies = getCalculationFrequenciesState();
        if (selected) {
            calcFrequencies.add(frequency);
        } else {
            calcFrequencies.remove(frequency);
        }
        setCalculationFrequenciesState(calcFrequencies);
    }

    // State management

    private void setFileLocationState(String fileLocation) {
        settingsModel.setLocalDirectoryPath(fileLocation);
        directorySaveLocationTextField.setText(fileLocation);
    }

    private String getFileLocationState() {
        return settingsModel.getLocalDirectoryPath();
    }

    private void setInstagramAccountsState(Set<String> instagramAccounts) {
        settingsModel.setInstagramAccountsToTrack(instagramAccounts);
        ObservableList<String> items = instagramAccountsListView.getItems();
        items.setAll(instagramAccounts);
        items.sort(Comparator.comparing(Function.identity()));
        instagramAccountsListView.setItems(items);
    }

    private Set<String> getInstagramAccountsState() {
        return settingsModel.getInstagramAccountsToTrack();
    }

    private void setCalculationFrequenciesState(Set<CalcFrequency> calculationFrequencies) {
        settingsModel.setCalculationFrequencies(calculationFrequencies);
        calcFreqMonthlyCheckBox.setSelected(calculationFrequencies.contains(CalcFrequency.MONTHLY));
        calcFreqWeeklyCheckBox.setSelected(calculationFrequencies.contains(CalcFrequency.WEEKLY));
        calcFreqDailyCheckBox.setSelected(calculationFrequencies.contains(CalcFrequency.DAILY));
        calcFreqHourlyCheckBox.setSelected(calculationFrequencies.contains(CalcFrequency.HOURLY));
        calcFreqMinutelyCheckBox.setSelected(calculationFrequencies.contains(CalcFrequency.MINUTELY));
        calcFreqTenSecondsCheckBox.setSelected(calculationFrequencies.contains(CalcFrequency.TEN_SECONDS));
    }

    private Set<CalcFrequency> getCalculationFrequenciesState() {
        return settingsModel.getCalculationFrequencies();
    }
}
