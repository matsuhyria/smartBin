package com.example.demo1.Core.Database;

import java.sql.*;
import java.util.Arrays;

public class DatabaseHandler {

    private static DatabaseHandler instance;
    //GoogleCloud database SQL ID: avian-cosmos-421323
    //Server location: europe-north1
    //Project ID: smartbin
    private static final String CONNECTION_NAME = "avian-cosmos-421323:europe-north1:smartbin";
    private static final String USERNAME = "postgres";
    private static final String USER_PASSWORD = "postgres";
    private static final String DATABASE_NAME = "postgres";
    private static final int MAX_BIN_COUNT = 5;
    private Connection connection;

    //Connection through Java Database Connectivity API
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

    public Connection getConnection() throws SQLException {
        String jdbcUrl = "jdbc:postgresql:///" + DATABASE_NAME + "?" +
                "cloudSqlInstance=" + CONNECTION_NAME +
                "&socketFactory=com.google.cloud.sql.postgres.SocketFactory" +
                "&user=" + USERNAME +
                "&password=" + USER_PASSWORD;

        return DriverManager.getConnection(jdbcUrl);
    }

    //Retrieve coordinates of all bins as an array.
    //Coordinates are recorded in the appropriate order (important)
    //bin_location table has columns id, x_coord (double), y_coord (double)
    public double[] getLocations(){
        double[] coordinates = new double[MAX_BIN_COUNT * 2];
        int index = 0; // Track position in the array
        try {
            Statement statement = connection.createStatement();

            String query = "SELECT x_coord, y_coord FROM bin_locations";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                double x = resultSet.getDouble("x_coord");
                double y = resultSet.getDouble("y_coord");
                coordinates[index] = x; //Even indices record x-coordinates
                coordinates[index+1] = y; //Odd indices record y-coordinates
                index = index + 2;
            }
            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Arrays.copyOf(coordinates, index);
    }

    //Delete bin from bin_locations table if the location was deleted from the map
    public void deleteLocation(double x, double y){
        int id = getId(x, y);
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM bin_locations WHERE id = ?");
            statement.setInt(1, id);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //add bin to bin_locations if new point was registered on the map
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

    //Retrieve ID of the bin from bin_location table based on the coordinates
    private int getId(double x, double y){
        int id = -1;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT id FROM bin_locations WHERE x_coord = ? AND y_coord = ?");
            statement.setDouble(1, x);
            statement.setDouble(2, y);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getInt("id");
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
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
