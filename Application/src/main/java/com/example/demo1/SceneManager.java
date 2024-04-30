package com.example.demo1;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SceneManager {
    private static SceneManager instance;
    private Stage stage;
    private Pane mainPage;
    private Pane notificationPage;

    private SceneManager() {}

    public static SceneManager getInstance(){
        if(instance == null){
            instance = new SceneManager();
        }
        return instance;
    }

    public void setStage(Stage stage, int height, int width) throws IOException{
        this.stage = stage;
        FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("mainPage.fxml"));
        mainPage = mainLoader.load();
        FXMLLoader notificationLoader = new FXMLLoader(getClass().getResource("notificationPage.fxml"));
        notificationPage = notificationLoader.load();
        stage.setScene(new Scene(mainPage, height, width));
        stage.show();
    }

    public void switchToMainPage(){
        stage.getScene().setRoot(mainPage);
    }

    public void switchToNotificationPage(){
        stage.getScene().setRoot(notificationPage);
    }

    public void switchToMapPage(){
        //TO-DO
    }

    public void switchToStatsPage(){
        //TO-DO
    }

}
