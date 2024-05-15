package com.example.demo1;

import static org.junit.Assert.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;

import org.junit.Test;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit.ApplicationTest;

import com.kenai.jffi.Platform;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SceneManagerTest extends ApplicationTest {

   

    // @Override
    // public void start(Stage stage) throws IOException {
    //     sceneManager.setStage(stage, 1920, 1080);
    //     stage.show();
    //     stage.toFront();
    // }

    // @Test
    // public void testSetNullStage_shouldThrowException(){
    //     assertThrows(IllegalArgumentException.class, () -> SceneManager.getInstance().setStage(null, 0, 0));
    // }

    // @Test
    // public void testHeaderHasButtons_shouldEqualFour(){
    //     Pane header = sceneManager.getHeader();
    //     assertEquals(4, header.getChildren().size());
    // }

    // @Test
    // public void testSwitchToMainPage_shouldChangeSceneContent() throws IOException {
    //     Pane header = sceneManager.getHeader();
    //     ImageView button = from(header).lookup("#mainPageImage").query();

    //     new FxRobot().clickOn(button);

    //     sleep(100);

    //     assertNotSame(currentPane.getChildren(), button.getScene().getRoot());
    // }
}
