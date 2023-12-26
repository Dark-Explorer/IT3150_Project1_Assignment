package com.w9_assignment;

import javafx.application.Application;
import javafx.stage.Stage;

public class ConverterApplication extends Application {
    @Override
    public void start(Stage stage){
        SceneManager.setPrimaryStage(stage);
        SceneManager.switchScene("ASCIIConverter.fxml");
    }

    public static void main(String[] args) {
        launch();
    }
}