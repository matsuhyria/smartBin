package com.example.demo1;

import javafx.application.Platform;
import org.eclipse.paho.client.mqttv3.*;

public class MqttClientHandler {

    private static MqttClientHandler instance;
    private BinAppController contr;
    private MqttClient client;

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
    }
    public void setController(BinAppController contr) {
        this.contr = contr;
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
                    if(topic.equals(humidTopic)){
                        contr.updateHumid(msg.substring(0,2));
                    } else{
                        float distance;
                        if(Integer.parseInt(msg) > maxLength){
                            distance = 0;
                        } else {
                            distance = 100 -((Integer.parseInt(msg) / maxLength) * 100);
                        }
                        contr.updateFull(String.valueOf((int)distance));
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
