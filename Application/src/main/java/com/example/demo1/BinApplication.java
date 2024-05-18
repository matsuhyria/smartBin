package com.example.demo1;

import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;

import com.example.demo1.Core.ChartBuilder;
import com.example.demo1.Core.MqttClientHandler;
import com.example.demo1.Core.Database.DataManager;
import com.example.demo1.Core.FXMLControllers.CardController;
import com.example.demo1.Core.FXMLControllers.MapController;
import com.example.demo1.Core.FXMLControllers.NotificationController;
import com.example.demo1.Core.FXMLControllers.StatisticsController;
import com.example.demo1.UI.SceneManager;

//Starting point of the application
    public class BinApplication extends Application {
    //Change according to screen resolution
    private static final int HEIGHT = 1920; 
    private static final int WIDTH = 1080;

    @Override
    public void start(Stage stage) throws IOException {
        //Setup for all controllers
        NotificationController notificationController = new NotificationController(stage);
        CardController cardControllerMain = new CardController();
        CardController cardControllerMap = new CardController();
        MapController mapController = new MapController(cardControllerMap);
        DataManager dataManager = new DataManager();
        ChartBuilder chartBuilder = new ChartBuilder();
        StatisticsController statsController = new StatisticsController(chartBuilder, dataManager);
        
        //MQTT connection
        MqttClientHandler mqttClientHandler = MqttClientHandler.getInstance();

        //Data updates observers
        mqttClientHandler.registerAlarmObserver(notificationController);
        mqttClientHandler.registerDataObserver(cardControllerMain);
        mqttClientHandler.registerDataObserver(cardControllerMap);
        mqttClientHandler.registerDataObserver(notificationController);
        mqttClientHandler.registerDataObserver(dataManager);

        //Scene translations
        SceneManager sceneManager = new SceneManager(notificationController, cardControllerMain, mapController, statsController);
        statsController.showDaily();
        sceneManager.setStage(stage, HEIGHT, WIDTH);
        stage.show();
    }


    public static void main(String[] args) {
        Application.launch();
    }
}