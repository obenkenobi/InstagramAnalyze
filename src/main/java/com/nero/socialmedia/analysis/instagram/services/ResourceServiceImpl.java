package com.nero.socialmedia.analysis.instagram.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class ResourceServiceImpl implements ResourceService {

    @Value("classpath:/images/tray.png")
    private Resource imagesTrayResource;

    @Value("classpath:/fxml/main.fxml")
    private Resource fxmlMainResource;

    @Override
    public Resource getImagesTrayResource() {
        return imagesTrayResource;
    }

    @Override
    public Resource getFxmlMainResource() {
        return fxmlMainResource;
    }
}
