package com.example.demo1.Core.FXMLControllers;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import com.example.demo1.Config.ImagePath;
import com.example.demo1.UI.SceneSwitcher;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

//consider creating an interface for ImageView states

public class HeaderController implements Initializable{
    @FXML
    private Button mainPage;
    @FXML
    private ImageView mainPageImage;

    @FXML
    private Button notificationPage;
    @FXML
    private ImageView notificationPageImage;

    @FXML
    private Button mapPage;
    @FXML
    private ImageView mapPageImage;

    @FXML
    private Button statsPage;
    @FXML
    private ImageView statsPageImage;

    private ImageView currentActiveImageView;

    private static final Map<ImagePath, Image> imageCache = new HashMap<>();
    private final SceneSwitcher sceneSwitcher;

    public HeaderController(SceneSwitcher sceneSwitcher){
        this.sceneSwitcher = sceneSwitcher;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mainPage.setOnAction(event -> switchToMainPage());
        notificationPage.setOnAction(event -> switchToNotificationPage());
        mapPage.setOnAction(event -> switchToMapPage());
        statsPage.setOnAction(event -> switchToStatsPage());
        currentActiveImageView = mainPageImage;
        setImage(mainPageImage, ImagePath.OVERVIEW_ACTIVE);
    }

    private void switchToMainPage(){
        sceneSwitcher.switchToMainPage();
        deactivateImage(currentActiveImageView);
        currentActiveImageView = mainPageImage;
        setImage(mainPageImage, ImagePath.OVERVIEW_ACTIVE);
    }

    private void switchToNotificationPage(){
        sceneSwitcher.switchToNotificationPage();
        deactivateImage(currentActiveImageView);
        currentActiveImageView = notificationPageImage;
        setImage(notificationPageImage, ImagePath.NOTIFICATION_ACTIVE);
    }

    private void switchToMapPage(){
        sceneSwitcher.switchToMapPage();
        deactivateImage(currentActiveImageView);
        currentActiveImageView = mapPageImage;
        setImage(mapPageImage, ImagePath.MAP_ACTIVE);
    }

    private void switchToStatsPage(){
        sceneSwitcher.switchToStatsPage();
        deactivateImage(currentActiveImageView);
        currentActiveImageView = statsPageImage;
        setImage(statsPageImage, ImagePath.STATISTICS_ACTIVE);
    }

    private void deactivateImage(ImageView image){
        if(image == mainPageImage){
            setImage(image, ImagePath.OVERVIEW_CLOSED);
        } else if(image == notificationPageImage){
            setImage(image, ImagePath.NOTIFICATION_CLOSED);
        } else if(image == mapPageImage){
            setImage(image, ImagePath.MAP_CLOSED);
        } else{
            setImage(image, ImagePath.STATISTICS_CLOSED);
        }
    }

    private void setImage(ImageView imageView, ImagePath image) {
        Image pic = imageCache.get(image);
        if (pic == null) {
            pic = new Image(image.getPath().toString());
            imageCache.put(image, pic);
        }
        imageView.setImage(pic);
    }
}
