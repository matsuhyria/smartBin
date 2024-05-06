package com.example.demo1;

import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;
import org.eclipse.paho.client.mqttv3.MqttException;


public class BinApplication extends Application {

    BinAppController contr;
    MqttClientHandler mqttClientHandler;


    @Override
    public void start(Stage stage) throws IOException {
        SceneManager.getInstance().setStage(stage, 800, 600);
        contr = SceneManager.getInstance().getBinController();
        setUpConnection();
    }

    public void setUpConnection() {
        try {
            mqttClientHandler = MqttClientHandler.getInstance();
            mqttClientHandler.setController(contr);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        Application.launch();
    }
}