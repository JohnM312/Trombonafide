package com.trombonafide;

import com.trombonafide.util.DataLoader;

import java.io.IOException;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * This class serves as the main entry point for the Trombonafide JavaFX application.
 * It is responsible for initializing the GUI, loading FXML views, and managing scene transitions.
 * 
 * Extends {@link javafx.application.Application}
 * 
 */
public class App extends Application {
    private static final Logger LOGGER = Logger.getLogger(App.class.getName());

    private static Scene scene;
    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException {
        DataLoader.populateUserList();
        DataLoader.populateLessonList();
        DataLoader.populateSongList();

        primaryStage = stage;

        Parent root = loadFXML("primary");

        Image backgroundImage = new Image(getClass().getResource("/images/background.jpg").toExternalForm());
        scene = new Scene(root, backgroundImage.getWidth() * 1.4, backgroundImage.getHeight() * 1.4);

        stage.setScene(scene);
        stage.setTitle("Trombonafide - Login");
        stage.show();
        stage.setResizable(false);
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch();
    }
}
