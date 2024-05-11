package com.example.demo1;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class BinUtilTest {
    @Test
    public void testGetX_CalculatesLayoutPositionCorrectly() {
        //case where x < gridStart + gridEnd/2
        double x1 = 100.0;
        double expectedX1 = x1 + 30.0;
        assertEquals(expectedX1, BinUtil.getX(x1));

        //case where x >= gridStart + gridEnd/2
        double x2 = 800.0;
        double expectedX2 = x2 - 360.0;
        assertEquals(expectedX2, BinUtil.getX(x2));
    }

    @Test
    public void testGetY_CalculatesLayoutPositionCorrectly() {
        //case where y < gridStart + gridEnd/2
        double y1 = 200.0;
        double expectedY1 = y1 - 50.0;
        assertEquals(expectedY1, BinUtil.getY(y1));

        //case where y >= gridStart + gridEnd/2
        double y2 = 700.0;
        double expectedY2 = y2 - 270.0;
        assertEquals(expectedY2, BinUtil.getY(y2));
    }

}
