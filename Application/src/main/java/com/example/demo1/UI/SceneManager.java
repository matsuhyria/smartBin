package com.example.demo1.UI;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.example.demo1.Config.CSSPath;
import com.example.demo1.Config.FXMLpath;
import com.example.demo1.Core.FXMLControllers.CardController;
import com.example.demo1.Core.FXMLControllers.HeaderController;
import com.example.demo1.Core.FXMLControllers.MapController;
import com.example.demo1.Core.FXMLControllers.NotificationController;
import com.example.demo1.Core.FXMLControllers.StatisticsController;
import com.example.demo1.Util.Util;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SceneManager implements SceneSwitcher{
    private Stage stage;
    private final Pane rootPane = new Pane();
    private final Map<FXMLpath, Pane> sceneCache = new HashMap<>();

    private NotificationController notificationController;
    private CardController cardController;
    private HeaderController headerController;
    private MapController mapController;
    private StatisticsController statController;

    public SceneManager(NotificationController nController, CardController cController, MapController mController, StatisticsController sController) {
        notificationController = nController;
        cardController = cController;
        mapController = mController;
        statController = sController;
        headerController = new HeaderController(this);
    }

    public void setStage(Stage stage, int height, int width) throws IOException{
        if(stage != null){
            this.stage = stage;
        } else{
            throw new IllegalArgumentException("Stage cannot be null");
        }
        
        initializeSceneCache();

        //Default starting page
        rootPane.getChildren().addAll(sceneCache.get(FXMLpath.MAIN_PAGE), sceneCache.get(FXMLpath.HEADER), sceneCache.get(FXMLpath.BIN_CARD)); //Main page (Bin overview page is opened by default)
        stage.setScene(new Scene(rootPane, height, width));
    }

    @Override
    public void switchToMainPage() {
        switchScene(FXMLpath.MAIN_PAGE);
    }

    @Override
    public void switchToNotificationPage() {
        switchScene(FXMLpath.NOTIFICATION_PAGE);
    }

    @Override
    public void switchToMapPage(){
        switchScene(FXMLpath.MAP_PAGE);
    }

    @Override
    public void switchToStatsPage(){
        switchScene(FXMLpath.STATISTICS_PAGE);
    }

    private void switchScene(FXMLpath path) {
        Pane pane = sceneCache.get(path);
        if (pane == null) {
          throw new IllegalStateException("Scene with path " + path + " not found in sceneCache");
        }
        rootPane.getChildren().clear();
        if (path == FXMLpath.MAIN_PAGE) {
            rootPane.getChildren().addAll(pane, sceneCache.get(FXMLpath.HEADER), sceneCache.get(FXMLpath.BIN_CARD));
        } else {
            rootPane.getChildren().addAll(pane, sceneCache.get(FXMLpath.HEADER));
        }
        stage.getScene().setRoot(rootPane);
    }

    //Scene cache is used for retrieval and reuse of scenes, improves performance
    private void initializeSceneCache() throws IOException{
        Pane header = Util.load(FXMLpath.HEADER, headerController);
        sceneCache.put(FXMLpath.HEADER, header);

        Pane mainPage = Util.load(FXMLpath.MAIN_PAGE);
        sceneCache.put(FXMLpath.MAIN_PAGE, mainPage);
        
        Pane binCard = Util.load(FXMLpath.BIN_CARD, cardController);
        sceneCache.put(FXMLpath.BIN_CARD, binCard);

        Pane notificationPage = Util.load(FXMLpath.NOTIFICATION_PAGE, CSSPath.SCROLLBAR, notificationController);
        sceneCache.put(FXMLpath.NOTIFICATION_PAGE, notificationPage);

        Pane mapPage = Util.load(FXMLpath.MAP_PAGE, mapController);
        sceneCache.put(FXMLpath.MAP_PAGE, mapPage);

        Pane statPage = Util.load(FXMLpath.STATISTICS_PAGE, statController);
        sceneCache.put(FXMLpath.STATISTICS_PAGE, statPage);
    }

    public Pane getCurrentPane(){
        return rootPane;
    }

    public Pane getHeader() {
        return sceneCache.get(FXMLpath.HEADER);
    }

    public Pane getBinCard(){
        return sceneCache.get(FXMLpath.BIN_CARD);
    }
}
