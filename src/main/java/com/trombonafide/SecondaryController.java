package com.trombonafide;

import java.io.IOException;

import javafx.fxml.FXML;
/**
 * this class handles user interaction for the secondary view 
 * in the trombonafide JavaFx application. it provides functionality to swtich from
 * the secondary scene back to the primary scene
 * 
 * this class is tied to the secondary.fxml file and uses JavaFX's FXML annotation
 * to bind the method to a UI control
 * 
 * @author Andrew Lim
 */
public class SecondaryController {
    /**
     * Handles the UI action to switch from the secondary view to the primary view
     * 
     * 
     * @throws IOException if the primary FXML file cannot be loaded
     */
    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}