package com.example.demo1;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SceneManager {
    private static SceneManager instance;
    private Stage stage;
    private final Pane currentPane = new Pane();
    private Pane mainPage;
    private Pane notificationPage;
    private Pane mapPage;
    private Pane header;
    private SplitPane binCard;
    private NotificationController notificationController;
    private CardController binCardController;

    private SceneManager() {}

    public static SceneManager getInstance(){
        if(instance == null){
            instance = new SceneManager();
        }
        return instance;
    }

    public void setStage(Stage stage, int height, int width) throws IOException{
        if(stage != null){
            this.stage = stage;
        } else{
            throw new IllegalArgumentException("Stage cannot be null");
        }
        FXMLLoader headerLoader = new FXMLLoader(FXMLpath.HEADER.getFxmlPath());
        header = headerLoader.load();
        FXMLLoader mainLoader = new FXMLLoader(FXMLpath.MAIN_PAGE.getFxmlPath());
        mainPage = mainLoader.load();
        FXMLLoader binCardLoader = new FXMLLoader(FXMLpath.BIN_CARD.getFxmlPath());
        binCard = binCardLoader.load();
        binCardController = binCardLoader.getController();
        FXMLLoader notificationLoader = new FXMLLoader(FXMLpath.NOTIFICATION_PAGE.getFxmlPath());
        notificationPage = notificationLoader.load();
        notificationController = notificationLoader.getController();
        FXMLLoader mapLoader = new FXMLLoader(FXMLpath.MAP_PAGE.getFxmlPath());
        mapPage = mapLoader.load();
        currentPane.getChildren().addAll(mainPage, header, binCard);
        stage.setScene(new Scene(currentPane, height, width));
        stage.show();
    }

    public void switchToMainPage() {
        currentPane.getChildren().clear();
        currentPane.getChildren().addAll(mainPage, header, binCard);
        stage.getScene().setRoot(currentPane);
    }

    public void switchToNotificationPage() {
        currentPane.getChildren().clear();
        currentPane.getChildren().addAll(notificationPage, header);
        stage.getScene().setRoot(currentPane);
    }

    public void switchToMapPage(){
        currentPane.getChildren().clear();
        currentPane.getChildren().addAll(mapPage, header);
        stage.getScene().setRoot(currentPane);
    }

    public void switchToStatsPage(){
        //TO-DO
    }

    public CardController getBinCardController(){
        return this.binCardController;
    }

    public NotificationController getNotificationController(){
        return this.notificationController;
    }

    public Pane getCurrentPane(){
        return currentPane;
    }
}
