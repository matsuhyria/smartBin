package com.example.demo1;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.jupiter.api.Test;

import com.example.demo1.Config.FXMLpath;

public class FXMLpathTest{

    @Test
    public void testGetHeaderFxmlPath_ReturnsCorrectPath() {
        FXMLpath path = FXMLpath.HEADER;
        URL url = path.getFxmlPath();
        assertNotNull(url);
        assertTrue(url.getPath().contains("header.fxml"));
    }

    @Test
    public void testGetBinCardFxmlPath_ReturnsCorrectPath()  {
        FXMLpath path = FXMLpath.BIN_CARD;
        URL url = path.getFxmlPath();
        assertNotNull(url);
        assertTrue(url.getPath().contains("bin1.fxml"));
    }

    @Test
    public void testGetMainPageFxmlPath_ReturnsCorrectPath() {
        FXMLpath path = FXMLpath.MAIN_PAGE;
        URL url = path.getFxmlPath();
        assertNotNull(url);
        assertTrue(url.getPath().contains("mainPage.fxml"));
    }

    @Test
    public void testGetMapFxmlPath_ReturnsCorrectPath() {
        FXMLpath path = FXMLpath.MAP_PAGE;
        URL url = path.getFxmlPath();
        assertNotNull(url);
        assertTrue(url.getPath().contains("map.fxml"));
    }

    @Test
    public void testGetNotificationFxmlPath_ReturnsCorrectPath() {
        FXMLpath path = FXMLpath.NOTIFICATION_PAGE;
        URL url = path.getFxmlPath();
        assertNotNull(url);
        assertTrue(url.getPath().contains("notificationPage.fxml"));
    }

}