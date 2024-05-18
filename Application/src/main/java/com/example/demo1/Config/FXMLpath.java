package com.example.demo1.Config;

import java.net.URL;

import com.example.demo1.BinApplication;

//Change path to the xml files here in case of changes in the folder structure
public enum FXMLpath {
    HEADER("fxml/header.fxml"),
    MAIN_PAGE("fxml/mainPage.fxml"),
    NOTIFICATION_PAGE("fxml/notificationPage.fxml"),
    MAP_PAGE("fxml/mapPage.fxml"),
    BIN_CARD("fxml/bin1.fxml"),
    BIN_CARD_OTHER("fxml/binOther.fxml"),
    STATISTICS_PAGE("fxml/statisticsPage.fxml"),
    POP_OUT_LIGHT("fxml/popOutLight.fxml");

    private final String fxmlPath;

    FXMLpath(String fxmlPath) {
        this.fxmlPath = fxmlPath;
    }

    public URL getFxmlPath() {
        return BinApplication.class.getResource(fxmlPath);
    }
}
