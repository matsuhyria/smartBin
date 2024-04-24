package com.example.demo1;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.io.IOException;

public class MapController {
    private boolean changeInProcess;
    @FXML
    private GridPane grid;

    @FXML
    private AnchorPane pane;
    @FXML
    private void initialize() throws IOException {
        changeInProcess = false;
        int[] coordinates = DatabaseHandler.getInstance().getLocations();
        int index = 0;
        while (index < coordinates.length) {
            int x = coordinates[index];
            int y = coordinates[index + 1];
            placePointer(x, y);
            index = index + 2;

        }
    }

    @FXML
    private void chooseNextPosition(MouseEvent event) throws IOException {
        System.out.println(changeInProcess);
        if (changeInProcess) {
            double x = event.getX();
            double y = event.getY();
            int row = BinUtil.getX(x);
            int column = BinUtil.getY(y);

            DatabaseHandler.getInstance().addLocation(column, row);
            initialize();
        }
    }

    @FXML
    public void addBin() {
        System.out.println("01");
        changeInProcess = true;
    }

    @FXML
    public void placePointer(int x, int y) throws IOException {
        Circle pointer = getPointer();
        grid.add(pointer, x, y);

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
        circle.setRadius(10);
        circle.setFill(Color.web("#A6CA82"));
        circle.setStroke(Color.web("#588889"));
        return circle;
    }

}
