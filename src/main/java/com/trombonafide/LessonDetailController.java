package com.trombonafide;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import com.model.Lesson;

public class LessonDetailController {

    @FXML
    private Label lessonTitleLabel;

    @FXML
    private TextArea lessonContentArea;

    private Lesson currentLesson;

    public void setLesson(Lesson lesson) {
        this.currentLesson = lesson;
        lessonTitleLabel.setText(lesson.getTitle());
        lessonContentArea.setText(lesson.getContent());
    }

    @FXML
    private void handleStartLesson() {
        System.out.println("Starting lesson: " + currentLesson.getTitle());
        // Later: Add actual start lesson functionality
    }

    @FXML
    private void handleBackToLessons() throws IOException {
        App.setRoot("lessons");
    }
}
