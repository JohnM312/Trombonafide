package com.trombonafide;

import java.io.IOException;
import java.util.UUID;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.Label;
import com.model.User;
import com.model.UserProgress;
import com.model.Lesson;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ProgressController {

    @FXML
    private Label pointsLabel;

    @FXML
    private ListView<String> completedLessonsList;

    private final MusicSystemFacade musicSystem = MusicSystemFacade.getFacadeInstance();

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

    @FXML
    private void handleBack() throws IOException {
        App.setRoot("home");
    }
}
