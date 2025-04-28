package com.trombonafide;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Controller for Claire's profile page.
 * <p>
 * Loads Claire's picture image and handles going back to the profiles list.
 * </p>
 */
public class ProfileClaireController implements Initializable {

    /**
     * Displays Claire's picture.
     */
    @FXML 
    private ImageView avatarView;

    /**
     * Sets up Claire's profile screen.
     * <p>
     * Loads her avatar image from the resources and displays it.
     * </p>
     *
     * @param location  Location used to resolve relative paths.
     * @param resources Resources for localization.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image img = new Image(getClass().getResourceAsStream("/images/user4567.png"));
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
