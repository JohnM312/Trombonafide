package com.trombonafide;

import java.io.IOException;

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

public class PrimaryController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Label errorLabel;

    private final MusicSystemFacade musicSystem = MusicSystemFacade.getFacadeInstance();

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

    @FXML
    private void handleSignup() throws IOException {
        App.setRoot("signup");
    }
    @FXML
    public void initialize() {
        Image backgroundImage = new Image(getClass().getResource("/images/background.jpg").toExternalForm());

        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage background = new BackgroundImage(backgroundImage, 
                                            BackgroundRepeat.NO_REPEAT, 
                                            BackgroundRepeat.NO_REPEAT, 
                                            BackgroundPosition.CENTER, 
                                            backgroundSize);
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
