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
    private final Pane currentScene = new Pane();
    private Pane mainPage;
    private Pane notificationPage;
    private Pane mapPage;
    private Pane statPage;
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
        this.stage = stage;
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
        FXMLLoader statLoader = new FXMLLoader(FXMLpath.STATISTICS_PAGE.getFxmlPath());
        statPage = statLoader.load();
        currentScene.getChildren().addAll(mainPage, header, binCard);
        stage.setScene(new Scene(currentScene, height, width));
        stage.show();
    }

    public void switchToMainPage() {
        currentScene.getChildren().clear();
        currentScene.getChildren().addAll(mainPage, header, binCard);
        stage.getScene().setRoot(currentScene);
    }

    public void switchToNotificationPage() {
        currentScene.getChildren().clear();
        currentScene.getChildren().addAll(notificationPage, header);
        stage.getScene().setRoot(currentScene);
    }

    public void switchToMapPage(){
        currentScene.getChildren().clear();
        currentScene.getChildren().addAll(mapPage, header);
        stage.getScene().setRoot(currentScene);
    }

    public void switchToStatsPage(){
        currentScene.getChildren().clear();
        ChartBuilder chart = new ChartBuilder();
        statPage.getChildren().add(chart.buildHumidityChart());
        currentScene.getChildren().addAll(statPage, header);
        currentScene.getStylesheets().add(getClass().getResource("chartStyle.css").toExternalForm());
        stage.getScene().setRoot(currentScene);
    }

    public CardController getBinCardController(){
        return this.binCardController;
    }

    public NotificationController getNotificationController(){
        return this.notificationController;
    }
}
