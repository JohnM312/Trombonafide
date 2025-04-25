package com.trombonafide;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class SecondaryController {

    @FXML
    private Label welcomeLabel;

    private final MusicSystemFacade musicSystem = MusicSystemFacade.getFacadeInstance();

    @FXML
    public void initialize() {
        if (musicSystem.getCurrentUser() != null) {
            welcomeLabel.setText("Welcome, " + musicSystem.getCurrentUser().getFirstName() + "!");
        }
    }

    @FXML
    private void switchToPrimary() throws IOException {
        musicSystem.logout(); // optional: log out the user
        App.setRoot("primary");
    }
}
