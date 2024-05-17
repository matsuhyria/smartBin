package com.example.demo1.Config;

import com.example.demo1.BinApplication;

public enum ImagePath {
    
    OVERVIEW_ACTIVE("OverviewActive.png"),
    NOTIFICATION_ACTIVE("NotificationActive.png"),
    MAP_ACTIVE("MapActive.png"),
    STATISTICS_ACTIVE("StatisticsActive.png"),
    
    OVERVIEW_CLOSED("OverviewClosed.png"),
    NOTIFICATION_CLOSED("NotificationClosed.png"),
    MAP_CLOSED("MapClosed.png"),
    STATISTICS_CLOSED("StatisticsClosed.png"),

    FULLNESS("binBackground.png"),
    HUMIDITY("humidityBackground.png"),
    ALARM("fireBackground.png");
    
    private final String path;

    ImagePath(String path) {
        this.path = path;
    }

    public String getPath() {
        return BinApplication.class.getResource(path).toExternalForm();
    }
}
