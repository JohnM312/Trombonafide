package com.trombonafide;

import java.io.IOException;
import java.util.Collections;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

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
        
    @FXML 
    private AnchorPane anchorPane;


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
    private void handleStartLesson() throws IOException {
        if (currentLesson != null) {
            User currentUser = MusicSystemFacade.getFacadeInstance().getCurrentUser();
            if (currentUser != null) {
                if (!currentUser.getProgress().getCompletedLessons().contains(currentLesson.getUuid())) {
                    // Mark lesson completed
                    currentUser.getProgress().addCompletedLesson(currentLesson.getUuid());

                    // Add 10 points
                    currentUser.getProgress().addPoints(10);

                    // Save updated user data
                    com.trombonafide.util.DataWriter.saveUsers(Collections.singletonList(currentUser));

                    System.out.println("Lesson '" + currentLesson.getTitle() + "' completed! +10 points awarded.");
                } else {
                    System.out.println("Lesson '" + currentLesson.getTitle() + "' is already completed.");
                }
            }
        }

        // Now switch to the Slide Lesson screen
        App.setRoot("slide_lesson");
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
    @FXML
    public void initialize() {
        Image backgroundImage = new Image(getClass().getResource("/images/desc.jpg").toExternalForm());

        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, false, true);
        BackgroundImage background = new BackgroundImage(backgroundImage, 
                                            BackgroundRepeat.NO_REPEAT, 
                                            BackgroundRepeat.NO_REPEAT, 
                                            BackgroundPosition.CENTER, 
                                            backgroundSize);
        anchorPane.setBackground(new Background(background));

        Rectangle overlay = new Rectangle();
        overlay.setFill(new Color(0, 0, 0, 0.65));
        overlay.widthProperty().bind(anchorPane.widthProperty());
        overlay.heightProperty().bind(anchorPane.heightProperty());
        anchorPane.getChildren().add(0, overlay);

        Platform.runLater(() -> {
            anchorPane.getScene().getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        });
    }
}
