package com.example.demo1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataManager{

    private DatabaseHandler databaseHandler;

    public DataManager () {
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
}