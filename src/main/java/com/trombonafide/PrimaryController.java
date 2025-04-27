package com.trombonafide;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Label;

public class PrimaryController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label errorLabel;

    private final MusicSystemFacade musicSystem = MusicSystemFacade.getFacadeInstance();

    @FXML
    private void handleLogin() throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (musicSystem.login(username, password)) {
            App.setRoot("secondary");
        } else {
            errorLabel.setText("Invalid username or password.");
        }
    }

    @FXML
    private void handleSignup() throws IOException {
        App.setRoot("signup");
    }

}
