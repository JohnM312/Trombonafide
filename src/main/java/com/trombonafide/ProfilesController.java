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

/**
 * Controller for the Profiles page.
 * <p>
 * Displays a scrollable list of user profiles, each with a picture, username, level,
 * and a button to view more details. Also handles navigation back to the dashboard.
 * </p>
 */
public class ProfilesController implements Initializable {

    /**
     * VBox that holds all the dynamically generated user profile cards.
     */
    @FXML 
    private VBox userListVBox;

    /**
     * Text field for searching users by name or level.
     */
    @FXML 
    private TextField searchField;

    private final String[] usernames = { "@Claire", "@Noah", "@Alice" };
    private final String[] levels = { "Beginner", "Advanced", "Intermediate" };
    private final String[] avatarResources = {
        "/images/user4567.png",
        "/images/user123.png",
        "/images/user456.png"
    };

    /**
     * Initializes the Profiles screen.
     * <p>
     * Creates a card for each user and adds it to the list view.
     * </p>
     *
     * @param location  Location to resolve relative paths.
     * @param resources Resources for localization.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (int i = 0; i < usernames.length; i++) {
            HBox card = createUserCard(usernames[i], levels[i], avatarResources[i]);
            userListVBox.getChildren().add(card);
        }
    }

    /**
     * Builds a user profile card with a picture, username, level badge, and view button.
     *
     * @param username       The user's display name.
     * @param level          The user's skill level (Beginner, Intermediate, Advanced).
     * @param avatarResource The path to the avatar image in the resources.
     * @return An HBox representing the full user profile card.
     */
    private HBox createUserCard(String username, String level, String avatarResource) {
        HBox row = new HBox(15);
        row.setAlignment(Pos.CENTER_LEFT);
        row.setPrefHeight(100);
        row.setStyle("-fx-background-color: #333333; -fx-background-radius: 10; -fx-padding: 20;");

        StackPane avatarContainer = new StackPane();
        avatarContainer.setPrefSize(60, 60);
        avatarContainer.setStyle("-fx-background-color: white; -fx-background-radius: 10;");

        ImageView avatar = new ImageView();
        avatar.setPreserveRatio(true);
        avatar.setFitWidth(60);
        avatar.setFitHeight(60);

        URL imgUrl = getClass().getResource(avatarResource);
        if (imgUrl != null) {
            avatar.setImage(new Image(imgUrl.toExternalForm()));
        }

        Rectangle clip = new Rectangle(60, 60);
        clip.setArcWidth(10);
        clip.setArcHeight(10);
        avatar.setClip(clip);
        avatarContainer.getChildren().add(avatar);

        Label usernameLabel = new Label(username);
        usernameLabel.setStyle("-fx-text-fill: yellow; -fx-font-size: 20px; -fx-font-weight: bold;");

        Button levelButton = new Button(level);
        levelButton.setStyle(
            "-fx-background-color: yellow;" +
            "-fx-text-fill: black;" +
            "-fx-background-radius: 20;" +
            "-fx-font-weight: bold;"
        );

        Pane spacer = new Pane();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Button viewProfileButton = new Button("View Profile");
        viewProfileButton.setStyle(
            "-fx-background-color: yellow;" +
            "-fx-text-fill: black;" +
            "-fx-background-radius: 20;" +
            "-fx-font-weight: bold;"
        );
        viewProfileButton.setOnAction(evt -> {
            try {
                String key = username.substring(1).toLowerCase();
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

    /**
     * Navigates the user back to the dashboard (home screen).
     *
     * @throws IOException if loading the home screen fails.
     */
    @FXML
    private void handleBackToDashboard() throws IOException {
        App.setRoot("home");
    }
}
