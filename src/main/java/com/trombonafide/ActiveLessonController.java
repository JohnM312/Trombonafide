package com.trombonafide;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.io.IOException;
import com.model.Lesson; // Import your Lesson model
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

public class ActiveLessonController {
    
    private static Lesson currentLesson;

    // Add FXML elements if you want to dynamically change elements based on the lesson
    @FXML
    private Label questionLabel;

    @FXML
    private ImageView musicNotationImage;

    // Setter method to receive the selected lesson
    public void setLesson(Lesson lesson) {
        ActiveLessonController.currentLesson = lesson;
        // Now you can use 'lesson' to populate your UI elements
        System.out.println("Lesson received: " + lesson.getTitle());
    }

    // STATIC METHOD THAT YOU CAN USE FOR UPDATING INFORMATION
    @FXML
    public void initialize() {
        if (currentLesson != null){
            questionLabel.setText(currentLesson.getContent());
        }
    }

    @FXML
    private void handleAnswerA(ActionEvent event) {
        // Handle answer A logic (e.g., check if correct)
        System.out.println("Answer A selected");
    }

    @FXML
    private void handleAnswerB(ActionEvent event) {
        // Handle answer B logic
        System.out.println("Answer B selected");
    }

    @FXML
    private void handleAnswerC(ActionEvent event) {
        // Handle answer C logic
        System.out.println("Answer C selected");
    }

    @FXML
    private void handleAnswerD(ActionEvent event) {
        // Handle answer D logic
        System.out.println("Answer D selected");
    }

    @FXML
    private void goBack(ActionEvent event) throws IOException {
        App.setRoot("lessons"); // Go back to lesson list
    }

     @FXML
    private void goToHome(ActionEvent event) throws IOException {
        App.setRoot("home"); // Return to home
    }
}