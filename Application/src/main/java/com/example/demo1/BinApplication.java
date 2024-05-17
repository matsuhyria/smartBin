package com.example.demo1;

import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;

import com.example.demo1.Core.CardController;
import com.example.demo1.Core.MapController;
import com.example.demo1.Core.MqttClientHandler;
import com.example.demo1.Core.NotificationController;
import com.example.demo1.UI.SceneManager;


    public class BinApplication extends Application {
    private static final int HEIGHT = 1920; 
    private static final int WIDTH = 1080;

    @Override
    public void start(Stage stage) throws IOException {
        NotificationController notificationController = new NotificationController(stage);
        CardController cardControllerMain = new CardController();
        CardController cardControllerMap = new CardController();
        MapController mapController = new MapController(cardControllerMap);
        SceneManager sceneManager = new SceneManager(notificationController, cardControllerMain, mapController);
        sceneManager.setStage(stage, HEIGHT, WIDTH);
        stage.show();
        MqttClientHandler mqttClientHandler = MqttClientHandler.getInstance();
        mqttClientHandler.registerAlarmObserver(notificationController);
        mqttClientHandler.registerDataObserver(cardControllerMain);
        mqttClientHandler.registerDataObserver(cardControllerMap);
        mqttClientHandler.registerDataObserver(notificationController);
    }


    public static void main(String[] args) {
        Application.launch();
    }
}