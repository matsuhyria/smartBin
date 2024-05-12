package com.example.demo1;

import javafx.scene.shape.Circle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MapControllerTest {

    private MapController mapController;

    @BeforeEach
    public void setUp() {
        mapController = new MapController();
    }

    @Test
    public void testAddBin() {
        mapController.addBin();
        assertTrue(mapController.isChangeInProcess());
        assertEquals(1, mapController.getNumOfBins());
    }

    @Test
    public void testGetPointer() {
        Circle pointer = mapController.getPointer();
        assertNotNull(pointer);
        assertEquals(20, pointer.getRadius());
    }
}
