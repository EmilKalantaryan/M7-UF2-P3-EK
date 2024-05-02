package com.ek.practise2javafxml.components;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class DniFormController {
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField multiplierField;

    public TextField getFirstNameField() {
        return firstNameField;
    }

    public void setFirstNameField(TextField firstNameField) {
        this.firstNameField = firstNameField;
    }

    public TextField getLastNameField() {
        return lastNameField;
    }

    public void setLastNameField(TextField lastNameField) {
        this.lastNameField = lastNameField;
    }

    public TextField getMultiplierField() {
        return multiplierField;
    }

    public void setMultiplierField(TextField multiplierField) {
        this.multiplierField = multiplierField;
    }

    public void clearInputFields() {
        getFirstNameField().clear();
        getLastNameField().clear();
        getMultiplierField().clear();
    }
}
