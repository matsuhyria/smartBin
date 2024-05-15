package com.example.demo1;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class StatisticsController {

    @FXML
    private Pane chartPane;

    @FXML
    private Label avgHumid;

    @FXML
    private Label avgFill;

    @FXML
    private Button showDaily;

    @FXML
    private Button showWeekly;
    private ChartBuilder chartBuilder;
    private DataManager dataManager;

    @FXML
    public void initialize() {
        this.chartBuilder = new ChartBuilder();
        this.dataManager = new DataManager();

        showDaily();
    }

    private void updateAverages() {
        String humid = Double.toString(dataManager.getAverageHumidity());
        avgHumid.setText(humid.substring(0, 2));

        String fill = Double.toString(dataManager.getAverageFullness());
        avgFill.setText(fill.substring(0, 2));
    }

    @FXML
    private void showDaily() {
        Pane dailyChart = chartBuilder.buildDailyChart(dataManager.getDailyData());

        chartPane.getChildren().clear();
        chartPane.getChildren().add(dailyChart);

        updateAverages();
    }

    @FXML
    private void showWeekly() {
        Pane weeklyChart = chartBuilder.buildWeeklyChart();

        chartPane.getChildren().clear();
        chartPane.getChildren().add(weeklyChart);

        String humid = Double.toString(dataManager.getAverageHumiditySummary());
        avgHumid.setText(humid.substring(0, 2));
        System.out.println(humid);

        String fill = Double.toString(dataManager.getAverageFullnessSummary());
        avgFill.setText(fill.substring(0, 2));
    }

}
