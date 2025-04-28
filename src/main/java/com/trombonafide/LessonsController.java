package com.trombonafide;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import com.model.Lesson;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


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

    @FXML
    private AnchorPane anchorPane;

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

        
        Image backgroundImage = new Image(getClass().getResource("/images/lessons.jpg").toExternalForm());

        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, false, true);
        BackgroundImage background = new BackgroundImage(backgroundImage, 
                                            BackgroundRepeat.NO_REPEAT, 
                                            BackgroundRepeat.NO_REPEAT, 
                                            BackgroundPosition.CENTER, 
                                            backgroundSize);
        anchorPane.setBackground(new Background(background));

        Rectangle overlay = new Rectangle();
        overlay.setFill(new Color(0, 0, 0, 0.5));
        overlay.widthProperty().bind(anchorPane.widthProperty());
        overlay.heightProperty().bind(anchorPane.heightProperty());
        anchorPane.getChildren().add(0, overlay);

        Platform.runLater(() -> {
            anchorPane.getScene().getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        });
    
    }
}
