package com.example.demo1;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;


public class Util {
    
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
        if (path == FXMLpath.NOTIFICATION_PAGE) {
            pane.getStylesheets().add(Util.class.getResource("scrollbar.css").toExternalForm());
        }
        return pane;
    }

    public static String getFormattedTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return formatter.format(now);
    }

    public static Image load(ImagePath path) {
        return new Image(path.getPath().toString());
    }

}
