package com.example.demo1;

import org.junit.jupiter.api.Test;

import com.example.demo1.ImagePath;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ImagePathTest {

    @Test
    public void test_getPath_overviewActive() {
        ImagePath imagePath = ImagePath.OVERVIEW_ACTIVE;
        assertEquals("OverviewActive.png", imagePath.getPath());
    }

    @Test
    public void test_getPath_OverviewClosed() {
        ImagePath imagePath = ImagePath.OVERVIEW_CLOSED;
        assertEquals("OverviewClosed.png", imagePath.getPath());
    }

    @Test
    public void test_getPath_notificationActive() {
        ImagePath imagePath = ImagePath.NOTIFICATION_ACTIVE;
        assertEquals("NotificationActive.png", imagePath.getPath());
    }

    @Test
    public void test_getPath_notificationClosed() {
        ImagePath imagePath = ImagePath.NOTIFICATION_CLOSED;
        assertEquals("NotificationClosed.png", imagePath.getPath());
    }


    @Test
    public void test_getPath_mapActive() {
        ImagePath imagePath = ImagePath.MAP_ACTIVE;
        assertEquals("MapActive.png", imagePath.getPath());
    }

    @Test
    public void test_getPath_mapClosed() {
        ImagePath imagePath = ImagePath.MAP_CLOSED;
        assertEquals("MapClosed.png", imagePath.getPath());
    }


    @Test
    public void test_getPath_StatisticsActive() {
        ImagePath imagePath = ImagePath.STATISTICS_ACTIVE;
        assertEquals("StatisticsActive.png", imagePath.getPath());
    }

    @Test
    public void test_getPath_StatisticsClosed() {
        ImagePath imagePath = ImagePath.STATISTICS_CLOSED;
        assertEquals("StatisticsClosed.png", imagePath.getPath());
    }


    
}