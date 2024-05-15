package com.example.demo1;

public interface MQTTDataObserver {

    void onHumidityUpdate(float value);
    void onFullnessUpdate(float value);
    
    
}
