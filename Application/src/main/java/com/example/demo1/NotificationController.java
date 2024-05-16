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

public class NotificationController implements MQTTAlarmObserver, MQTTDataObserver{

    @FXML
    private VBox notificationList;

    private static final float HUMIDITY_THRESHOLD = 60.0f;
    private static final String HUMIDITY_NOTIFICATION = "Bin humidity level is greater than 60%!";
    private static final float FULLNESS_THRESHOLD = 80.0f;
    private static final String FULLNESS_NOTIFICATION = "Bin fullness level is greater than 80%!";
    private static final String ALARM_NOTIFICATION = "Fire detected! Call emergency!";

    private static final String TEXT_BACKGROUND_COLOR = "-fx-background-color: #EBD9B4;";
    //Be careful when using not default font, install it on your machine first
    private static final String FONT_NAME = "Aldrich Regular";
    private static final int TEXT_FONT_SIZE = 40;
    private static final int TIME_FONT_SIZE = 30;

    private static final int TEXT_FRAME_WIDTH = 1720;
    private static final int TEXT_FRAME_HEIGHT = 75;

    private static final int TIME_FRAME_WIDTH = 830;
    private static final int TIME_FRAME_HEIGHT = 75;

    private static final int ICON_FIT_WIDTH = 80;
    private static final int ICON_FIT_HEIGHT = 80;

    //Notification container as a whole
    private static final int CONTAINER_WIDTH = 1800;
    private static final int CONTAINER_HEIGHT = 75;
    private static final int CONTAINER_SPACING = 40;


    @FXML
    public void addNotification(NotificationType type, String message, String time) {
        HBox notification = formatNotification(type, message, time);
        notificationList.getChildren().add(0, notification);
        Notifications notification1 = Notifications.create().title(type.toString()).text(message);
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
        notification.setPrefHeight(CONTAINER_HEIGHT);
        notification.setPrefWidth(CONTAINER_WIDTH);
        notification.setSpacing(CONTAINER_SPACING);
        return notification;
    }

    private ImageView createNotificationIcon(NotificationType type){
        ImageView icon = new ImageView();
        icon.setFitHeight(ICON_FIT_HEIGHT);
        icon.setFitWidth(ICON_FIT_WIDTH);
        if(type == NotificationType.FULLNESS){
            icon.setImage(Util.load(ImagePath.BIN_BACKGROUND));
        } else if (type == NotificationType.HUMIDITY){
            icon.setImage(Util.load(ImagePath.HUMIDITY_BACKGROUND));
        } else{
            System.out.println("ALARM!!!");
        }
        return icon;
    }

    private Label createNotificationText(String text){
        Label message = new Label(text);
        message.setPrefHeight(TEXT_FRAME_HEIGHT);
        message.setPrefWidth(TEXT_FRAME_WIDTH);
        message.setAlignment(Pos.BOTTOM_LEFT);
        message.setTextAlignment(TextAlignment.CENTER);
        message.setStyle(TEXT_BACKGROUND_COLOR);
        message.setPadding(new Insets(5.0, 5.0, 15.0, 60.0));
        message.setFont(Font.font(FONT_NAME, TEXT_FONT_SIZE));
        return message;
    }

    private Label createNotificationTime(String currentTime){
        Label time = new Label(currentTime);
        time.setPrefHeight(TIME_FRAME_HEIGHT);
        time.setPrefWidth(TIME_FRAME_WIDTH);
        time.setAlignment(Pos.CENTER);
        time.setStyle(TEXT_BACKGROUND_COLOR);
        time.setFont(Font.font(FONT_NAME, TIME_FONT_SIZE)); 
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
