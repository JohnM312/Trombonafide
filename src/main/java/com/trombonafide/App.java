package com.trombonafide;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * this class serves as the main entry point for the Trombonafide JavaFX application.
 * It is responsible for initializing the GUI, loading FXML views, and managing scene transitions.
 * 
 * extends {@link javafx.application.Application}
 * 
 * @author Andrew Lim
 */
public class App extends Application {
    /**
     * The main JavaFX scene used throughout the application.
     */
    private static Scene scene;
    /**
     * Starts the JavaFX application and loads the initial (primary) view.
     * 
     * @param stage the primary stage for this application
     * @throws IOException if the FXML file for the primary view cannot be loaded
     */
    @Override
    public void start(Stage stage) throws IOException {
        System.out.println(App.class.getResource("primary.fxml"));

        scene = new Scene(loadFXML("primary"), 640, 480);
        stage.setScene(scene);
        stage.show();
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

}