package com.example.demo1.Core.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataManager {
    //Two tables are used to record statistics: bin_data and daily_bin_summary
    //Table bin_data has three columns: record_time (timestamp), bin_fullness_level (int), bin_humidity (int)
    //Table daily_bin_summary has four columns: day_of_week (varchar), avg_fullness_level (int), avg_humidity (int), id (serial) (used within SQL procedure)
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

    //Average humidity based on the CURRENT (daily) data
    public double getAverageHumidity() {
        String query = "SELECT AVG(bin_humidity) AS avg_humidity FROM bin_data WHERE record_time >= CURRENT_DATE";
        return getAverage(query, AVG_HUMIDITY);
    }
    //Average fullness based on the CURRENT (daily) data
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

    //Average humidity based on ALL SUMMARY (weekly) data
    public double getAverageHumiditySummary() {
        String query = "SELECT AVG(avg_humidity) AS avg_humidity FROM daily_bin_summary";
        return getAverage(query, AVG_HUMIDITY);
    }
    //Average fullness based on ALL SUMMARY (weekly) data
    public double getAverageFullnessSummary() {
        String query = "SELECT AVG(avg_fullness_level) AS avg_fullness FROM daily_bin_summary";
        return getAverage(query, "avg_fullness");
    }
    //Retrieves a list of needed data points used by the ChartBuilder
    private List<BinDataPoint> getData(String query, String nameHumidity, String nameFullness, String period){
        List<BinDataPoint> dataPoints = new ArrayList<>();
        try (Connection connection = databaseHandler.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String time = resultSet.getString(period); //day or time
                float humidity = resultSet.getFloat(nameHumidity);
                float fullness = resultSet.getFloat(nameFullness);
                dataPoints.add(new BinDataPoint(time, humidity, fullness));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataPoints;
    }

    //For daily chart (retrieve data for the current day only)
    public List<BinDataPoint> getDailyData() {
        String query = "SELECT record_time, bin_humidity, bin_fullness_level FROM bin_data WHERE record_time >= CURRENT_DATE";
        return getData(query, CURRENT_HUMIDITY, CURRENT_FULLNESS, TIME);
    }

    //For weekly chart (retrieve average data from all data ever recorded)
    public List<BinDataPoint> getWeeklyData() {
        String query = "SELECT day_of_week, AVG(avg_humidity) AS avg_humidity, AVG(avg_fullness_level) AS avg_fullness_level FROM daily_bin_summary GROUP BY day_of_week";
        return getData(query, AVG_HUMIDITY, AVG_FULLNESS, DAY);
    }
}