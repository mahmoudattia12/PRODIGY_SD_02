package com.example.guessinggame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class GuessingGameApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GuessingGameApp.class.getResource("GuessingGame-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1100, 500);
        stage.setTitle("Guessing Game!");
        stage.setScene(scene);
        stage.show();
    }
}