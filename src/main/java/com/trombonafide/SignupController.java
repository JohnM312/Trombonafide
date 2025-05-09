package com.trombonafide;

import java.io.IOException;
import java.util.Collections;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Label;

import com.model.User;
import com.trombonafide.util.DataWriter;

/**
 * Controller class for handling user sign-up functionality.
 * Allows users to create new accounts, validates input,
 * registers the user into the system, and persists user data into JSON.
 * 
 * This class interacts with {@link MusicSystemFacade} and {@link DataWriter}.
 * 
 * @author Aiden Campbell
 */
public class SignupController {

    // TextField for the user's first name
    @FXML
    private TextField firstNameField;

    // TextField for the user's last name
    @FXML
    private TextField lastNameField;

    // TextField for the user's desired username
    @FXML
    private TextField usernameField;

    //PasswordField for the user's password
    @FXML
    private PasswordField passwordField;

    // TextField for the user's email address
    @FXML
    private TextField emailField;

    // Label for displaying error messages
    @FXML
    private Label errorLabel;
    // AnchorPane for the background image
    @FXML
    private AnchorPane anchorPane;

    /** Singleton facade for interacting with the music system */
    private final MusicSystemFacade musicSystem = MusicSystemFacade.getFacadeInstance();

    /**
     * Handles the creation of a new user account.
     * Validates all input fields, registers the user,
     * and saves the new user into the JSON file.
     */
    @FXML
    private void handleCreateAccount() {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String username = usernameField.getText();
        String password = passwordField.getText();
        String email = emailField.getText();

        if (firstName.isEmpty() || lastName.isEmpty() || username.isEmpty() || password.isEmpty() || email.isEmpty()) {
            errorLabel.setText("All fields are required.");
            return;
        }

        boolean success = musicSystem.register(firstName, lastName, username, password, email, "student", "0000000000");

        if (success) {
            User newUser = musicSystem.getCurrentUser(); // Get the newly created user
            DataWriter.saveUsers(Collections.singletonList(newUser)); // Save to JSON

            try {
                App.setRoot("primary");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            errorLabel.setText("Username already exists.");
        }
    }

    /**
     * Handles the event when the user chooses to return to the login screen.
     * 
     * @throws IOException if the primary FXML cannot be loaded
     */
    @FXML
    private void handleBackToLogin() throws IOException {
        App.setRoot("primary");
    }
    /**
     * initialize the singup scene by setting the backgroun image
     */
    @FXML
    public void initialize() {
        Image backgroundImage = new Image(getClass().getResource("/images/signup-background.png").toExternalForm());

        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, false, true);
        BackgroundImage background = new BackgroundImage(backgroundImage, 
                                            BackgroundRepeat.NO_REPEAT, 
                                            BackgroundRepeat.NO_REPEAT, 
                                            BackgroundPosition.CENTER, 
                                            backgroundSize);
        anchorPane.setBackground(new Background(background));

        Rectangle overlay = new Rectangle();
        overlay.setFill(new Color(0, 0, 0, 0.65));
        overlay.widthProperty().bind(anchorPane.widthProperty());
        overlay.heightProperty().bind(anchorPane.heightProperty());
        anchorPane.getChildren().add(0, overlay);

        Platform.runLater(() -> {
            anchorPane.getScene().getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        });
    }
}
