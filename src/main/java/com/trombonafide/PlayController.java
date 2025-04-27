package com.trombonafide;

import com.model.Song;
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

public class PlayController {

    @FXML
    private Slider pitchSlider;

    @FXML
    private Label scoreLabel;

    @FXML
    private AnchorPane rootPane;

    private List<Circle> notes = new ArrayList<>();
    private int score = 0;
    private AnimationTimer timer;
    private Song currentSong;

    @FXML
    public void initialize() {
        // Prepare the timer but don't start yet
        timer = new AnimationTimer() {
            private long lastSpawnTime = 0;

            @Override
            public void handle(long now) {
                if (now - lastSpawnTime > 1_000_000_000) { // Every second
                    spawnNote();
                    lastSpawnTime = now;
                }
                moveNotes();
            }
        };
    }

    public void setSong(Song song) {
        this.currentSong = song;
    }

    public void startPlaying() {
        timer.start();
    }

    private void spawnNote() {
        Circle note = new Circle(10, Color.YELLOW);
        note.setCenterX(600);
        note.setCenterY(Math.random() * 300 + 50);
        rootPane.getChildren().add(note);
        notes.add(note);
    }

    private void moveNotes() {
        List<Circle> toRemove = new ArrayList<>();
        for (Circle note : notes) {
            note.setCenterX(note.getCenterX() - 2);

            if (note.getCenterX() <= 70) {
                double sliderPos = pitchSlider.getValue();
                double notePos = note.getCenterY();

                if (Math.abs(sliderPos - (notePos - 50)) < 10) {
                    // Correct note hit
                    note.setFill(Color.GREEN);
                    score += 10;
                    scoreLabel.setText("Score: " + score);
                }
                toRemove.add(note);
            }
        }
        notes.removeAll(toRemove);
        rootPane.getChildren().removeAll(toRemove);
    }

    @FXML
    private void handleBackToLibrary() throws IOException {
        timer.stop();
        App.setRoot("library");
    }
}
