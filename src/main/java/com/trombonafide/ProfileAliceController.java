package com.trombonafide;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Controller for Alice's profile page.
 * <p>
 * Loads Alice's profile image and handles going back to the profiles list.
 * </p>
 */
public class ProfileAliceController implements Initializable {

    /**
     * Displays Alice's profile.
     */
    @FXML 
    private ImageView avatarView;

    /**
     * Sets up Alice's profile screen.
     * <p>
     * Loads her avatar image from the resources folder and shows it.
     * </p>
     *
     * @param location  Location used to resolve relative paths.
     * @param resources Resources for localization.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Load Alice’s image from the classpath images folder
        Image img = new Image(getClass().getResourceAsStream("/images/user456.png"));
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
