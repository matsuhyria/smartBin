package com.example.demo1;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;


public class BinApplication extends Application {

    BinAppController contr;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("mainPage.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        contr = fxmlLoader.getController();

        stage.setTitle("First Demo");
        stage.setScene(scene);
        stage.show();

        setUpConnection();

    }
    public void setUpConnection(){
        String broker = "tcp://test.mosquitto.org:1883";
        String clientId = "SmartBinPlusMain";
        String humidTopic = "Sensors/Humidity";
        String ulsTopic = "Sensors/Ultrasonic";
        float maxLength = 50;

        int qos = 1;

        try {
            MqttClient client = new MqttClient(broker, clientId);
            MqttConnectOptions options = new MqttConnectOptions();

            client.setCallback(new MqttCallback() {
                public void connectionLost(Throwable cause) {
                    System.out.println("Connection lost: " + cause.getMessage());
                }

                public void messageArrived(String topic, MqttMessage message) throws Exception {
                    Platform.runLater(() -> {
                        //System.out.println("Topic: " + topic);
                        //System.out.println("QoS: " + message.getQos());
                        String msg = new String(message.getPayload());
                        //System.out.println("Message content: " + msg);
                        if(topic.equals(humidTopic)){
                            contr.updateHumid(msg.substring(0,2));
                        } else{
                            float distance;
                            if(Integer.parseInt(msg) > maxLength){
                                distance = 0;
                            } else {
                                distance = 100 -((Integer.parseInt(msg) / maxLength) * 100);
                            }
                            System.out.println(distance);
                            System.out.println(Integer.parseInt(msg));
                            contr.updateFull(String.valueOf((int)distance));
                        }
                    });
                }

                public void deliveryComplete(IMqttDeliveryToken token) {
                    System.out.println("Delivery complete: " + token.isComplete());
                }
            });

            client.connect(options);

            client.subscribe(humidTopic, qos);
            client.subscribe(ulsTopic, qos);

            client.disconnect();
            client.close();

        } catch (MqttException e) {
            e.printStackTrace();
        }
    }


    // public static void main(String[] args) {
    //     Application.launch();
    // }
}