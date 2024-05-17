package com.example.demo1.Core;

public interface MQTTDataObserver {

    void onHumidityUpdate(float value);
    void onFullnessUpdate(float value);
    
    
}
