package com.trombonafide;

import java.io.IOException;

import javafx.fxml.FXML;

public class ProfileClaireController {

    @FXML
    private void handleBackToProfiles() throws IOException {
        App.setRoot("profiles");
    }

}
