package com.example.guessinggame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;

public class GuessingGameController {
    public TextField inputStartRange;
    public TextField inputEndRange;
    public TextField guessedNumber;
    public VBox resultField;
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public void onInputKeyTyped(KeyEvent keyEvent) {
    }

    public void onGenerateButtonClick(ActionEvent actionEvent) {
    }

    public void onGuessButtonClick(ActionEvent actionEvent) {
    }
}