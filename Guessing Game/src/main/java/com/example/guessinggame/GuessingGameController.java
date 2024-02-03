package com.example.guessinggame;

import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Random;

public class GuessingGameController {

    @FXML
    private TextField inputStartRange;
    @FXML
    private TextField inputEndRange;
    @FXML
    private TextField guessedNumberField;
    @FXML
    private HBox resultField;
    @FXML
    private HBox generationHBox;
    private TextField currentTextField;
    private int generatedNumber;
    private int attempts;

    @FXML
    private void initialize() {
        currentTextField = inputStartRange;
        inputStartRange.setText("1");
        inputEndRange.setText("6");
        attempts = 0;
        generateRandom(1, 6);
    }

    public void onGenerateButtonClick(ActionEvent actionEvent) {
        String startRangeStr = inputStartRange.getText(), endRangeStr = inputEndRange.getText();
        generationHBox.getChildren().clear();
        resultField.getChildren().clear();
        attempts = 0;
        if (isNumeric(startRangeStr) && isNumeric(endRangeStr)) {
            int start = Integer.parseInt(startRangeStr), end = Integer.parseInt(endRangeStr);
            if (start > end) showAlert("Start range must be smaller or equal to the end range!");
            else generateRandom(start, end);
        } else {
            //alert
            showAlert("Please enter an integer value for the start and end range!");
        }
    }

    private void generateRandom(int start, int end) {
        Random random = new Random();
        generatedNumber = random.nextInt(end - start + 1) + start;
        Label label = new Label("Number generated successfully");
        label.getStyleClass().add("dynamicLabel");
        label.setId("generationLabel");
        generationHBox.getChildren().addAll(label);
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Input Error");

        Image icon = new Image(String.valueOf(getClass().getResource("warning.png")));
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(icon);

        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void onGuessButtonClick(ActionEvent actionEvent) {
        String guessedStr = guessedNumberField.getText();
        resultField.getChildren().clear();
        if (isNumeric(guessedStr)) {
            int guessedNumber = Integer.parseInt(guessedStr);
            attempts++;
            resultField.getChildren().addAll(addLabel(guessedNumber));
        } else {
            //alert
            showAlert("Please enter an integer value for the guessed number!");
        }
    }

    private Label addLabel(int guessedNumber) {
        Label label;
        if (guessedNumber == generatedNumber) {
            label = new Label("Congratulations you've guessed the number successfully\n" + "Number of attempts = " + attempts);
            label.getStyleClass().add("dynamicLabel");
            label.setId("generationLabel");
            attempts = 0;
            playCelebrationAnimation(label);
        } else {
            if (guessedNumber > generatedNumber) label = new Label("Too high (larger than the generated number)");
            else label = new Label("Too low (lower than the generated number)");
            label.getStyleClass().add("dynamicLabel");
            label.setId("wrongLabel");
        }
        return label;
    }

    public void onNumberInputTyped(KeyEvent keyEvent) {
        if (currentTextField == null) return;
        String input = currentTextField.getText();
        int indexPos = currentTextField.getCaretPosition() - 1;
        if (!isValidChar(input, keyEvent.getCharacter().charAt(0), indexPos)) {
            keyEvent.consume();
            // Remove the wrong character from the text field
            if (indexPos != -1) currentTextField.setText(input.substring(0, indexPos) + input.substring(indexPos + 1));
            currentTextField.positionCaret(indexPos);
        }
    }

    @FXML
    private void onTextFieldClicked(MouseEvent event) {
        if (event.getSource() instanceof TextField) {
            currentTextField = (TextField) event.getSource();
        }
    }

    private boolean isValidChar(String input, char curr, int pos) {
        return Character.isDigit(curr) || (pos == 0 && curr == '-' && input.chars().filter(ch -> ch == '-').count() == 1);
    }

    public boolean isNumeric(String str) {
        try {
            // Try to parse the string as a double
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            // If an exception is thrown, the string is not a number
            return false;
        }
    }

    private void playCelebrationAnimation(Label label) {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(1), label);
        scaleTransition.setFromX(1.0);
        scaleTransition.setFromY(1.0);
        scaleTransition.setToX(1.5);
        scaleTransition.setToY(1.5);
        scaleTransition.setCycleCount(2);
        scaleTransition.setAutoReverse(true);
        scaleTransition.play();
    }
}