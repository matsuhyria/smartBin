package com.example.demo1;

import java.net.URL;

public enum ImagePath {
    
    OVERVIEW_ACTIVE("OverviewActive.png"),
    NOTIFICATION_ACTIVE("NotificationActive.png"),
    MAP_ACTIVE("MapActive.png"),
    STATISTICS_ACTIVE("StatisticsActive.png"),
    
    OVERVIEW_CLOSED("OverviewClosed.png"),
    NOTIFICATION_CLOSED("NotificationClosed.png"),
    MAP_CLOSED("MapClosed.png"),
    STATISTICS_CLOSED("StatisticsClosed.png"),

    BIN_BACKGROUND("binBackground.png"),
    HUMIDITY_BACKGROUND("humidityBackground.png"),
    FIRE_BACKGROUND("fireBackground.png");
    


    private final String path;

    ImagePath(String path) {
        this.path = path;
    }

    public String getPath() {
        return getClass().getResource(path).toString();
    }
}
