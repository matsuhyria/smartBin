package com.example.demo1;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class HeaderController {
    @FXML
    private Button mainPage;

    @FXML
    private Button notificationPage;

    @FXML
    private Button mapPage;

    @FXML
    private Button statsPage;

    @FXML
    public void initialize() {
        mainPage.setOnAction(event -> SceneManager.getInstance().switchToMainPage());
        notificationPage.setOnAction(event -> SceneManager.getInstance().switchToNotificationPage());
        mapPage.setOnAction(event -> SceneManager.getInstance().switchToMapPage());
        statsPage.setOnAction(event -> SceneManager.getInstance().switchToStatsPage());
    }
}
