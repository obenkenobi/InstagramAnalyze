package com.nero.socialmedia.analysis.instagram.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomLoggerFactory {
    private CustomLoggerFactory() {}
    public static Logger getLogger(Class<?> c) {
        return LoggerFactory.getLogger(c);
    }
}
