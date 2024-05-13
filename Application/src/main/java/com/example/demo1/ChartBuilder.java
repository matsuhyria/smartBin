package com.example.demo1;

import javafx.scene.chart.AreaChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Pane;

public class ChartBuilder {
    public Pane buildHumidityChart() {
        // Create axes
        NumberAxis humidityAxis = new NumberAxis(0, 100, 10);
        humidityAxis.setLabel("Percentage (%)");
        NumberAxis timeAxis = new NumberAxis(6, 24, 1);
        timeAxis.setLabel("Time");

        timeAxis.setTickLabelFormatter(new NumberAxis.DefaultFormatter(timeAxis) {
            @Override
            public String toString(Number object) {
                int hour = object.intValue();
                return String.format("%02d:00", hour);
            }
        });

        // Create the area chart
        AreaChart<Number, Number> areaChart = new AreaChart<>(timeAxis, humidityAxis);
        areaChart.setTitle("Humidity Level");

        // Create sample data
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.getData().add(new XYChart.Data<>(6, 20));
        series.getData().add(new XYChart.Data<>(9, 40));
        series.getData().add(new XYChart.Data<>(12, 60));
        series.getData().add(new XYChart.Data<>(15, 80));
        series.getData().add(new XYChart.Data<>(18, 70));
        series.getData().add(new XYChart.Data<>(21, 50));
        series.getData().add(new XYChart.Data<>(24, 30));

        // Add data to the chart
        areaChart.getData().add(series);

        // Create a Pane to hold the chart
        Pane chartPane = new Pane(areaChart);

        return chartPane;
    }
}
