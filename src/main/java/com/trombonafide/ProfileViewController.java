package com.trombonafide;

import java.io.IOException;
import com.model.User;
import com.trombonafide.MusicSystemFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ProfileViewController {
    @FXML private Label usernameLabel;
    @FXML private Label nameLabel;
    @FXML private Label emailLabel;
    @FXML private Label pointsLabel;
    @FXML private Label lessonsLabel;

    @FXML
    private void goBack(ActionEvent event) throws IOException {
        App.setRoot("home");
    }

    @FXML
    public void initialize() {
        User currentUser = MusicSystemFacade.getFacadeInstance().getCurrentUser();
        
        if (currentUser != null) {
            usernameLabel.setText("Username: " + currentUser.getUsername());
            nameLabel.setText("Name: " + currentUser.getFirstName() + " " + currentUser.getLastName());
            emailLabel.setText("Email: " + currentUser.getEmail());
            pointsLabel.setText("Points: " + currentUser.getProgress().getTotalPoints());
            lessonsLabel.setText("Lessons Completed: " + currentUser.getProgress().getCompletedLessons().size());
        }
    }
}