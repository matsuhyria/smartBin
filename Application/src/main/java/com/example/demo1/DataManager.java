package com.example.demo1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataManager {

    private DatabaseHandler databaseHandler;

    public DataManager() {
        this.databaseHandler = DatabaseHandler.getInstance();
    }

    public void addHumidityData(float humidity) {
        String query = "INSERT INTO bin_data (record_time, bin_humidity) VALUES (CURRENT_TIMESTAMP, ?)";
        try (Connection connection = databaseHandler.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setFloat(1, humidity);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addFullnessData(float fullness) {
        String query = "INSERT INTO bin_data (record_time, bin_fullness_level) VALUES (CURRENT_TIMESTAMP, ?)";
        try (Connection connection = databaseHandler.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setFloat(1, fullness);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public double getAverageHumidity() {
        String query = "SELECT AVG(bin_humidity) AS avg_humidity FROM bin_data WHERE record_time >= CURRENT_DATE";
        try (Connection connection = databaseHandler.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getDouble("avg_humidity");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    public double getAverageFullness() {
        String query = "SELECT AVG(bin_fullness_level) AS avg_fullness FROM bin_data WHERE record_time >= CURRENT_DATE";
        try (Connection connection = databaseHandler.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getDouble("avg_fullness");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    public double getAverageHumiditySummary() {
        String query = "SELECT AVG(avg_humidity) AS avg_humidity FROM daily_bin_summary";
        try (Connection connection = databaseHandler.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getDouble("avg_humidity");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    public double getAverageFullnessSummary() {
        String query = "SELECT AVG(avg_fullness_level) AS avg_fullness FROM daily_bin_summary";
        try (Connection connection = databaseHandler.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getDouble("avg_fullness");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;

    }

    public List<BinDataPoint> getDailyData() {
        List<BinDataPoint> dataPoints = new ArrayList<>();
        String query = "SELECT record_time, bin_humidity, bin_fullness_level FROM bin_data WHERE record_time >= CURRENT_DATE";
        try (Connection connection = databaseHandler.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String time = resultSet.getString("record_time");
                float humidity = resultSet.getFloat("bin_humidity");
                float fullness = resultSet.getFloat("bin_fullness_level");
                dataPoints.add(new BinDataPoint(time, humidity, fullness));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataPoints;
    }

    public List<BinDataPoint> getWeeklyData(){
        List<BinDataPoint> dataPoints = new ArrayList<>();

        String query = "SELECT day_of_week, AVG(avg_humidity) AS avg_humidity, AVG(avg_fullness_level) AS avg_fullness_level FROM daily_bin_summary GROUP BY day_of_week";
        try (Connection connection = databaseHandler.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String time = resultSet.getString("day_of_week");
                float humidity = resultSet.getFloat("avg_humidity");
                float fullness = resultSet.getFloat("avg_fullness_level");
                dataPoints.add(new BinDataPoint(time, humidity, fullness));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataPoints;
    }
}