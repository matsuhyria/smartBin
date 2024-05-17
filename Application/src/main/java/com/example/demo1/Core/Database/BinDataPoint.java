package com.example.demo1.Core.Database;

public class BinDataPoint {
    private final String time;
    private final float humidity;
    private final float fullness;

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
