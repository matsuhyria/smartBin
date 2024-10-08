package com.example.demo1.Util;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.example.demo1.Config.CSSPath;
import com.example.demo1.Config.FXMLpath;
import com.example.demo1.Config.ImagePath;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;


public class Util {
    //Overloaded method to set a custom controller and stylesheet if needed
    public static Pane load(FXMLpath path) throws IOException{
        FXMLLoader loader = new FXMLLoader(path.getFxmlPath());
        Pane pane = loader.load();
        return pane;
    }

    public static Pane load(FXMLpath path, Object controller) throws IOException{
        FXMLLoader loader = new FXMLLoader(path.getFxmlPath());
        if(controller != null){
            loader.setController(controller);
        }
        Pane pane = loader.load();
        return pane;
    }

    public static Pane load(FXMLpath path, CSSPath cssFile, Object controller) throws IOException{
        FXMLLoader loader = new FXMLLoader(path.getFxmlPath());
        if(controller != null){
            loader.setController(controller);
        }
        Pane pane = loader.load();
        pane.getStylesheets().add(cssFile.getCssPath().toExternalForm());
        return pane;
    }

    public static Image load(ImagePath path) {
        return new Image(path.getPath().toString());
    }

    public static String getFormattedTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return formatter.format(now);
    }

    public static Node loadNotificationIcon(ImagePath icon){
        ImageView image = new ImageView(icon.getPath());
        image.setFitHeight(60);
        image.setFitWidth(60);
        return image;
    }

    //Used to determine X coordinate of the popup with bin information (doesn't block the view of location adjustment pane)
    public static double getBinX(double x) {
        double gridStart = 0.0;
        double gridEnd = 1450.0; //Length of the map pane

        double xLayout;
        if (x < gridStart+gridEnd/2){
            xLayout = x + 30.0;
        } else {
            xLayout = x - 360.0;
        }

        return xLayout;
    }

    //Used to determine Y coordinate of the popup with bin information (doesn't block the view of location adjustment pane)
    public static double getBinY(double y) {
        double gridStart = 0.0;
        double gridEnd = 630.0;; //Height of the map pane

        double yLayout;
        if (y < gridStart+gridEnd/2){
            yLayout = y - 50.0;
        } else {
            yLayout = y - 270.0;
        }

        return yLayout;
    }

}
