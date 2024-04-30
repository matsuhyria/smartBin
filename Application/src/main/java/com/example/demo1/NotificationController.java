package com.example.demo1;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class NotificationController implements Initializable{

    @FXML
    private VBox notificationList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(notificationList == null){
            System.out.println("NotificationList is not intialized");
        }
    }

    @FXML
    public void addNotification(String message) {
        Label newLabel = new Label(message);
        newLabel.setStyle("-fx-font-size: 79;");
        notificationList.getChildren().add(0, newLabel);
    }
}
