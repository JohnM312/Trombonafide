package com.trombonafide;

import com.model.Song;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.AnchorPane;


import java.util.Comparator;
import java.io.IOException;

/**
 * Controls the Song Library screen where users can view, sort, and pick songs to play.
 */
public class LibraryController {

    @FXML private TableView<Song> songsTable;
    @FXML private TableColumn<Song, String> titleColumn;
    @FXML private TableColumn<Song, String> artistColumn;
    @FXML private TableColumn<Song, String> genreColumn;
    @FXML private TableColumn<Song, Integer> difficultyColumn;
    @FXML private AnchorPane anchorPane;

    private final MusicSystemFacade musicSystem = MusicSystemFacade.getFacadeInstance();

    /**
     * Sets up the table when the screen loads.
     */
    @FXML
    public void initialize() {
        setupColumns();
        loadSongs();
        setupSortListener();

        Image backgroundImage = new Image(getClass().getResource("/images/library.jpg").toExternalForm());

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

    /**
     * Connects the table columns to song info.
     */
    private void setupColumns() {
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        artistColumn.setCellValueFactory(cellData -> 
            new SimpleStringProperty(
                cellData.getValue().getArtist().getFirstName() + " " +
                cellData.getValue().getArtist().getLastName()
            ));
        genreColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));
        difficultyColumn.setCellValueFactory(new PropertyValueFactory<>("difficultyRate"));
    }

    /**
     * Loads all the songs into the table.
     */
    private void loadSongs() {
        songsTable.setItems(FXCollections.observableArrayList(musicSystem.getAllSongs()));
    }

    /**
     * Sets up sorting when a column is clicked.
     */
    private void setupSortListener() {
        songsTable.getSortOrder().addListener((ListChangeListener<TableColumn<Song, ?>>) change -> {
            while (change.next()) {
                if (change.wasAdded() || change.wasRemoved() || change.wasPermutated()) {
                    sortSongs();
                }
            }
        });
    }

    /**
     * Sorts the songs based on which column is selected.
     */
    private void sortSongs() {
        if (!songsTable.getSortOrder().isEmpty()) {
            TableColumn<Song, ?> sortBy = songsTable.getSortOrder().get(0);
            Comparator<Song> songSorter = getSongSorter(sortBy);
            FXCollections.sort(songsTable.getItems(), songSorter);
        }
    }

    /**
     * Picks how to sort based on the column.
     */
    private Comparator<Song> getSongSorter(TableColumn<Song, ?> column) {
        return (song1, song2) -> {
            if (column == titleColumn) {
                return song1.getTitle().compareToIgnoreCase(song2.getTitle());
            } else if (column == artistColumn) {
                String artist1 = song1.getArtist().getFirstName() + " " + song1.getArtist().getLastName();
                String artist2 = song2.getArtist().getFirstName() + " " + song2.getArtist().getLastName();
                return artist1.compareToIgnoreCase(artist2);
            } else if (column == genreColumn) {
                return song1.getGenre().compareToIgnoreCase(song2.getGenre());
            } else if (column == difficultyColumn) {
                return Integer.compare(song1.getDifficultyRate(), song2.getDifficultyRate());
            }
            return 0;
        };
    }

    /**
     * Goes back to the main menu screen.
     */
    @FXML
    private void handleBack() throws IOException {
        musicSystem.stopSong();
        App.setRoot("home");
    }

    /**
     * Starts the play screen for the song the user picked.
     */
    @FXML
    private void handlePlaySelected() {
        Song selectedSong = songsTable.getSelectionModel().getSelectedItem();
        if (selectedSong != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/play.fxml"));
                Parent root = loader.load();
                PlayController controller = loader.getController();
                controller.setSong(selectedSong);
                controller.startPlaying();

                App.getPrimaryStage().getScene().setRoot(root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Stops any song that's playing.
     */
    @FXML
    private void handleStop() {
        musicSystem.stopSong();
    }
}
