package com.example.demo1;

import javafx.application.Platform;
import org.eclipse.paho.client.mqttv3.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MqttClientHandler {

    private static MqttClientHandler instance;
    private CardController cardController;
    private MqttClient client;
    NotificationController notificationController;
    private DataManager dataManager;

    private static final float HUMIDITY_THRESHOLD = 60.0f;
    private static final String HUMIDITY_NOTIFICATION = "Bin humidity level is greater than 60%!";
    private static final float FULLNESS_THRESHOLD = 80.0f;
    private static final String FULLNESS_NOTIFICATION = "Bin fullness level is greater than 80%!";

    String broker = "tcp://test.mosquitto.org:1883";
    String clientId = "SmartBinPlusMain";
    String humidTopic = "Sensors/Humidity";
    String ulsTopic = "Sensors/Ultrasonic";
    float maxLength = 50;
    int qos = 1;

    private MqttClientHandler() throws MqttException {
        this.client = new MqttClient(broker, clientId);
        try {
            setCallback();
            connect();
            subscribe();
        } catch (MqttException e) {
            e.printStackTrace();
        }
        this.notificationController = SceneManager.getInstance().getNotificationController();
        this.cardController = SceneManager.getInstance().getBinCardController();
        this.dataManager = new DataManager();
    }

    public static MqttClientHandler getInstance() throws MqttException {
        if (instance == null) {
            instance = new MqttClientHandler();
        }
        return instance;
    }

    public void connect() throws MqttException {
        MqttConnectOptions options = new MqttConnectOptions();
        client.connect(options);
    }

    public void subscribe() throws MqttException {
        client.subscribe(humidTopic, qos);
        client.subscribe(ulsTopic, qos);

    }

    public void setCallback() {
        client.setCallback(new MqttCallback() {
            public void connectionLost(Throwable cause) {
                System.out.println("Connection lost: " + cause.getMessage());
            }

            public void messageArrived(String topic, MqttMessage message) throws Exception {
                Platform.runLater(() -> {
                    String msg = new String(message.getPayload());
                    LocalDateTime now = LocalDateTime.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                    String formattedTime = formatter.format(now);

                    if(topic.equals(humidTopic)){
                        cardController.updateHumid(msg.substring(0,2));
                        float humidityLevel = Float.parseFloat(msg);
                        dataManager.addHumidityData(humidityLevel);
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
                        cardController.updateFull(String.valueOf((int)distance));
                        dataManager.addFullnessData(distance);
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
    }
    public void disconnect() throws MqttException {
        client.disconnect();
    }

    public void close() throws MqttException {
        client.close();
    }
}
