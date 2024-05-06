package com.example.demo1;

import javafx.fxml.FXML;

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

}