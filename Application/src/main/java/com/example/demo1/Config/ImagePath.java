package com.example.demo1.Config;

import com.example.demo1.BinApplication;

public enum ImagePath {
    
    OVERVIEW_ACTIVE("images/OverviewActive.png"),
    NOTIFICATION_ACTIVE("images/NotificationActive.png"),
    MAP_ACTIVE("images/MapActive.png"),
    STATISTICS_ACTIVE("images/StatisticsActive.png"),
    
    OVERVIEW_CLOSED("images/OverviewClosed.png"),
    NOTIFICATION_CLOSED("images/NotificationClosed.png"),
    MAP_CLOSED("images/MapClosed.png"),
    STATISTICS_CLOSED("images/StatisticsClosed.png"),

    FULLNESS("images/binBackground.png"),
    HUMIDITY("images/humidityBackground.png"),
    ALARM("images/fireBackground.png");
    
    private final String path;

    ImagePath(String path) {
        this.path = path;
    }

    public String getPath() {
        return BinApplication.class.getResource(path).toExternalForm();
    }
}
