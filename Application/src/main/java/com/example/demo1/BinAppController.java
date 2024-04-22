package com.example.demo1;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class BinAppController {

    @FXML
    private Label bin1_humid;

    @FXML
    private Label bin1_full;

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

}