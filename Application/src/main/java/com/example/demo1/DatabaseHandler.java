package com.example.demo1;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseHandler {
    private static final String URL = "jdbc:postgresql://avian-cosmos-421323:europe-north1:smartbin";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "postgres";
    private Connection connection;

    public DatabaseHandler(){
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void closeConnection(){
        connection.close();
    }
}
