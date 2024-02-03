package com.example.guessinggame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Random;

public class GuessingGameController {

    @FXML
    private TextField inputStartRange;
    @FXML
    private TextField inputEndRange;
    @FXML
    private TextField guessedNumberField;
    @FXML
    private VBox resultField;
    @FXML
    private HBox generationHBox;
    private TextField currentTextField;
    private int generatedNumber;
    private int attempts;

    @FXML
    private void initialize() {
        currentTextField = inputStartRange;
        attempts = 0;
    }

    public void onGenerateButtonClick(ActionEvent actionEvent) {
        String startRangeStr = inputStartRange.getText(), endRangeStr = inputEndRange.getText();
        generationHBox.getChildren().clear();
        if (isNumeric(startRangeStr) && isNumeric(endRangeStr)) {
            int start = Integer.parseInt(startRangeStr), end = Integer.parseInt(endRangeStr);
            if (start > end) showAlert("Start range must be smaller or equal to the end range!");
            else {
                Random random = new Random();
                generatedNumber = random.nextInt(end - start + 1) + start;
                System.out.println(generatedNumber);
                Label label = new Label("Number generated successfully");
                label.setId("generationLabel");
                generationHBox.getChildren().addAll(label);
            }
        } else {
            //alert
            showAlert("Please enter an integer value for the start and end range!");
        }
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
        if (isNumeric(guessedStr)) {
            int guessedNumber = Integer.parseInt(guessedStr);
            attempts++;
            if (guessedNumber == generatedNumber) {
                System.out.println(attempts);
                System.out.println("Congrats");
            } else {
                //handle close or far here

            }
        } else {
            //alert
            showAlert("Please enter an integer value for the guessed number!");
        }
    }

    public void onNumberInputTyped(KeyEvent keyEvent) {
        System.out.println("hi");
        if (currentTextField == null) {
            System.out.println("null");
            return;
        }

        String input = currentTextField.getText();
        int indexPos = currentTextField.getCaretPosition() - 1;

        System.out.println(indexPos);
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
            System.out.println("mouse clicked");
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
}