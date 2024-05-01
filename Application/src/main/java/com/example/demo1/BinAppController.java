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

    @FXML
    public void updateHumid(String newValue) {
        bin1_humid.setText(newValue);
    }

    @FXML
    public void updateFull(String newValue) {
        bin1_full.setText(newValue);
    }
}