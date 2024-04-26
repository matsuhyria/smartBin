package com.example.demo1;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class NotificationController implements Initializable{

    @FXML
    private VBox notificationList;

    @FXML
    private Label testLabel;

    private Stage stage = new Stage();
    private Scene scene;
    private Parent root;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(notificationList == null){
            System.out.println("NotificationList is not intialized");
        }
    }

    @FXML
    public void addNotification(String message) {
        Label newLabel = new Label(message);
        newLabel.setStyle("-fx-font-size: 79;");
        notificationList.getChildren().add(0, newLabel);
    }

    @FXML
    public void test(String message) {
        testLabel.setText(message);
    }

    public void switchToMainPage(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("mainPage.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
}
