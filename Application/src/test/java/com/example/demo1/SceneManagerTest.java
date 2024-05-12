package com.example.demo1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import static org.mockito.Mockito.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

@ExtendWith(MockitoExtension.class)
public class SceneManagerTest {
    
    @Mock
    public FXMLLoader mainPageLoader;

    @Mock
    private AnchorPane mainPage;

    @Mock
    private HBox header;

    @Mock 
    private SplitPane binCard;

    @Mock
    private Stage stage;



    // Singleton test
    @Test
    public void testGetInstance_shouldReturnSameInstance() {
        SceneManager firstInstance = SceneManager.getInstance();
        SceneManager secondInstance = SceneManager.getInstance();

        assertNotNull(firstInstance);
        assertNotNull(secondInstance);
        assertEquals(firstInstance, secondInstance);
    }

    @Test
    public void testSetNullStage_shouldThrowException(){
        assertThrows(IllegalArgumentException.class, () -> SceneManager.getInstance().setStage(null, 0, 0));
    }

    // @Test
    // public void testSwitchToMainPage_shouldChangeSceneContent() throws IOException{
    //     SceneManager sceneManager = SceneManager.getInstance();
    //     when(mainPageLoader.load()).thenReturn(mainPage);
    //     sceneManager.setStage(stage, 800, 600);

    //     sceneManager.switchToMainPage();

    //     verify(sceneManager.getCurrentPane().getChildren()).clear();
    //     verify(sceneManager.getCurrentPane().getChildren()).addAll(mainPage, header, binCard);

    //     verify(stage.getScene()).setRoot(sceneManager.getCurrentPane());
    // }




}
