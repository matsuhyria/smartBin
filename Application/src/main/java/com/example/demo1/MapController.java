package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
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
    private int numOfBins;
    private boolean changeInProcess;
    @FXML
    private AnchorPane grid;

    @FXML
    private AnchorPane pane;
    @FXML
    private void initialize() throws IOException {
        numOfBins = 0;
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
        numOfBins++;
    }

    @FXML
    public void placePointer(double x, double y) throws IOException {
        numOfBins++;
        Circle pointer = getPointer();
        pointer.setLayoutX(x);
        pointer.setLayoutY(y);
        grid.getChildren().add(pointer);

        FXMLLoader loader = new FXMLLoader();

        AnchorPane popOut = loader.load(getClass().getResource("popOutLight.fxml").openStream());
        popOut.setVisible(false);
        Label name = (Label) popOut.lookup("#name");

        SplitPane infoPop;
        if (numOfBins == 1) {
            loader.setLocation(getClass().getResource("bin1.fxml"));
            infoPop = loader.load(getClass().getResource("bin1.fxml"));
            infoPop.setVisible(false);
        } else {
            loader.setLocation(getClass().getResource("binOther.fxml"));
            infoPop = loader.load(getClass().getResource("binOther.fxml"));
            infoPop.setVisible(false);
            name.setText("OTHER BIN");
        }

        infoPop.setLayoutX(x + 40.0);
        infoPop.setLayoutY(y + 40.0);

        pointer.setOnMouseClicked(event -> {
            popOut.setVisible(!popOut.isVisible());
            infoPop.setVisible(!infoPop.isVisible());
        });

        Label change = (Label) popOut.lookup("#changeLocation");

        change.setOnMouseClicked(event -> {
            try {
                changeLocation(x, y);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        Label delete = (Label) popOut.lookup("#delete");

        delete.setOnMouseClicked(event -> {
            try {
                deleteLocation(x, y);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        pane.getChildren().add(popOut);
        grid.getChildren().add(infoPop);
    }

    public void changeLocation(double x, double y) throws IOException {
        deleteLocation(x, y);
        addBin();
    }

    public void deleteLocation(double x, double y) throws IOException {
        pane.getChildren().clear();
        DatabaseHandler.getInstance().deleteLocation(x, y);
        grid.getChildren().clear();
        initialize();
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
