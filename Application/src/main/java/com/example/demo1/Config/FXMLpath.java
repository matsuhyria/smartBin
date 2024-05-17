package com.example.demo1.Config;

import java.net.URL;

import com.example.demo1.BinApplication;

public enum FXMLpath {
    HEADER("header.fxml"),
    MAIN_PAGE("mainPage.fxml"),
    NOTIFICATION_PAGE("notificationPage.fxml"),
    MAP_PAGE("mapPage.fxml"),
    BIN_CARD("bin1.fxml"),
    BIN_CARD_OTHER("binOther.fxml"),
    STATISTICS_PAGE("statisticsPage.fxml"),
    POP_OUT_LIGHT("popOutLight.fxml");

    private final String fxmlPath;

    FXMLpath(String fxmlPath) {
        this.fxmlPath = fxmlPath;
    }

    public URL getFxmlPath() {
        System.out.println(BinApplication.class.getResource(fxmlPath));
        return BinApplication.class.getResource(fxmlPath);
    }
}
