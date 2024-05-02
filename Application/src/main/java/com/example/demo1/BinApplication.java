package com.example.demo1;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;


public class BinApplication extends Application {

    BinAppController contr;
    NotificationController notificationController; 

    private static final float HUMIDITY_THRESHOLD = 60.0f;
    private static final String HUMIDITY_NOTIFICATION = "Bin humidity level is greater than 60%!";
    private static final float FULLNESS_THRESHOLD = 80.0f;
    private static final String FULLNESS_NOTIFICATION = "Bin fullness level is greater than 80%!";

    @Override
    public void start(Stage stage) throws IOException {
        SceneManager.getInstance().setStage(stage, 800, 600);
        contr = SceneManager.getInstance().getBinController();
        setUpConnection();
        notificationController = SceneManager.getInstance().getNotificationController();
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
                        LocalDateTime now = LocalDateTime.now();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                        String formattedTime = formatter.format(now);
                        //System.out.println("Message content: " + msg);
                        if(topic.equals(humidTopic)){
                            contr.updateHumid(msg.substring(0,2));
                            float humidityLevel = Float.parseFloat(msg);
                            if(humidityLevel > HUMIDITY_THRESHOLD){
                                notificationController.addNotification(false, HUMIDITY_NOTIFICATION, formattedTime);
                            }
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
                            if(distance > FULLNESS_THRESHOLD){
                                notificationController.addNotification(true, FULLNESS_NOTIFICATION, formattedTime);
                            }
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

            //client.disconnect();
            //client.close();

        } catch (MqttException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        Application.launch();
    }
}