package com.example.demo1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.mockito.Mock;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class NotificationControllerTest{

    @Test
    public void testAddNotification_shouldIncreaseVBoxSize() throws IOException {
        System.out.println((getClass().getResource(FXMLpath.NOTIFICATION_PAGE.toString())));
        FXMLLoader notificationLoader = new FXMLLoader(getClass().getResource(FXMLpath.NOTIFICATION_PAGE.toString()));
        Pane notificationPage = notificationLoader.load();
        NotificationController controller = notificationLoader.getController();


        controller.addNotification(true, "Bin is full!", "10:30 AM");
        // Size 3 is expected since the scene starts with 2 notifications
        assertEquals(3, controller.getNotificationList().getChildren().size());
        controller.addNotification(false, "Humidity is high!", "11:40 AM");
        // Size 4 is expected since the scene starts with 2 notifications
        assertEquals(4, controller.getNotificationList().getChildren().size());
}

}