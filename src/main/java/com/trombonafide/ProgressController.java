package com.trombonafide;

import java.io.IOException;
import java.util.UUID;

import com.model.Lesson;
import com.model.User;
import com.model.UserProgress;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

/**
 * Controller for the Progress screen.
 * <p>
 * Shows the current user's total points and a list of completed lessons.
 * Also provides a way to return to the home screen.
 * </p>
 */
public class ProgressController {

    /**
     * Label that displays the total points earned by the user.
     */
    @FXML
    private Label pointsLabel;

    /**
     * ListView showing the titles of lessons the user has completed.
     */
    @FXML
    private ListView<String> completedLessonsList;

    /**
     * Facade used to access user and lesson data.
     */
    private final MusicSystemFacade musicSystem = MusicSystemFacade.getFacadeInstance();

    /**
     * Initializes the Progress screen.
     * <p>
     * Loads the current user's points and their completed lessons
     * and displays them on the screen.
     * </p>
     */
    @FXML
    public void initialize() {
        User currentUser = musicSystem.getCurrentUser();
        if (currentUser != null) {
            UserProgress progress = currentUser.getProgress();
            pointsLabel.setText("Total Points: " + progress.getTotalPoints());

            ObservableList<String> completedTitles = FXCollections.observableArrayList();
            for (UUID lessonId : progress.getCompletedLessons()) {
                Lesson lesson = musicSystem.getLessonById(lessonId);
                if (lesson != null) {
                    completedTitles.add(lesson.getTitle());
                }
            }
            completedLessonsList.setItems(completedTitles);
        }
    }

    /**
     * Takes the user back to the home screen.
     *
     * @throws IOException if loading the home screen fails.
     */
    @FXML
    private void handleBack() throws IOException {
        App.setRoot("home");
    }
}
