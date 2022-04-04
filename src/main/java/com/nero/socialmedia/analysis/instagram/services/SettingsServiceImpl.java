package com.nero.socialmedia.analysis.instagram.services;

import com.nero.socialmedia.analysis.instagram.configuration.SettingsConfiguration;
import com.nero.socialmedia.analysis.instagram.domain.Setting;
import com.nero.socialmedia.analysis.instagram.logger.CustomLoggerFactory;
import com.nero.socialmedia.analysis.instagram.models.SettingsModel;
import com.nero.socialmedia.analysis.instagram.repositories.SettingRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class SettingsServiceImpl implements SettingsService {
    private static Logger log = CustomLoggerFactory.getLogger(SettingsServiceImpl.class);

    private final SettingRepository settingRepository;
    private final SettingsConfiguration settingsConfiguration;

    public SettingsServiceImpl(@Autowired SettingRepository settingRepository,
                               @Autowired SettingsConfiguration settingsConfiguration) {
        this.settingRepository = settingRepository;
        this.settingsConfiguration = settingsConfiguration;
    }

    @Override
    @Transactional
    public SettingsModel updateSettings(SettingsModel settingsModel) {
        settingRepository.deleteAll();
        List<Setting> settings = new ArrayList<>();
        settings.add(create(settingsConfiguration.getGoogleDriveUsername().getFieldName(),
                settingsModel.getGoogleDriveUsername(),
                settingsConfiguration.getGoogleDriveUsername().isMultiple()));
        settings.add(create(settingsConfiguration.getGoogleDrivePassword().getFieldName(),
                settingsModel.getGoogleDrivePassword(),
                settingsConfiguration.getGoogleDrivePassword().isMultiple()));
        settings.add(create(settingsConfiguration.getGoogleDriveFilepath().getFieldName(),
                settingsModel.getGoogleDriveFilepath(),
                settingsConfiguration.getGoogleDriveFilepath().isMultiple()));
        settings.addAll(settingsModel.getInstagramAccountsToTrack().stream()
                .map(accountToTrack -> create(settingsConfiguration.getInstagramAccountsToTrack().getFieldName(),
                        accountToTrack, settingsConfiguration.getInstagramAccountsToTrack().isMultiple()))
                .collect(Collectors.toList()));
        settingRepository.saveAll(settings);
        return getSettings();
    }

    @Override
    public SettingsModel getSettings() {
        List<Setting> settingList = settingRepository.findAll();
        Map<String, Setting> singleSettings = settingList.stream().filter(s -> !s.isMultiple())
                .collect(Collectors.toMap(Setting::getFieldName, Function.identity()));
        Map<String, List<Setting>> multipleSettings = settingList.stream().filter(Setting::isMultiple)
                .collect(Collectors.groupingBy(Setting::getFieldName));
        SettingsModel settingsModel = new SettingsModel();
        settingsModel.setGoogleDriveUsername(getSingleSettingValue(singleSettings,
                settingsConfiguration.getGoogleDriveUsername().getFieldName()));
        settingsModel.setGoogleDrivePassword(getSingleSettingValue(singleSettings,
                settingsConfiguration.getGoogleDrivePassword().getFieldName()));
        settingsModel.setGoogleDriveFilepath(getSingleSettingValue(singleSettings,
                settingsConfiguration.getGoogleDriveFilepath().getFieldName()));
        settingsModel.setInstagramAccountsToTrack(getMultiSettingValue(multipleSettings,
                settingsConfiguration.getInstagramAccountsToTrack().getFieldName()));
        return settingsModel;
    }

    private Setting create(String fieldName, String value, boolean isMultiple) {
        Setting setting = new Setting();
        setting.setFieldName(fieldName);
        setting.setMultiple(isMultiple);
        setting.setValue(value);
        return setting;
    }

    private String getSingleSettingValue(Map<String, Setting> singleSettings, String fieldName) {
        return singleSettings.getOrDefault(fieldName, new Setting()).getValue();
    }

    private List<String> getMultiSettingValue(Map<String, List<Setting>> multipleSettings, String fieldName) {
        return multipleSettings.getOrDefault(fieldName, new ArrayList<>()).stream()
                .map(Setting::getValue).collect(Collectors.toList());
    }
}
