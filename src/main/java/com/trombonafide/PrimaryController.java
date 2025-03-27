package com.trombonafide;

import java.io.IOException;

import javafx.fxml.FXML;
/**
 * this class manages the user interaction for the primary view 
 * in the Trombonafide JavaFX application. it allows the switch from primary scene to the secondary scene
 * 
 * @author Andrew Lim
 */
public class PrimaryController {

    /**
     * Handles the UI action to switch from primary view to secondary view 
     * This method is called when the user interacts with the designatied UI control
     * 
     * @throws IOException if the secondary FXML file cannot be loaded
     */
    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
}
