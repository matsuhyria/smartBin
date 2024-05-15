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

public class NotificationController implements Initializable, MQTTAlarmObserver, MQTTDataObserver{

    @FXML
    private VBox notificationList;

    private static final float HUMIDITY_THRESHOLD = 60.0f;
    private static final String HUMIDITY_NOTIFICATION = "Bin humidity level is greater than 60%!";
    private static final float FULLNESS_THRESHOLD = 80.0f;
    private static final String FULLNESS_NOTIFICATION = "Bin fullness level is greater than 80%!";
    private static final String ALARM_NOTIFICATION = "Fire detected! Call emergency!";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(notificationList == null){
            System.out.println("NotificationList is not intialized");
        }
    }

    @FXML
    public void addNotification(NotificationType type, String message, String time) {
        HBox notification = formatNotification(type, message, time);
        notificationList.getChildren().add(0, notification);
        String title;
        if(type == NotificationType.FULLNESS) {
           title  = "Fullness Sensor";
        } else if (type == NotificationType.HUMIDITY){
           title = "Humidity Sensor";
        } else{
            title = "ALARM!!!";
        }
        Notifications notification1 = Notifications.create().title(title).text(message);
        notification1.showWarning();
    }

    private HBox formatNotification(NotificationType type, String text, String currentTime){
        HBox notification = createNotificationContainer();

        ImageView icon = createNotificationIcon(type);

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

    private ImageView createNotificationIcon(NotificationType type){
        ImageView icon = new ImageView();
        icon.setFitHeight(79.0);
        icon.setFitWidth(91.0);
        icon.setPickOnBounds(true);
        icon.setPreserveRatio(true);
        if(type == NotificationType.FULLNESS){
            icon.setImage(new Image(getClass().getResourceAsStream("binBackground.png")));
        } else if (type == NotificationType.HUMIDITY){
            icon.setImage(new Image(getClass().getResourceAsStream("humidityBackground.png")));
        } else{
            System.out.println("ALARM!!!");
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

    //For testing purposes
    public VBox getNotificationList() {
        return notificationList;
    }

    @Override
    public void onAlarmUpdate() {
        addNotification(NotificationType.ALARM, ALARM_NOTIFICATION, Util.getFormattedTime());
    }

    @Override
    public void onHumidityUpdate(float value) {
        if(value > HUMIDITY_THRESHOLD){
            addNotification(NotificationType.HUMIDITY, HUMIDITY_NOTIFICATION, Util.getFormattedTime());
        }
    }

    @Override
    public void onFullnessUpdate(float value) {
        if(value > FULLNESS_THRESHOLD){
            addNotification(NotificationType.FULLNESS, FULLNESS_NOTIFICATION, Util.getFormattedTime());
        }
    }
}
