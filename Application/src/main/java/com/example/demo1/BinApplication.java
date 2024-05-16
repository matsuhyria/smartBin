package com.example.demo1;

import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;
import org.eclipse.paho.client.mqttv3.MqttException;


public class BinApplication extends Application {
    MqttClientHandler mqttClientHandler;
    DatabaseHandler databaseHandler;


    @Override
    public void start(Stage stage) throws IOException {
        SceneManager.getInstance().setStage(stage, 800, 600);
        setUpConnection();
    }

    public void setUpConnection() {
        try {
            databaseHandler = DatabaseHandler.getInstance();
            mqttClientHandler = MqttClientHandler.getInstance();
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        Application.launch();
    }
}