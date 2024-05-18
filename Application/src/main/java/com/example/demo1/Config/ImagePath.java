package com.example.demo1.Config;

import com.example.demo1.BinApplication;

//Change path to the images here in case of changes in the folder structure
public enum ImagePath {

    //Active navigation menu icons
    OVERVIEW_ACTIVE("images/OverviewActive.png"),
    NOTIFICATION_ACTIVE("images/NotificationActive.png"),
    MAP_ACTIVE("images/MapActive.png"),
    STATISTICS_ACTIVE("images/StatisticsActive.png"),

    //Closed navigation menu icons
    OVERVIEW_CLOSED("images/OverviewClosed.png"),
    NOTIFICATION_CLOSED("images/NotificationClosed.png"),
    MAP_CLOSED("images/MapClosed.png"),
    STATISTICS_CLOSED("images/StatisticsClosed.png"),

    //Notification icons
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
