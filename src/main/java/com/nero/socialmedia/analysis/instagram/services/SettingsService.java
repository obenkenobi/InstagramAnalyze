package com.nero.socialmedia.analysis.instagram.services;

import com.nero.socialmedia.analysis.instagram.models.SettingsModel;

public interface SettingsService {
    void updateSettings(SettingsModel settingsModel);
    SettingsModel getSettings();
}
