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

        Parent root = FXMLLoader.load(getClass().getResource("map.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }


}