package com.example.demo1;

import javafx.scene.chart.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class ChartBuilder {

    public Pane buildDailyChart(List<BinDataPoint> dataPoints) {

        // Create axes
        NumberAxis humidityAxis = new NumberAxis();
        humidityAxis.setLabel("Levels in %");
        CategoryAxis timeAxis = new CategoryAxis();
        timeAxis.setLabel("time (hours)");


        BarChart<String, Number> barChart = new BarChart<>(timeAxis, humidityAxis);
        barChart.setTitle("Fill & Humidity Levels");

        XYChart.Series<String, Number> fullness = new XYChart.Series<>();
        fullness.setName("Fullness");

        XYChart.Series<String, Number> humidity = new XYChart.Series<>();
        humidity.setName("Humidity");

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        // Populate the series with data points
        for (BinDataPoint dataPoint : dataPoints) {
            String time = dataPoint.getTime().substring(11, 16); // Extract the hour and minute from the timestamp
            fullness.getData().add(new XYChart.Data<>(time, dataPoint.getFullness()));
            humidity.getData().add(new XYChart.Data<>(time, dataPoint.getHumidity()));
        }


        barChart.getData().add(fullness);
        barChart.getData().add(humidity);

        String css = getClass().getResource("chartStyle.css").toExternalForm();
        barChart.getStylesheets().add(css);

        // Create a Pane to hold the chart
        StackPane chartPane = new StackPane(barChart);
        chartPane.setPrefSize(800, 600);

        return chartPane;
    }

    public Pane buildWeeklyChart() {
        //return buildDailyChart();
        return new Pane();
    }

}
