package com.ek.practise2javafxml;

import com.ek.practise2javafxml.components.DniFormController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.text.Normalizer;
import java.util.ArrayList;

public class MainController {
    @FXML
    private DniFormController dniFormComponentController;
    @FXML
    private Button generateDniButton;
    @FXML
    private Button deleteDniButton;
    @FXML
    private Button clearFieldsButton;
    @FXML
    private Text feedbackText;
    @FXML
    private TextField resultField;
    private static final String ALLOWED_CHARS_REGEX = "[^a-zA-ZáéíóúÁÉÍÓÚàèìòùÀÈÌÒÙäëïöüÄËÏÖÜñÑçÇ]";
    private static final String ALPHABET = "abcçdefghijklmnñopqrstuvwxyz";
    private static final String DNI_LETTERS = "TRWAGMYFPDXBNJZSQVHLCKE";

    public DniFormController getDniFormComponentController() {
        return dniFormComponentController;
    }

    public void setDniFormComponentController(DniFormController dniFormComponentController) {
        this.dniFormComponentController = dniFormComponentController;
    }

    public Button getGenerateDniButton() {
        return generateDniButton;
    }

    public void setGenerateDniButton(Button generateDniButton) {
        this.generateDniButton = generateDniButton;
    }

    public Button getDeleteDniButton() {
        return deleteDniButton;
    }

    public void setDeleteDniButton(Button deleteDniButton) {
        this.deleteDniButton = deleteDniButton;
    }

    public Button getClearFieldsButton() {
        return clearFieldsButton;
    }

    public void setClearFieldsButton(Button clearFieldsButton) {
        this.clearFieldsButton = clearFieldsButton;
    }

    public Text getFeedbackText() {
        return feedbackText;
    }

    public void setFeedbackText(Text feedbackText) {
        this.feedbackText = feedbackText;
    }

    public TextField getResultField() {
        return resultField;
    }

    public void setResultField(TextField resultField) {
        this.resultField = resultField;
    }

    private void clearFeedbackText() {
        getFeedbackText().setText("");
    }

    private String getMultipliedComputedNumber(String chars, int multiplier) {
        ArrayList<Integer> numbers = new ArrayList<>();

        for (String currentChar : chars.split("")) {
            numbers.add(ALPHABET.indexOf(currentChar) % 10);
        }

        StringBuilder numberAsText = new StringBuilder();
        for (int number : numbers) {
            numberAsText.append(number);
        }

        int computedNumber = Integer.parseInt(numberAsText.toString());

        String multipliedComputedNumber;
        if (multiplier == 1) {
            multipliedComputedNumber = "0" + computedNumber;
        } else {
            multipliedComputedNumber = String.valueOf(computedNumber * multiplier);
        }
        return multipliedComputedNumber;
    }

    private String getDniLetter(int number) {
        return String.valueOf(DNI_LETTERS.charAt(number % 23));
    }

    public void generateDni() {
        String firstname = getDniFormComponentController().getFirstNameField().getText();
        if (firstname.isEmpty()) {
            getFeedbackText().setText("ERROR: First name field empty");
            return;
        }

        String lastname = getDniFormComponentController().getLastNameField().getText();
        if (lastname.isEmpty()) {
            getFeedbackText().setText("ERROR: Last name field empty");
            return;
        }

        int multiplier;
        try {
            multiplier = Integer.parseInt(getDniFormComponentController().getMultiplierField().getText());
        } catch (Exception e) {
            getFeedbackText().setText("ERROR: Empty or invalid multiplier value");
            return;
        }

        if (multiplier < 1 || multiplier > 9) {
            getFeedbackText().setText("ERROR: Out of range multiplier value");
            return;
        }

        String chars = firstname + lastname;
        chars = Normalizer.normalize(chars.replaceAll(ALLOWED_CHARS_REGEX, ""), Normalizer.Form.NFD).replaceAll("\\p{M}", "").toLowerCase();

        if (chars.length() >= 7) {
            chars = chars.substring(0, 7);
        } else {
            getFeedbackText().setText("ERROR: Not enough characters");
            return;
        }

        clearFeedbackText();

        String multipliedComputedNumber = getMultipliedComputedNumber(chars, multiplier);
        String DniResult = multipliedComputedNumber + getDniLetter(Integer.parseInt(multipliedComputedNumber));

        getResultField().setText(DniResult);
    }

    public void deleteDni() {
        getResultField().clear();
        clearFeedbackText();
    }

    public void clearFields() {
        getDniFormComponentController().clearInputFields();
        clearFeedbackText();
    }
}
