package com.trombonafide;

import java.io.IOException;

import javafx.fxml.FXML;

public class ProfileAliceController {

    @FXML
    private void handleBackToProfiles() throws IOException {
        App.setRoot("profiles");
    }

}
