package com.trombonafide;
    
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.io.IOException;
    
public class HomeController {
    
    @FXML
    private void goToLibrary(ActionEvent event) throws IOException {
        App.setRoot("library"); // Navigate to library.fxml
    }
    
    @FXML
     private void goToLessons(ActionEvent event) throws IOException {
        App.setRoot("lessons"); // Navigate to lessons.fxml
    }
    
    @FXML
    private void goToProfiles(ActionEvent event) throws IOException {
        App.setRoot("profiles"); // Navigate to profiles.fxml
    }
}