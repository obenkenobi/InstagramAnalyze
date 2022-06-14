package com.nero.socialmedia.analysis.instagram.services;

import com.nero.socialmedia.analysis.instagram.configuration.SettingsConfiguration;
import com.nero.socialmedia.analysis.instagram.domain.Setting;
import com.nero.socialmedia.analysis.instagram.models.SettingsModel;
import com.nero.socialmedia.analysis.instagram.repositories.SettingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManagerFactory;
import javax.swing.filechooser.FileSystemView;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class SettingsServiceImpl implements SettingsService {

    private final SettingRepository settingRepository;
    private final SettingsConfiguration settingsConfiguration;
    private final TransactionService transactionService;

    public SettingsServiceImpl(@Autowired SettingRepository settingRepository,
                               @Autowired SettingsConfiguration settingsConfiguration,
                               @Autowired EntityManagerFactory entityManagerFactory,
                               @Autowired TransactionService transactionService) {
        this.settingRepository = settingRepository;
        this.settingsConfiguration = settingsConfiguration;
        this.transactionService = transactionService;
    }

    @Override
    public void updateSettings(SettingsModel settingsModel) {
        transactionService.runTransaction(() -> {
            settingRepository.deleteAll();
            List<Setting> settings = new ArrayList<>();
            settings.add(Setting.builder()
                    .fieldName(settingsConfiguration.getLocalDirectoryPath().getFieldName())
                    .value(settingsModel.getLocalDirectoryPath())
                    .multiple(settingsConfiguration.getLocalDirectoryPath().isMultiple())
                    .build());
            settings.addAll(settingsModel.getInstagramAccountsToTrack().stream()
                    .map(accountToTrack -> Setting.builder()
                            .fieldName(settingsConfiguration.getInstagramAccountsToTrack().getFieldName())
                            .value(accountToTrack)
                            .multiple(settingsConfiguration.getInstagramAccountsToTrack().isMultiple())
                            .build())
                    .collect(Collectors.toList()));
            return settingRepository.saveAll(settings);
        });
    }

    @Override
    public SettingsModel getSettings() {
        List<Setting> settingList = settingRepository.findAll();
        Map<String, Setting> singleSettings = settingList.stream().filter(s -> !s.isMultiple())
                .collect(Collectors.toMap(Setting::getFieldName, Function.identity()));
        Map<String, List<Setting>> multipleSettings = settingList.stream().filter(Setting::isMultiple)
                .collect(Collectors.groupingBy(Setting::getFieldName));
        return SettingsModel.builder()
                .localDirectoryPath(getSingleSettingValue(singleSettings,
                        settingsConfiguration.getLocalDirectoryPath().getFieldName(),
                        Paths.get(FileSystemView.getFileSystemView().getDefaultDirectory().getPath(),
                                settingsConfiguration.getLocalDirectoryPath().getSingleDefault()).toString()))
                .instagramAccountsToTrack(getMultiSettingValue(multipleSettings,
                        settingsConfiguration.getInstagramAccountsToTrack().getFieldName()))
                .build();
    }

    private String getSingleSettingValue(Map<String, Setting> singleSettings, String fieldName) {
        return getSingleSettingValue(singleSettings, fieldName, new Setting().getValue());
    }

    private String getSingleSettingValue(Map<String, Setting> singleSettings, String fieldName, String defaultValue) {
        Setting setting = singleSettings.get(fieldName);
        return setting != null && StringUtils.hasText(setting.getValue()) ? setting.getValue() : defaultValue;
    }

    private List<String> getMultiSettingValue(Map<String, List<Setting>> multipleSettings, String fieldName) {
        return multipleSettings.getOrDefault(fieldName, new ArrayList<>()).stream()
                .map(Setting::getValue).collect(Collectors.toList());
    }
}
