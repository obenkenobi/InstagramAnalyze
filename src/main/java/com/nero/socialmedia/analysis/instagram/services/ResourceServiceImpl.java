package com.nero.socialmedia.analysis.instagram.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class ResourceServiceImpl implements ResourceService {

    @Value("classpath:/icons/tray.png")
    private Resource iconsTrayResource;

    @Value("classpath:/fxml/settings.fxml")
    private Resource fxmlSettingsResource;

    @Override
    public Resource getIconsTrayResource() {
        return iconsTrayResource;
    }

    @Override
    public Resource getFxmlSettingsResource() {
        return fxmlSettingsResource;
    }
}
