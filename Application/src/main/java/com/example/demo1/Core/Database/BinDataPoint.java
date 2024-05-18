package com.example.demo1.Core.Database;

//This class is used to efficiently retrieve bin data from the database
public class BinDataPoint {
    private final String time; //day of the week (for weekly statistics) or time point (for daily statistics)
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
