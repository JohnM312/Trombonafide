package com.trombonafide;

import com.model.Song;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.Comparator;
import java.io.IOException;

/**
 * Controller for the Song Library view that displays available songs and handles playback.
 * @author Trent Petersen
 */
public class LibraryController {
    /** TableView displaying the list of available songs */
    @FXML private TableView<Song> songsTable;
    
    /** Column displaying song titles */
    @FXML private TableColumn<Song, String> titleColumn;
    
    /** Column displaying artist names (first + last name) */ 
    @FXML private TableColumn<Song, String> artistColumn;
    
    /** Column displaying song genres */
    @FXML private TableColumn<Song, String> genreColumn;
    
    /** Column displaying difficulty ratings */
    @FXML private TableColumn<Song, Integer> difficultyColumn;
    
    private final MusicSystemFacade musicSystem = MusicSystemFacade.getFacadeInstance();
    
    /** 
     * Initializes the controller after FXML loading completes.
     * Configures table columns, loads songs, and sets up sorting.
     */
    @FXML
    public void initialize() {
        /** Configure table columns */
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        artistColumn.setCellValueFactory(cellData -> 
            new SimpleStringProperty(
                cellData.getValue().getArtist().getFirstName() + " " + 
                cellData.getValue().getArtist().getLastName()
            ));
        genreColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));
        difficultyColumn.setCellValueFactory(new PropertyValueFactory<>("difficultyRate"));
        
        /** Load songs */
        songsTable.setItems(FXCollections.observableArrayList(musicSystem.getAllSongs()));
        
        /** Enable sorting with proper listener */
        songsTable.getSortOrder().addListener((ListChangeListener<TableColumn<Song, ?>>) change -> {
            while (change.next()) {
                if (change.wasAdded() || change.wasRemoved() || change.wasPermutated()) {
                    sortTable();
                }
            }
        });
    }
    
    /**
     * Sorts the songs table based on the currently selected column.
     */
    private void sortTable() {
        if (!songsTable.getSortOrder().isEmpty()) {
            TableColumn<Song, ?> sortColumn = songsTable.getSortOrder().get(0);
            Comparator<Song> comparator = createComparator(sortColumn);
            FXCollections.sort(songsTable.getItems(), comparator);
        }
    }
    
    /**
     * Creates a comparator for sorting based on the specified column.
     * @param column The table column to sort by
     * @return Comparator for the specified column type
     */
    private Comparator<Song> createComparator(TableColumn<Song, ?> column) {
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
     * Handles navigation back to the previous view.
     * Stops any currently playing song before transitioning.
     * @throws IOException if the FXML file cannot be loaded
     */
    @FXML
    private void handleBack() throws IOException {
        musicSystem.stopSong();
        App.setRoot("secondary");
    }
    
    /**
     * Handles playback of the selected song.
     * Starts playback in a background thread to avoid UI freezing.
     */
    @FXML
    private void handlePlaySelected() {
        Song selectedSong = songsTable.getSelectionModel().getSelectedItem();
        if (selectedSong != null) {
            new Thread(() -> musicSystem.playSong(selectedSong.getTitle())).start();
        }
    }

    @FXML
    private void handleStop() {
        musicSystem.stopSong();
    }

}