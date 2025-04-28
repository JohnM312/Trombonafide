package com.trombonafide;

import java.io.IOException;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
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

/**
 * Controller for the login screen.
 * <p>
 * Handles user login, navigation to the signup screen,
 * and sets up the background visuals.
 * </p>
 */
public class PrimaryController {

    /**
     * Field for the user to enter their username.
     */
    @FXML
    private TextField usernameField;

    /**
     * Field for the user to enter their password.
     */
    @FXML
    private PasswordField passwordField;

    /**
     * The main container for the login screen layout.
     */
    @FXML
    private AnchorPane anchorPane;

    /**
     * Label for showing login errors (like invalid username/password).
     */
    @FXML
    private Label errorLabel;

    /**
     * Facade for handling user authentication and system operations.
     */
    private final MusicSystemFacade musicSystem = MusicSystemFacade.getFacadeInstance();

    /**
     * Tries to log the user in based on the entered username and password.
     * <p>
     * If login succeeds, it navigates to the home screen. Otherwise, it shows an error.
     * </p>
     *
     * @throws IOException if navigation to the home screen fails.
     */
    @FXML
    private void handleLogin() throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (musicSystem.login(username, password)) {
            App.setRoot("home");
        } else {
            errorLabel.setText("Invalid username or password.");
        }
    }

    /**
     * Takes the user to the signup screen.
     *
     * @throws IOException if navigation to the signup screen fails.
     */
    @FXML
    private void handleSignup() throws IOException {
        App.setRoot("signup");
    }

    /**
     * Initializes the login screen.
     * <p>
     * Sets up the background image, applies a dark overlay,
     * and loads the external CSS stylesheet for styling.
     * </p>
     */
    @FXML
    public void initialize() {
        Image backgroundImage = new Image(getClass().getResource("/images/background.jpg").toExternalForm());

        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage background = new BackgroundImage(
            backgroundImage, 
            BackgroundRepeat.NO_REPEAT, 
            BackgroundRepeat.NO_REPEAT, 
            BackgroundPosition.CENTER, 
            backgroundSize
        );
        anchorPane.setBackground(new Background(background));

        Rectangle overlay = new Rectangle();
        overlay.setFill(new Color(0, 0, 0, 0.5));
        overlay.widthProperty().bind(anchorPane.widthProperty());
        overlay.heightProperty().bind(anchorPane.heightProperty());
        anchorPane.getChildren().add(0, overlay);

        Platform.runLater(() -> {
            anchorPane.getScene().getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        });
    }
}
