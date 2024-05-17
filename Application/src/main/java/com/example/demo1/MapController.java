package com.example.demo1;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.io.IOException;


public class MapController {
    private int numOfBins;
    private boolean changeInProcess;
    private CardController cardController;

    @FXML
    private AnchorPane grid;
    @FXML
    private AnchorPane pane;
    @FXML
    private Button add;
    @FXML
    private void initialize() throws IOException {
        numOfBins = 0;
        changeInProcess = false;
        double[] coordinates = DatabaseHandler.getInstance().getLocations();
        int index = 0;
        while (index < coordinates.length) {
            double x = coordinates[index];
            double y = coordinates[index + 1];
            placePointer(x, y);
            index = index + 2;
        }
    }

    public MapController(CardController cController){
        cardController = cController;
    }

    @FXML
    private void highlightAdd (){
        ColorAdjust effect = new ColorAdjust();
        effect.setBrightness(0.2);
        add.setEffect(effect);
    }

    @FXML
    private void deHighlightAdd (){
        add.setEffect(null);
    }

    @FXML
    private void chooseNextPosition(MouseEvent event) throws IOException {
        if (changeInProcess) {
            double x = event.getX();
            double y = event.getY();

            DatabaseHandler.getInstance().addLocation(x, y);
            initialize();
        }
    }

    @FXML
    public void addBin() {
        changeInProcess = true;
        numOfBins++;
    }

    @FXML
    public void placePointer(double x, double y) throws IOException {
        numOfBins++;
        Circle pointer = getPointer();
        pointer.setLayoutX(x);
        pointer.setLayoutY(y);
        grid.getChildren().add(pointer);

        AnchorPane popOut = loadPopOutLight();
        popOut.setVisible(false);

        Pane infoPop = loadBinInfo(numOfBins);
        infoPop.setVisible(false);

        double xCoordLayout = BinUtil.getX(x);
        double yCoordLayout = BinUtil.getY(y);
        infoPop.setLayoutX(xCoordLayout);
        infoPop.setLayoutY(yCoordLayout);

        setPointerClickBehavior(pointer, popOut, infoPop);
        setChangeLocationBehavior(popOut, x, y);
        setDeleteLocationBehavior(popOut, x, y);

        pane.getChildren().add(popOut);
        grid.getChildren().add(infoPop);
    }

    private AnchorPane loadPopOutLight() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("popOutLight.fxml"));
        AnchorPane popOut = loader.load();
        Label name = (Label) popOut.lookup("#name");
        if (name != null) {
            name.setText(numOfBins == 1 ? "Bin 1" : "OTHER BIN");
        }
        return popOut;
    }

    private Pane loadBinInfo(int numOfBins) throws IOException {

        Pane infoPop;
        if (numOfBins == 1) {
            infoPop = Util.load(FXMLpath.BIN_CARD, cardController);
        } else {
            infoPop = Util.load(FXMLpath.BIN_CARD_OTHER);
            Label name = (Label) infoPop.lookup("#name");
            if (name != null) {
                name.setText("OTHER BIN");
            }
        }
        return infoPop;
    }

    private void setPointerClickBehavior(Circle pointer, AnchorPane popOut, Pane infoPop) {
        pointer.setOnMouseClicked(event -> {
            popOut.setVisible(!popOut.isVisible());
            infoPop.setVisible(!infoPop.isVisible());
        });
    }

    private void setChangeLocationBehavior(AnchorPane popOut, double x, double y) {
        Label change = (Label) popOut.lookup("#changeLocation");
        change.setOnMouseEntered(event -> change.setStyle("-fx-background-color: #7ebc59; -fx-text-fill: white;"));
        change.setOnMouseExited(event -> change.setStyle(""));
        change.setOnMouseClicked(event -> {
            change.setStyle("-fx-background-color: #588889; -fx-text-fill: white;");
            try {
                changeLocation(x, y);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            change.setStyle("");
        });
    }

    private void setDeleteLocationBehavior(AnchorPane popOut, double x, double y) {
        Label delete = (Label) popOut.lookup("#delete");
        delete.setOnMouseEntered(event -> delete.setStyle("-fx-background-color: #7ebc59; -fx-text-fill: white;"));
        delete.setOnMouseExited(event -> delete.setStyle(""));
        delete.setOnMouseClicked(event -> {
            delete.setStyle("-fx-background-color: #588889; -fx-text-fill: white;");
            try {
                deleteLocation(x, y);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            delete.setStyle("");
        });
    }

    public void changeLocation(double x, double y) throws IOException {
        deleteLocation(x, y);
        addBin();
    }

    public void deleteLocation(double x, double y) throws IOException {
        pane.getChildren().clear();
        DatabaseHandler.getInstance().deleteLocation(x, y);
        grid.getChildren().clear();
        initialize();
    }

    public Circle getPointer(){
        Circle circle = new Circle();
        circle.setRadius(20);
        circle.setFill(Color.web("#A6CA82"));
        circle.setStroke(Color.web("#588889"));
        return circle;
    }

    public int getNumOfBins() {
        return numOfBins;
    }

    public boolean isChangeInProcess() {
        return changeInProcess;
    }
}
