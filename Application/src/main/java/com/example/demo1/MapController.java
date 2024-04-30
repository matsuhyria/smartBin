package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;

public class MapController {
    private Stage stage = new Stage();
    private Scene scene;
    private Parent root;
    private boolean changeInProcess;
    @FXML
    private AnchorPane grid;

    @FXML
    private AnchorPane pane;
    @FXML
    private void initialize() throws IOException {
        changeInProcess = false;
        double[] coordinates = DatabaseHandler.getInstance().getLocations();
        int index = 0;
        while (index < coordinates.length) {
            double x = coordinates[index];
            double y = coordinates[index + 1];
            placePointer(x, y);
            index = index + 2;

        }
    }

    @FXML
    private void chooseNextPosition(MouseEvent event) throws IOException {
        if (changeInProcess) {
            double x = event.getX();
            double y = event.getY();

            DatabaseHandler.getInstance().addLocation(x, y);
            initialize();
        }
    }

    @FXML
    public void addBin() {
        changeInProcess = true;
    }

    @FXML
    public void placePointer(double x, double y) throws IOException {
        Circle pointer = getPointer();
        pointer.setLayoutX(x);
        pointer.setLayoutY(y);
        System.out.println(x);
        System.out.println(y);
        grid.getChildren().add(pointer);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("popOutLight.fxml"));
        AnchorPane popOut = loader.load();
        popOut.setVisible(false);

        pointer.setOnMouseClicked(event -> {
            popOut.setVisible(!popOut.isVisible());
        });

        Label change = (Label) popOut.lookup("#changeLocation");

        change.setOnMouseClicked(event -> {
            changeLocation();
        });

        Label delete = (Label) popOut.lookup("#delete");

        delete.setOnMouseClicked(event -> {
            deleteLocation();
        });

        pane.getChildren().add(popOut);
    }

    public void changeLocation(){
        deleteLocation();
        addBin();
    }

    public void deleteLocation(){
        grid.getChildren().clear();
        pane.getChildren().clear();
        DatabaseHandler.getInstance().deleteLocation();
    }

    public Circle getPointer(){
        Circle circle = new Circle();
        circle.setRadius(20);
        circle.setFill(Color.web("#A6CA82"));
        circle.setStroke(Color.web("#588889"));
        return circle;
    }

    public void switchToMainPage(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("mainPage.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

}
