package com.example.demo1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class NotificationScreen extends Application{

    private NotificationController notificationController;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("notificationPage.fxml"));
        AnchorPane root = fxmlLoader.load();
        Scene scene = new Scene(root, 800, 600);

        notificationController = fxmlLoader.getController();

        notificationController.addNotification("The bin is 20% full");
        notificationController.addNotification("The bin is 40% full");
        notificationController.addNotification("The bin is 80% full");
        notificationController.addNotification("The bin is 100% full");


        stage.setTitle("Notifications");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args){
        Application.launch(args);
    }
    
}
