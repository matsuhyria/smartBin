package com.example.demo1;



public class BinUtil {
    public static double getX(double x) {
        double gridStart = 0.0;
        double gridEnd = 1450.0;

        double xLayout;
        if (x < gridStart+gridEnd/2){
            xLayout = x + 30.0;
        } else {
            xLayout = x - 360.0;
        }

        return xLayout;
    }

    public static double getY(double y) {
        double gridStart = 0.0;
        double gridEnd = 630.0;;

        double yLayout;
        if (y < gridStart+gridEnd/2){
            yLayout = y - 50.0;
        } else {
            yLayout = y - 270.0;
        }

        return yLayout;
    }
}
