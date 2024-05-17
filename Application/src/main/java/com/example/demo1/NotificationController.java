package com.example.demo1;


import java.net.URL;
import java.time.Duration;
import java.util.ResourceBundle;

import org.controlsfx.control.Notifications;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class NotificationController implements MQTTAlarmObserver, MQTTDataObserver{

    @FXML
    private VBox notificationList;

    private static final float HUMIDITY_THRESHOLD = 60.0f;
    private static final String HUMIDITY_NOTIFICATION = "Bin humidity level is greater than 60%!";
    private static final float FULLNESS_THRESHOLD = 80.0f;
    private static final String FULLNESS_NOTIFICATION = "Bin fullness level is greater than 80%!";
    private static final String ALARM_NOTIFICATION = "Fire detected! Call emergency!";

    private static final String TEXT_BACKGROUND_COLOR = "-fx-background-color: #EBD9B4;";
    private static final String TEXT_COLOR = "-fx-text-fill: #000000;";
    private static final String TEXT_ALARM_COLOR = "-fx-text-fill: #CC483E;";
    //Be careful when using not default font, install it on your machine first
    private static final String FONT_NAME = "Aldrich Regular";
    private static final int TEXT_FONT_SIZE = 40;
    private static final int TIME_FONT_SIZE = 30;

    private static final int TEXT_FRAME_WIDTH = 1000;
    private static final int TEXT_FRAME_HEIGHT = 75;

    private static final int TIME_FRAME_WIDTH = 125;
    private static final int TIME_FRAME_HEIGHT = 75;

    private static final int ICON_FIT_WIDTH = 80;
    private static final int ICON_FIT_HEIGHT = 75;

    //Notification container as a whole
    private static final int CONTAINER_WIDTH = 1800;
    private static final int CONTAINER_HEIGHT = 75;
    private static final int CONTAINER_SPACING = 30;

    //Text padding
    private static final int PADDING_TOP = 5;
    private static final int PADDING_BOTTOM = 15;
    private static final int PADDING_LEFT = 60;
    private static final int PADDING_RIGHT = 5;

    private final Stage owner;

    public NotificationController(Stage stage){
        owner = stage;
    }

    @FXML
    public void addNotification(ImagePath type, String message, String time) {
        HBox notification = formatNotification(type, message, time);
        notificationList.getChildren().add(0, notification);
        Notifications notificationBuilder = Notifications.create().title(type.toString()).text(message).owner(owner).graphic(Util.loadNotificationIcon(type));
        owner.getScene().getStylesheets().add(CSSPath.POP_UP.getCssPath().toExternalForm());
        notificationBuilder.show();
    }

    private HBox formatNotification(ImagePath type, String text, String currentTime){
        HBox notification = createNotificationContainer();

        ImageView icon = createNotificationIcon(type);

        Label message = createNotificationText(type, text);

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

    private ImageView createNotificationIcon(ImagePath type){
        ImageView icon = new ImageView();
        icon.setFitHeight(ICON_FIT_HEIGHT);
        icon.setFitWidth(ICON_FIT_WIDTH);
        icon.setImage(Util.load(type));
        return icon;
    }

    private Label createNotificationText(ImagePath type, String text){
        Label message = new Label(text);
        message.setPrefHeight(TEXT_FRAME_HEIGHT);
        message.setPrefWidth(TEXT_FRAME_WIDTH);
        message.setAlignment(Pos.BOTTOM_LEFT);
        message.setTextAlignment(TextAlignment.CENTER);
        message.setStyle(TEXT_BACKGROUND_COLOR + TEXT_COLOR);
        if(type == ImagePath.ALARM){
            message.setStyle(TEXT_BACKGROUND_COLOR + TEXT_ALARM_COLOR);
        }
        message.setPadding(new Insets(PADDING_TOP, PADDING_RIGHT, PADDING_BOTTOM, PADDING_LEFT));
        message.setFont(Font.font(FONT_NAME, TEXT_FONT_SIZE));
        return message;
    }

    private Label createNotificationTime(String currentTime){
        Label time = new Label(currentTime);
        time.setPrefHeight(TIME_FRAME_HEIGHT);
        time.setPrefWidth(TIME_FRAME_WIDTH);
        time.setAlignment(Pos.CENTER);
        time.setStyle(TEXT_BACKGROUND_COLOR + TEXT_COLOR);
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
        addNotification(ImagePath.ALARM, ALARM_NOTIFICATION, Util.getFormattedTime());
    }

    @Override
    public void onHumidityUpdate(float value) {
        if(value > HUMIDITY_THRESHOLD){
            addNotification(ImagePath.HUMIDITY, HUMIDITY_NOTIFICATION, Util.getFormattedTime());
        }
    }

    @Override
    public void onFullnessUpdate(float value) {
        if(value > FULLNESS_THRESHOLD){
            addNotification(ImagePath.FULLNESS, FULLNESS_NOTIFICATION, Util.getFormattedTime());
        }
    }
}
