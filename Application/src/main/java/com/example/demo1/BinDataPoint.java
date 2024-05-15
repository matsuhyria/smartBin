package com.example.demo1;

public class BinDataPoint {
    private String time;
    private float humidity;
    private float fullness;

    public BinDataPoint(String time, float humidity, float fullness) {
        this.time = time;
        this.humidity = humidity;
        this.fullness = fullness;
    }

    public String getTime() {
        return time;
    }

    public float getHumidity() {
        return humidity;
    }

    public float getFullness() {
        return fullness;
    }
}
