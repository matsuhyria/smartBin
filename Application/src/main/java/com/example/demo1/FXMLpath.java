package com.example.demo1;

import java.net.URL;

public enum FXMLpath {
    HEADER("header.fxml"),
    MAIN_PAGE("mainPage.fxml"),
    NOTIFICATION_PAGE("notificationPage.fxml"),
    MAP_PAGE("map.fxml"),
    BIN_CARD("bin1.fxml"),
    BIN_CARD_OTHER("binOther.fxml");

    private final String fxmlPath;

    FXMLpath(String fxmlPath) {
        this.fxmlPath = fxmlPath;
    }

    public URL getFxmlPath() {
        return getClass().getResource(fxmlPath);
    }
}
