package com.trombonafide;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Controller for Noah's profile page.
 * <p>
 * Loads Noah's picture and handles going back to the profiles list.
 * </p>
 */
public class ProfileNoahController implements Initializable {

    /**
     * Displays Noah's picture.
     */
    @FXML 
    private ImageView avatarView;

    /**
     * Sets up Noah's profile screen.
     * <p>
     * Loads his avatar image from the resources and displays it.
     * </p>
     *
     * @param location  Location used to resolve relative paths.
     * @param resources Resources for localization.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Load Noah’s image from the resources/images folder
        Image img = new Image(getClass().getResourceAsStream("/images/user123.png"));
        avatarView.setImage(img);
    }

    /**
     * Takes the user back to the profiles list.
     *
     * @throws IOException if the profiles screen can't be loaded.
     */
    @FXML
    private void handleBackToProfiles() throws IOException {
        App.setRoot("profiles");
    }
}
