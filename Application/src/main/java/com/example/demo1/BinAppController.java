package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class BinAppController {

    @FXML
    private Label bin1_humid;

    @FXML
    private Label bin1_full;

    private Stage stage = new Stage();
    private Scene scene;
    private Parent root;

    @FXML
    private void initialize() {
        bin1_humid.setText("55");
        bin1_full.setText("11");
    }

    @FXML
    public void updateHumid(String newValue) {
        bin1_humid.setText(newValue);
    }

    @FXML
    public void updateFull(String newValue) {
        bin1_full.setText(newValue);
    }

    public void switchToNotificationPage(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("notificationPage.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
}