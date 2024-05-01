package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class BinAppController {
    private Stage stage = new Stage();
    private Scene scene;
    private Parent root;

    private CardController bin1;


    @FXML
    public void updateHumid(String newValue) {
        bin1.updateHumid(newValue);
    }

    @FXML
    public void updateFull(String newValue) {
        bin1.updateFull(newValue);
    }

    public void switchToMapPage(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("map.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }


}