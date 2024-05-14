package com.example.demo1;

import javafx.scene.Node;
import javafx.scene.chart.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class ChartBuilder {
    public Pane buildHumidityChart() {
        // Create axes
        NumberAxis timeAxis = new NumberAxis();
        timeAxis.setLabel("Levels in %");
        CategoryAxis humidityAxis = new CategoryAxis();
        humidityAxis.setLabel("time (hours)");

//        timeAxis.setTickLabelFormatter(new NumberAxis.DefaultFormatter(timeAxis) {
//            @Override
//            public String toString(Number object) {
//                int hour = object.intValue();
//                return String.format("%02d:00", hour);
//            }
//        });

        // Create the area chart
        BarChart<String, Number> barChart = new BarChart<>(humidityAxis, timeAxis);
        barChart.setTitle("Fill & Humidity Levels");

        // Create sample data
        XYChart.Series<String, Number> fullness = new XYChart.Series<>();
        fullness.setName("Fullness");
        fullness.getData().add(new XYChart.Data<>("06.00", 20));
        fullness.getData().add(new XYChart.Data<>("09.00", 40));
        fullness.getData().add(new XYChart.Data<>("12.00", 60));
        fullness.getData().add(new XYChart.Data<>("15.00", 80));
        fullness.getData().add(new XYChart.Data<>("18.00", 70));
        fullness.getData().add(new XYChart.Data<>("21.00", 50));
        fullness.getData().add(new XYChart.Data<>("24.00", 30));

        // Create sample data
        XYChart.Series<String, Number> humidity = new XYChart.Series<>();
        humidity.setName("Humidity");
        humidity.getData().add(new XYChart.Data<>("06.00", 60));
        humidity.getData().add(new XYChart.Data<>("09.00", 23));
        humidity.getData().add(new XYChart.Data<>("12.00", 34));
        humidity.getData().add(new XYChart.Data<>("15.00", 80));
        humidity.getData().add(new XYChart.Data<>("18.00", 78));
        humidity.getData().add(new XYChart.Data<>("21.00", 36));
        humidity.getData().add(new XYChart.Data<>("24.00", 12));

        // Add data to the chart
        barChart.getData().add(fullness);
        barChart.getData().add(humidity);

        String css = getClass().getResource("chartStyle.css").toExternalForm();
        barChart.getStylesheets().add(css);

        // Create a Pane to hold the chart
        StackPane chartPane = new StackPane(barChart);
        chartPane.setPrefSize(800, 600);

        return chartPane;
    }
}
