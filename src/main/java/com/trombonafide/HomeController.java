package com.trombonafide;

import java.io.IOException;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 * Controller for the Home screen.
 * <p>
 * Sets up the background, applies a fade-in effect, and provides navigation to different parts of the app.
 * </p>
 */
public class HomeController {

    /**
     * The main container for the home screen layout.
     */
    @FXML
    private AnchorPane anchorPane;

    /**
     * Navigates to the Library screen.
     *
     * @param event The button click event.
     * @throws IOException if the Library screen cannot be loaded.
     */
    @FXML
    private void goToLibrary(ActionEvent event) throws IOException {
        App.setRoot("library");
    }

    /**
     * Navigates to the Lessons screen.
     *
     * @param event The button click event.
     * @throws IOException if the Lessons screen cannot be loaded.
     */
    @FXML
    private void goToLessons(ActionEvent event) throws IOException {
        App.setRoot("lessons");
    }

    /**
     * Navigates to the Profiles screen.
     *
     * @param event The button click event.
     * @throws IOException if the Profiles screen cannot be loaded.
     */
    @FXML
    private void goToProfiles(ActionEvent event) throws IOException {
        App.setRoot("profiles");
    }

    /**
     * Navigates to the Primary screen.
     *
     * @param event The button click event.
     * @throws IOException if the Primary screen cannot be loaded.
     */
    @FXML
    private void goToPrimary(ActionEvent event) throws IOException {
        App.setRoot("primary");
    }

    /**
     * Navigates to the Profile View screen.
     *
     * @param event The button click event.
     * @throws IOException if the Profile View screen cannot be loaded.
     */
    @FXML
    private void goToProfileView(ActionEvent event) throws IOException {
        App.setRoot("profileView");
    }

    /**
     * Initializes the home screen.
     * <p>
     * Sets a background image, applies a fade-in animation,
     * adds a dim overlay, and loads the external CSS stylesheet.
     * </p>
     */
    @FXML
    public void initialize() {
        Image backgroundImage = new Image(getClass().getResource("/images/home.png").toExternalForm());

        BackgroundSize backgroundSize = new BackgroundSize(
            1.0, 1.0,
            true, true,
            false, false
        );

        BackgroundImage background = new BackgroundImage(
            backgroundImage,
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.CENTER,
            backgroundSize
        );

        anchorPane.setBackground(new Background(background));

        FadeTransition fadeIn = new FadeTransition(Duration.millis(1000), anchorPane);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();
        
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
