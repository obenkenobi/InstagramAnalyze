package com.nero.socialmedia.analysis.instagram.services;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
@Getter
public class ResourceServiceImpl implements ResourceService {

    @Value("classpath:/icons/tray.png")
    private Resource trayIconResource;

    @Value("classpath:/fxml/settings.fxml")
    private Resource fxmlSettingsUIResource;

}
