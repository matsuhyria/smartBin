package com.example.demo1;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

class NotificationScreen extends Application {
    
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("notificationPage.fxml"));
            AnchorPane root = (AnchorPane)fxmlLoader.load();
            Scene scene = new Scene(root);
    
            stage.setTitle("Notifications");
            stage.setScene(scene);
            stage.setFullScreen (true);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    public static void main(String[] args) throws IOException {   
        Application.launch();
    }
}
