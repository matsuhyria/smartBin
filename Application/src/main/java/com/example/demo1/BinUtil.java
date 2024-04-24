package com.example.demo1;

public class BinUtil {
    public static int getX(double x) {
        int gridWidth = 24;
        double cellWidth = 62.1;

        int col = (int) Math.floor((x - 241) / cellWidth);

        col = Math.max(0, Math.min(col, gridWidth - 1));

        return col;
    }

    public static int getY(double y) {
        int gridHeight = 11;
        double cellHeight = 67.3;

        int row = (int) Math.floor((y - 305) / cellHeight);

        row = Math.max(0, Math.min(row, gridHeight - 1));

        return row;
    }
}
