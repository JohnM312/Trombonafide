package com.trombonafide;

import com.trombonafide.util.DataLoader;

import java.io.IOException;
import java.io.InputStream;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.Song;
import java.util.List;
import java.util.logging.Level;
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
 * @author Andrew Lim, Aiden Campbell, and Trent Petersen
 */
public class App extends Application {
    private static final Logger LOGGER = Logger.getLogger(App.class.getName());
    
    /**
     * The main JavaFX scene used throughout the application.
     */
    private static Scene scene;
    private static Stage primaryStage;


    /**
     * Starts the JavaFX application and loads the initial (primary) view.
     * 
     * @param stage the primary stage for this application
     * @throws IOException if the FXML file for the primary view cannot be loaded
     */
    @Override
    public void start(Stage stage) throws IOException {
    DataLoader.populateUserList();
    DataLoader.populateLessonList();

    try {
        loadSongsFromJson();
    } catch (Exception e) {
        LOGGER.log(Level.WARNING, "Could not load songs from JSON", e);
    }

    primaryStage = stage; 

    Parent root = loadFXML("primary");

    Image backgroundImage = new Image(getClass().getResource("/images/background.jpg").toExternalForm());

    scene = new Scene(root, backgroundImage.getWidth() * 1.4, backgroundImage.getHeight() * 1.4);

    stage.setScene(scene);
    stage.setTitle("Trombonafide - Login");
    stage.show();
    stage.setResizable(false);

}
    

    /**
     * Loads songs from the Song.json file into the system.
     */
    private void loadSongsFromJson() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        try (InputStream is = getClass().getResourceAsStream("/Song.json")) {
            if (is != null) {
                List<Song> songs = mapper.readValue(is, new TypeReference<List<Song>>() {});
                MusicSystemFacade facade = MusicSystemFacade.getFacadeInstance();
                songs.forEach(facade::addSong);
                LOGGER.info("Loaded " + songs.size() + " songs from JSON");
            } else {
                LOGGER.warning("Song.json file not found in resources");
            }
        }
    }

    /**
     * Replaces the root of the current scene with a new view loaded from the given FXML file.
     * 
     * @param fxml the name of the FXML file (without the .fxml extension)
     * @throws IOException if the FXML file cannot be loaded
     */
    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    /**
     * Loads an FXML file and returns the resulting UI hierarchy as a {@link Parent} node.
     *
     * @param fxml the name of the FXML file (without the .fxml extension)
     * @return the root node loaded from the FXML file
     * @throws IOException if the FXML file cannot be found or loaded
     */
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }

    /**
     * The main method, which launches the JavaFX application.
     * 
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        launch();
    }

    /**
     * Returns the primary stage.
     *
     * @return the primary Stage
     */
    public static Stage getPrimaryStage() {
        return primaryStage;
    }

}