package com.example.demo1;

import javafx.application.Platform;
import org.eclipse.paho.client.mqttv3.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MqttClientHandler {

    private static final MqttClientHandler instance = new MqttClientHandler();
    private MqttClient client;
    private final List<MQTTDataObserver> mqttDataObservers = new ArrayList<>();
    private final List<MQTTAlarmObserver> mqttAlarmObservers = new ArrayList<>();

    public static final String broker = "tcp://test.mosquitto.org:1883";
    public static final String clientId = "SmartBinPlusMain";
    public static final String HUMIDITY_TOPIC = "Sensors/Humidity";
    public static final String ULS_TOPIC = "Sensors/Ultrasonic";
    public static final String ALARM_TOPIC = "Sensors/Flame";
    public static final float BIN_MAX_LENGTH = 50;
    public static final int qos = 1;

    private MqttClientHandler() { 
        try {
            this.client = new MqttClient(broker, clientId);
            setCallback();
            connect();
            subscribe();
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public static MqttClientHandler getInstance() {
        return instance;
    }

    public void connect() throws MqttException {
        MqttConnectOptions options = new MqttConnectOptions();
        client.connect(options);
    }

    public void subscribe() throws MqttException {
        client.subscribe(HUMIDITY_TOPIC, qos);
        client.subscribe(ULS_TOPIC, qos);
        client.subscribe(ALARM_TOPIC, qos);
    }

    public void setCallback() {
        client.setCallback(new MqttCallback() {
            public void connectionLost(Throwable cause) {
                System.out.println("Connection lost: " + cause.getMessage());
            }

            public void messageArrived(String topic, MqttMessage message) throws Exception {
                Platform.runLater(() -> {
                    String msg = new String(message.getPayload());
                    float value = Float.parseFloat(msg);

                    if(topic.equals(HUMIDITY_TOPIC)){
                        handleHumidityMessage(value);
                    } else if(topic.equals(ULS_TOPIC)){
                        handleUltrasonicMessage(value);
                    } else{
                        handleAlarmMessage();
                    }});
                }

            @Override
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

    public void registerDataObserver(MQTTDataObserver obs){
        mqttDataObservers.add(obs);
    }

    public void registerAlarmObserver(MQTTAlarmObserver obs){
        mqttAlarmObservers.add(obs);
    }

    private void handleHumidityMessage(float value){
        for(MQTTDataObserver obs : mqttDataObservers){
            obs.onHumidityUpdate(value);
        }
    }

    private void handleUltrasonicMessage(float value){
        for(MQTTDataObserver obs : mqttDataObservers){
            obs.onFullnessUpdate(value);
        }
    }

    private void handleAlarmMessage(){
        for(MQTTAlarmObserver obs : mqttAlarmObservers){
            obs.onAlarmUpdate();
        }
    }
}
