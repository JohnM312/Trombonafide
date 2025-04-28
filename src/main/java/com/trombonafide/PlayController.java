package com.trombonafide;

import com.model.Song;
import com.trombonafide.Note;
import com.trombonafide.util.MusicPlayer;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.animation.AnimationTimer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller for the play screen, where users interact with a song
 * by matching slider movement with falling notes to achieve a score.
 */
public class PlayController {

    @FXML
    private Slider pitchSlider;

    @FXML
    private Label scoreLabel;

    @FXML
    private AnchorPane rootPane;

    private List<NoteCircle> activeNotes = new ArrayList<>();
    private List<Note> songNotes = new ArrayList<>();
    private int currentNoteIndex = 0;
    private int score = 0;
    private AnimationTimer timer;
    private Song currentSong;

    private long lastNoteTime = 0;

    /**
     * Initializes the play screen by setting the pane dimensions
     * and preparing the animation timer without starting it.
     */
    @FXML
    public void initialize() {
        rootPane.setPrefWidth(App.getPrimaryStage().getWidth());
        rootPane.setPrefHeight(App.getPrimaryStage().getHeight());

        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                updateGame(now);
            }
        };
    }

    /**
     * Sets the current song to be played.
     * 
     * @param song The song to be loaded for playback.
     */
    public void setSong(Song song) {
        this.currentSong = song;
        if (song.getNotes() != null) {
            this.songNotes = song.getNotes();
        }
    }

    /**
     * Starts the gameplay sequence, resetting necessary variables.
     */
    public void startPlaying() {
        score = 0;
        scoreLabel.setText("Score: 0");
        currentNoteIndex = 0;
        activeNotes.clear();
        songNotes = currentSong.getNotes();
        timer.start();
    }

    /**
     * Updates the game state during each animation frame.
     * 
     * @param now The current time in nanoseconds.
     */
    private void updateGame(long now) {
        if (currentNoteIndex < songNotes.size()) {
            if (now - lastNoteTime > 1_000_000_000L) {
                spawnNote(songNotes.get(currentNoteIndex));
                currentNoteIndex++;
                lastNoteTime = now;
            }
        }
        moveNotes();
    }

    /**
     * Spawns a new note as a graphical circle on the screen.
     * 
     * @param note The musical note corresponding to the spawned object.
     */
    private void spawnNote(Note note) {
        Circle circle = new Circle(10, Color.YELLOW);
        circle.setCenterX(600);
        circle.setCenterY(Math.random() * 300 + 50);

        rootPane.getChildren().add(circle);
        activeNotes.add(new NoteCircle(circle, note));
    }

    /**
     * Moves all active note circles leftward and checks for interactions
     * when they reach the scoring area.
     */
    private void moveNotes() {
        List<NoteCircle> toRemove = new ArrayList<>();
        for (NoteCircle noteCircle : activeNotes) {
            Circle circle = noteCircle.getCircle();
            circle.setCenterX(circle.getCenterX() - 2);

            if (circle.getCenterX() <= 70) {
                double sliderValue = pitchSlider.getValue();
                double sliderMappedY = mapSliderValueToY(sliderValue);
                double noteY = circle.getCenterY();

                Note note = noteCircle.getNote();
                if (note != null && note.getPitch() != null) {
                    new Thread(() -> MusicPlayer.playNoteWithInstrument(note.getPitch(), "Trombone")).start();
                }

                if (Math.abs(sliderMappedY - noteY) < 30) {
                    circle.setFill(Color.GREEN);
                    score += 10;
                    scoreLabel.setText("Score: " + score);
                }

                toRemove.add(noteCircle);
            }
        }

        activeNotes.removeAll(toRemove);
        rootPane.getChildren().removeAll(
            toRemove.stream().map(NoteCircle::getCircle).toList()
        );
    }

    /**
     * Maps a slider value to a Y-position coordinate on the screen.
     * 
     * @param sliderValue The current value of the pitch slider.
     * @return The mapped Y-coordinate.
     */
    private double mapSliderValueToY(double sliderValue) {
        double sliderMin = pitchSlider.getMin();
        double sliderMax = pitchSlider.getMax();
        
        double yMin = 50;
        double yMax = 350;

        double percentage = (sliderValue - sliderMin) / (sliderMax - sliderMin);
        return yMax - (percentage * (yMax - yMin));
    }

    /**
     * Handles the action of returning to the library screen.
     * 
     * @throws IOException If the library FXML fails to load.
     */
    @FXML
    private void handleBackToLibrary() throws IOException {
        timer.stop();
        App.setRoot("library");
    }
}
