package com.example.demo1;


import java.net.URL;
import java.util.ResourceBundle;

import org.controlsfx.control.Notifications;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

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
    public void addNotification(boolean isFullnessNotification, String message, String time) {
        HBox notification = formatNotification(isFullnessNotification, message, time);
        notificationList.getChildren().add(0, notification);
        String title;
        if(isFullnessNotification) {
           title  = "Fullness Sensor";
        } else {
           title = "Humidity Sensor";
        }
        Notifications notification1 = Notifications.create().title(title).text(message);
        notification1.showWarning();
    }

    private HBox formatNotification(boolean isFullness, String text, String currentTime){
        HBox notification = createNotificationContainer();

        ImageView icon = createNotificationIcon(isFullness);

        Label message = createNotificationText(text);

        Label time = createNotificationTime(currentTime);

        notification.getChildren().addAll(icon, message, time);
        return notification;
    }

    private HBox createNotificationContainer(){
        HBox notification = new HBox();
        notification.setPrefHeight(75.0);
        notification.setPrefWidth(1792.0);
        notification.setSpacing(40.0);
        return notification;
    }

    private ImageView createNotificationIcon(boolean isFullness){
        ImageView icon = new ImageView();
        icon.setFitHeight(79.0);
        icon.setFitWidth(91.0);
        icon.setPickOnBounds(true);
        icon.setPreserveRatio(true);
        if(isFullness){
            icon.setImage(new Image(getClass().getResourceAsStream("binBackground.png")));
        } else{
            icon.setImage(new Image(getClass().getResourceAsStream("humidityBackground.png")));
        }
        return icon;
    }

    private Label createNotificationText(String text){
        Label message = new Label(text);
        message.setPrefHeight(75.0);
        message.setPrefWidth(1719.0);
        message.setAlignment(Pos.BOTTOM_LEFT);
        message.setTextAlignment(TextAlignment.CENTER);
        message.setStyle("-fx-background-color: #EBD9B4;");
        message.setPadding(new Insets(5.0, 5.0, 15.0, 60.0));
        message.setFont(Font.font("Aldrich Regular", 40));
        return message;
    }

    private Label createNotificationTime(String currentTime){
        Label time = new Label(currentTime);
        time.setPrefHeight(75.0);
        time.setPrefWidth(831.0);
        time.setAlignment(Pos.CENTER);
        time.setStyle("-fx-background-color: #EBD9B4;");
        time.setFont(Font.font("Aldrich Regular", 30)); 
        return time;
    }
}
