package com.trombonafide;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

public class ProfilesController implements Initializable {
    @FXML private VBox userListVBox;
    @FXML private TextField searchField;

    // Data for each profile
    private final String[] usernames =   { "@Claire", "@Noah",   "@Alice" };
    private final String[] levels =      { "Beginner", "Advanced", "Intermediate" };
    private final String[] avatarResources = {
        "/images/user4567.png",
        "/images/user123.png",
        "/images/user456.png"
    };

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Build one card per user
        for (int i = 0; i < usernames.length; i++) {
            HBox card = createUserCard(
                usernames[i],
                levels[i],
                avatarResources[i]
            );
            userListVBox.getChildren().add(card);
        }
    }

    private HBox createUserCard(String username, String level, String avatarResource) {
        // Container HBox
        HBox row = new HBox(15);
        row.setAlignment(Pos.CENTER_LEFT);
        row.setPrefHeight(100);
        row.setStyle("-fx-background-color: #333333; -fx-background-radius: 10; -fx-padding: 20;");

        // Avatar pane
        StackPane avatarContainer = new StackPane();
        avatarContainer.setPrefSize(60, 60);
        avatarContainer.setStyle("-fx-background-color: white; -fx-background-radius: 10;");

        ImageView avatar = new ImageView();
        avatar.setPreserveRatio(true);
        avatar.setFitWidth(60);
        avatar.setFitHeight(60);
        // Load from classpath
        URL imgUrl = getClass().getResource(avatarResource);
        if (imgUrl != null) {
            avatar.setImage(new Image(imgUrl.toExternalForm()));
        }
        // Rounded corners
        Rectangle clip = new Rectangle(60, 60);
        clip.setArcWidth(10);
        clip.setArcHeight(10);
        avatar.setClip(clip);
        avatarContainer.getChildren().add(avatar);

        // Username label
        Label usernameLabel = new Label(username);
        usernameLabel.setStyle("-fx-text-fill: yellow; -fx-font-size: 20px; -fx-font-weight: bold;");

        // Level badge
        Button levelButton = new Button(level);
        levelButton.setStyle(
            "-fx-background-color: yellow;" +
            "-fx-text-fill: black;" +
            "-fx-background-radius: 20;" +
            "-fx-font-weight: bold;"
        );

        // Spacer to push the View Profile button right
        Pane spacer = new Pane();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        // View Profile button
        Button viewProfileButton = new Button("View Profile");
        viewProfileButton.setStyle(
            "-fx-background-color: yellow;" +
            "-fx-text-fill: black;" +
            "-fx-background-radius: 20;" +
            "-fx-font-weight: bold;"
        );
        viewProfileButton.setOnAction(evt -> {
            try {
                // Turn "@Claire" → "claire", "@Noah" → "noah", etc.
                String key = username.substring(1).toLowerCase();
                // Load fxml/profile_claire.fxml, etc.
                App.setRoot("profile_" + key);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        row.getChildren().addAll(
            avatarContainer,
            usernameLabel,
            levelButton,
            spacer,
            viewProfileButton
        );
        return row;
    }

    @FXML
    private void handleBackToDashboard() throws IOException {
        App.setRoot("home");
    }
}
