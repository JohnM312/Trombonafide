package com.trombonafide;

import java.io.IOException;

import javafx.scene.image.ImageView;
import com.model.User;
import com.trombonafide.MusicSystemFacade;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;

public class ProfileViewController {
    @FXML private Label usernameLabel;
    @FXML private Label nameLabel;
    @FXML private Label emailLabel;
    @FXML private Label pointsLabel;
    @FXML private Label lessonsLabel;
    @FXML private AnchorPane anchorPane;
    @FXML private ImageView profileImageView;

    @FXML
    private void goBack() throws IOException {
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

        // Set background image
        Image backgroundImage = new Image(getClass().getResource("/images/profileicon.jpg").toExternalForm());
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, false, true);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        anchorPane.setBackground(new Background(background));

        // Dim background with overlay
        Rectangle overlay = new Rectangle();
        overlay.setFill(new Color(0, 0, 0, 0.4));
        overlay.widthProperty().bind(anchorPane.widthProperty());
        overlay.heightProperty().bind(anchorPane.heightProperty());
        anchorPane.getChildren().add(0, overlay);

        // Set the profile picture
        Image profilePic = new Image(getClass().getResource("/images/Default_pfp.jpg").toExternalForm());
        profileImageView.setImage(profilePic);

        // Clip the profile picture to a circle
        double radius = Math.min(profileImageView.getFitWidth(), profileImageView.getFitHeight()) / 2;
        Circle clip = new Circle(radius, radius, radius);
        profileImageView.setClip(clip);

        Platform.runLater(() -> {
            anchorPane.getScene().getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        });
    }
}