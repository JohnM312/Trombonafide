package com.trombonafide;

import com.trombonafide.util.MusicPlayer;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.IOException;

/**
 * Controller for the slider lesson screen, where users
 * practice matching different pitches by sliding the trombone.
 */
public class SlideLessonController {

    @FXML private AnchorPane rootPane;
    @FXML private Slider slideSlider;
    @FXML private Button startButton;
    @FXML private Label instructionLabel;

    private AnimationTimer sliderMonitor;
    private boolean lessonStarted = false;
    private double lastPlayedPitch = -1;

    private static final double PITCH_MIN = 40; 
    private static final double PITCH_MAX = 80; 
    @FXML
    public void initialize() {
        slideSlider.setMin(PITCH_MIN);
        slideSlider.setMax(PITCH_MAX);
        slideSlider.setValue(PITCH_MIN);
        slideSlider.setDisable(true);

        instructionLabel.setText("Press Start to begin sliding practice!");
        instructionLabel.setFont(Font.font(20));
        instructionLabel.setTextFill(Color.WHITE);

        // Monitor slider movement
        sliderMonitor = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (lessonStarted) {
                    double pitch = slideSlider.getValue();
                    if (Math.abs(pitch - lastPlayedPitch) > 1) { // Only trigger if moved enough
                        playPitch(pitch);
                        lastPlayedPitch = pitch;
                    }
                }
            }
        };
    }

    @FXML
    private void handleStartLesson() {
        lessonStarted = true;
        slideSlider.setDisable(false);
        instructionLabel.setText("Move the slide to change pitch!");

        sliderMonitor.start();
    }

    private void playPitch(double pitchValue) {
        String noteName = midiToNoteName((int) Math.round(pitchValue));

        new Thread(() -> {
            MusicPlayer.playNoteWithInstrument(noteName, "Trombone");
        }).start();
    }

    // Helper to convert MIDI number to a note like "C4"
    private String midiToNoteName(int midiNumber) {
        String[] noteNames = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};
        int octave = (midiNumber / 12) - 1;
        String note = noteNames[midiNumber % 12];
        return note + octave;
    }

    @FXML
    private void handleBackToHome() throws IOException {
        lessonStarted = false;
        sliderMonitor.stop();
        App.setRoot("home");
    }
}
