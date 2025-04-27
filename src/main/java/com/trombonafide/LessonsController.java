package com.trombonafide;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import com.model.Lesson;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Controller class for managing the Lessons view.
 * Displays a list of available lessons and handles user interactions such as
 * selecting a lesson or returning to the dashboard.
 *
 * This class uses {@link MusicSystemFacade} to access lesson data and switches
 * views based on user actions.
 *
 * @author Aiden Campbell
 */
public class LessonsController {

    //ListView component for displaying lesson titles
    @FXML
    private ListView<String> lessonsListView;

    // Singleton facade for accessing the music system data
    private final MusicSystemFacade musicSystem = MusicSystemFacade.getFacadeInstance();

    /**
     * Handles the "Back to Dashboard" button click event.
     * Switches the scene back to the dashboard view (secondary.fxml).
     *
     * @throws IOException if the dashboard FXML cannot be loaded
     */
    @FXML
    private void handleBack() throws IOException {
        App.setRoot("home");
    }

    /**
     * Initializes the Lessons view by populating the ListView with lesson titles.
     * Also sets up a double click listener for opening lesson details.
     */
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
