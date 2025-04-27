package com.trombonafide;

import java.io.IOException;
import java.util.Collections;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import com.model.Lesson;
import com.model.User;

/**
 * Controller class for displaying and managing the Lesson Detail view
 * Allows the user to view a lesson's title and details of it
 *  start the lesson to mark it as completed and earn points
 * and return to the list of available lessons.
 *
 * @author Aiden Campbell
 */
public class LessonDetailController {

    // Label displaying the title of the lesson
    @FXML
    private Label lessonTitleLabel;

    // TextArea displaying the content of the lesson
    @FXML
    private TextArea lessonContentArea;

    // The lesson currently being viewed
    private Lesson currentLesson;

    /**
     * Sets the lesson to be displayed in the view.
     *
     * @param lesson the {@link Lesson} to display
     */
    public void setLesson(Lesson lesson) {
        this.currentLesson = lesson;
        lessonTitleLabel.setText(lesson.getTitle());
        lessonContentArea.setText(lesson.getContent());
    }

    /**
     * Handles the "Start Lesson" button click event.
     *
     * Marks the lesson as completed for the current user
     * awards points and saves the updated user progress into JSON.
     */
    @FXML
    private void handleStartLesson() {
        if (currentLesson != null) {
            User currentUser = MusicSystemFacade.getFacadeInstance().getCurrentUser();
            if (currentUser != null) {
                if (!currentUser.getProgress().getCompletedLessons().contains(currentLesson.getUuid())) {
                    currentUser.getProgress().addCompletedLesson(currentLesson.getUuid());
                    currentUser.getProgress().addPoints(10);

                    // Save updated user progress to Users.json
                    com.trombonafide.util.DataWriter.saveUsers(Collections.singletonList(currentUser));

                    System.out.println("Lesson '" + currentLesson.getTitle() + "' completed! +10 points awarded.");
                } else {
                    System.out.println("Lesson '" + currentLesson.getTitle() + "' is already completed.");
                }
            }
        }
    }

    /**
     * Handles the "Back to Lessons" button click
     *
     * Switches the view back to the list of available lessons.
     *
     * @throws IOException if the lessons FXML cannot be loaded
     */
    @FXML
    private void handleBackToLessons() throws IOException {
        App.setRoot("lessons");
    }
}
