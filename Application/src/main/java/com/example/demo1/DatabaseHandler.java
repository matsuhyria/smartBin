package com.example.demo1;

import java.sql.*;
import java.util.Arrays;

public class DatabaseHandler {

    private static DatabaseHandler instance;

    private static final String CONNECTION_NAME = "avian-cosmos-421323:europe-north1:smartbin";
    private static final String USERNAME = "postgres";
    private static final String USER_PASSWORD = "postgres";
    private static final String DATABASE_NAME = "postgres";

    private Connection connection;
    private static final int MAX_BIN_COUNT = 5;

    private DatabaseHandler() {
        try {
            String jdbcUrl = "jdbc:postgresql:///" + DATABASE_NAME + "?" +
                    "cloudSqlInstance=" + CONNECTION_NAME +
                    "&socketFactory=com.google.cloud.sql.postgres.SocketFactory" +
                    "&user=" + USERNAME +
                    "&password=" + USER_PASSWORD;

            connection = DriverManager.getConnection(jdbcUrl);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static DatabaseHandler getInstance() {
        if (instance == null) {
            instance = new DatabaseHandler();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    public double[] getLocations(){
        double[] coordinates = new double[MAX_BIN_COUNT * 2];
        int index = 0;
        try {
            Statement statement = connection.createStatement();

            String query = "SELECT x_coord, y_coord FROM bin_locations";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                double x = resultSet.getDouble("x_coord");
                double y = resultSet.getDouble("y_coord");
                coordinates[index] = x;
                coordinates[index+1] = y;
                index = index + 2;
            }
            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Arrays.copyOf(coordinates, index);
    }

    public void deleteLocation(){
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM bin_locations");
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addLocation(double x, double y){
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO bin_locations (x_coord, y_coord) VALUES (?, ?)");
            statement.setDouble(1, x);
            statement.setDouble(2, y);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
