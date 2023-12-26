package com.w9_assignment;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneManager {
    private Stage primaryStage;
    private static FXMLLoader fxmlLoader = new FXMLLoader();

    private static final SceneManager sceneManager = new SceneManager();

    private SceneManager() {}

    public static void switchScene(String sceneName) {
        if (sceneManager == null) {
            throw new NullPointerException("Not existed");
        }
        Scene scene;
        try {
            scene = generateScene(sceneName);
            Stage primaryStage = sceneManager.primaryStage;
            primaryStage.setScene(scene);
            primaryStage.setTitle("Converter");
            primaryStage.setResizable(false);
            primaryStage.centerOnScreen();
            primaryStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Scene generateScene(String sceneName) throws IOException {
        fxmlLoader =
                new FXMLLoader(ConverterApplication.class.getResource(sceneName));
        return new Scene(fxmlLoader.load());
    }

    public static void setPrimaryStage(Stage primaryStage) {
        sceneManager.primaryStage = primaryStage;
    }
}
