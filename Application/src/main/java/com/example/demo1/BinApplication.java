package com.example.demo1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import org.eclipse.paho.client.mqttv3.MqttException;


public class BinApplication extends Application {

    BinAppController contr;
    MqttClientHandler mqttClientHandler;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("mainPage.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        contr = fxmlLoader.getController();

        stage.setTitle("Second Demo");
        stage.setScene(scene);
        stage.show();

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