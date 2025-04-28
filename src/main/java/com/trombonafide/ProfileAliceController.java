package com.trombonafide;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ProfileAliceController implements Initializable {
    @FXML private ImageView avatarView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Load Alice’s image from the classpath images folder
        Image img = new Image(getClass().getResourceAsStream("/images/user456.png"));
        avatarView.setImage(img);
    }

    @FXML
    private void handleBackToProfiles() throws IOException {
        App.setRoot("profiles");
    }
}
