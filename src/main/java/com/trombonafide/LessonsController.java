package com.trombonafide;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import com.model.Lesson;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class LessonsController {

    @FXML
    private ListView<String> lessonsListView;

    private final MusicSystemFacade musicSystem = MusicSystemFacade.getFacadeInstance();

    @FXML
    private void handleBack() throws IOException {
        App.setRoot("secondary");
    }

    @FXML
    private void initialize() {
        ObservableList<String> lessonTitles = FXCollections.observableArrayList();

        for (Lesson lesson : musicSystem.getAllLessons()) {
            lessonTitles.add(lesson.getTitle());
        }

        lessonsListView.setItems(lessonTitles);

        lessonsListView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) { // Double-click
                String selectedTitle = lessonsListView.getSelectionModel().getSelectedItem();
                Lesson selectedLesson = musicSystem.getLessonByTitle(selectedTitle);

                if (selectedLesson != null) {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/lessonDetails.fxml"));
                        Parent root = loader.load();

                        LessonDetailController controller = loader.getController();
                        controller.setLesson(selectedLesson);

                        
                        App.getPrimaryStage().getScene().setRoot(root);



                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
