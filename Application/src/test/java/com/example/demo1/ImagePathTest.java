package com.example.demo1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

public class ImagePathTest {

    @Test
    public void test_getPath_overviewActive() {
        ImagePath imagePath = ImagePath.OVERVIEW_ACTIVE;
        assertTrue(imagePath.getPath().toString().contains("OverviewActive.png"));
    }

    @Test
    public void test_getPath_OverviewClosed() {
        ImagePath imagePath = ImagePath.OVERVIEW_CLOSED;
        assertTrue(imagePath.getPath().toString().contains("OverviewClosed.png"));
    }

    @Test
    public void test_getPath_notificationActive() throws IOException {
        ImagePath imagePath = ImagePath.NOTIFICATION_ACTIVE;
        assertTrue(imagePath.getPath().toString().contains("NotificationActive.png"));
    }

    @Test
    public void test_getPath_notificationClosed() {
        ImagePath imagePath = ImagePath.NOTIFICATION_CLOSED;
        assertTrue(imagePath.getPath().toString().contains("NotificationClosed.png"));
    }


    @Test
    public void test_getPath_mapActive() {
        ImagePath imagePath = ImagePath.MAP_ACTIVE;
        assertTrue(imagePath.getPath().toString().contains("MapActive.png"));
    }

    @Test
    public void test_getPath_mapClosed() {
        ImagePath imagePath = ImagePath.MAP_CLOSED;
        assertTrue(imagePath.getPath().toString().contains("MapClosed.png"));
    }


    @Test
    public void test_getPath_StatisticsActive() {
        ImagePath imagePath = ImagePath.STATISTICS_ACTIVE;
        assertTrue(imagePath.getPath().toString().contains("StatisticsActive.png"));
    }

    @Test
    public void test_getPath_StatisticsClosed() {
        ImagePath imagePath = ImagePath.STATISTICS_CLOSED;
        assertTrue(imagePath.getPath().toString().contains("StatisticsClosed.png"));
    }

}