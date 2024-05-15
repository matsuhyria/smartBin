package com.example.demo1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataManager {
    final static String CURRENT_FULLNESS = "bin_fullness_level";
    final static String CURRENT_HUMIDITY = "bin_humidity";
    final static String AVG_FULLNESS = "avg_fullness_level";
    final static String AVG_HUMIDITY = "avg_humidity";
    final static String TIME = "record_time";
    final static String DAY = "day_of_week";


    private DatabaseHandler databaseHandler;

    public DataManager() {
        this.databaseHandler = DatabaseHandler.getInstance();
    }

    private void executeAddingData(String query, float type){
        try (Connection connection = databaseHandler.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setFloat(1, type);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addHumidityData(float humidity) {
        String query = "INSERT INTO bin_data (record_time, bin_humidity) VALUES (CURRENT_TIMESTAMP, ?)";
        executeAddingData(query, humidity);
    }

    public void addFullnessData(float fullness) {
        String query = "INSERT INTO bin_data (record_time, bin_fullness_level) VALUES (CURRENT_TIMESTAMP, ?)";
        executeAddingData(query, fullness);
    }

    public double getAverageHumidity() {
        String query = "SELECT AVG(bin_humidity) AS avg_humidity FROM bin_data WHERE record_time >= CURRENT_DATE";
        return getAverage(query, AVG_HUMIDITY);
    }

    public double getAverageFullness() {
        String query = "SELECT AVG(bin_fullness_level) AS avg_fullness FROM bin_data WHERE record_time >= CURRENT_DATE";
        return getAverage(query, "avg_fullness");
    }

    private double getAverage(String query, String columnLabel){
        try (Connection connection = databaseHandler.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getDouble(columnLabel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    public double getAverageHumiditySummary() {
        String query = "SELECT AVG(avg_humidity) AS avg_humidity FROM daily_bin_summary";
        return getAverage(query, AVG_HUMIDITY);
    }

    public double getAverageFullnessSummary() {
        String query = "SELECT AVG(avg_fullness_level) AS avg_fullness FROM daily_bin_summary";
        return getAverage(query, "avg_fullness");
    }

    private List<BinDataPoint> getData(String query, String nameHumidity, String nameFullness, String period){
        List<BinDataPoint> dataPoints = new ArrayList<>();
        try (Connection connection = databaseHandler.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String time = resultSet.getString(period);
                float humidity = resultSet.getFloat(nameHumidity);
                float fullness = resultSet.getFloat(nameFullness);
                dataPoints.add(new BinDataPoint(time, humidity, fullness));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataPoints;
    }

    public List<BinDataPoint> getDailyData() {
        String query = "SELECT record_time, bin_humidity, bin_fullness_level FROM bin_data WHERE record_time >= CURRENT_DATE";
        return getData(query, CURRENT_HUMIDITY, CURRENT_FULLNESS, TIME);
    }

    public List<BinDataPoint> getWeeklyData() {
        String query = "SELECT day_of_week, AVG(avg_humidity) AS avg_humidity, AVG(avg_fullness_level) AS avg_fullness_level FROM daily_bin_summary GROUP BY day_of_week";
        return getData(query, AVG_HUMIDITY, AVG_FULLNESS, DAY);
    }
}