package com.example.demo1.Core.FXMLControllers;

import com.example.demo1.Core.ChartBuilder;
import com.example.demo1.Core.Database.DataManager;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.layout.Pane;

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
    private final ChartBuilder chartBuilder;
    private final DataManager dataManager;

    public StatisticsController(ChartBuilder chartBuilder, DataManager dataManager){
        this.chartBuilder = chartBuilder;
        this.dataManager = dataManager;
    }

    @FXML
    private void highlightDailyButton (){
        ColorAdjust effect = new ColorAdjust();
        effect.setBrightness(0.2);
        showDaily.setEffect(effect);
    }

    @FXML
    private void deHighlightDailyButton (){
        showDaily.setEffect(null);
    }

    @FXML
    private void highlightWeeklyButton (){
        ColorAdjust effect = new ColorAdjust();
        effect.setBrightness(0.2);
        showWeekly.setEffect(effect);
    }

    @FXML
    private void deHighlightWeeklyButton (){
        showWeekly.setEffect(null);
    }

    //Updates average values showed on the right of the chart according to the data recorded in the database when daily chart is opened
    private void updateAverages() {
        String humid = Double.toString(dataManager.getAverageHumidity());
        avgHumid.setText(humid.substring(0, 2));

        String fill = Double.toString(dataManager.getAverageFullness());
        avgFill.setText(fill.substring(0, 2));
    }

    @FXML
    public void showDaily() {
        Pane dailyChart = chartBuilder.buildDailyChart(dataManager.getDailyData());

        chartPane.getChildren().clear();
        chartPane.getChildren().add(dailyChart);

        updateAverages();
    }

    @FXML
    public void showWeekly() {
        Pane weeklyChart = chartBuilder.buildWeeklyChart(dataManager.getWeeklyData());

        chartPane.getChildren().clear();
        chartPane.getChildren().add(weeklyChart);

        String humid = Double.toString(dataManager.getAverageHumiditySummary());
        avgHumid.setText(humid.substring(0, 2));

        String fill = Double.toString(dataManager.getAverageFullnessSummary());
        avgFill.setText(fill.substring(0, 2));
    }

}
