package com.trombonafide;

import com.model.Song;
import com.trombonafide.Note;
import com.trombonafide.util.MusicPlayer;
import javafx.animation.AnimationTimer;
import javafx.animation.PauseTransition;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Controller for the play screen, where users match a slider
 * to falling notes corresponding to a trombone melody.
 */
public class PlayController {

    @FXML private Slider pitchSlider;
    @FXML private Label scoreLabel;
    @FXML private AnchorPane rootPane;

    private final List<NoteCircle> activeNotes = new ArrayList<>();
    private final Random random = new Random();
    private final Color[] NOTE_COLORS = {
        Color.CORNFLOWERBLUE, Color.MEDIUMPURPLE, Color.ORANGE, Color.PINK, Color.YELLOWGREEN
    };

    private List<Note> songNotes = new ArrayList<>();
    private int currentNoteIndex = 0;
    private int score = 0;
    private int streak = 0;
    private int hitCount = 0;
    private int missCount = 0;

    private long lastNoteTime = 0;
    private int minMidi = 0, maxMidi = 1;
    private AnimationTimer timer;
    private Song currentSong;

    private static final double MARGIN = 40;
    private static final double SPEED = 3;
    private static final double HIT_THRESHOLD = 30;

    @FXML
    public void initialize() {
        // Prepare the game loop but do not start yet
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                updateFrame(now);
            }
        };
    }

    /**
     * Loads the chosen song’s notes and computes its pitch range.
     * @param song The song to play.
     */
    public void setSong(Song song) {
        this.currentSong = song;
        if (song.getNotes() != null) {
            this.songNotes = song.getNotes();
        }
    }

    /**
     * Resets state and begins the falling-note animation.
     */
    public void startPlaying() {
        score = 0;
        streak = 0;
        hitCount = 0;
        missCount = 0;
        scoreLabel.setText("Score: 0");
        currentNoteIndex = 0;
        activeNotes.clear();
        lastNoteTime = 0;

        // Determine MIDI range for vertical mapping
        minMidi = songNotes.stream().mapToInt(Note::toMidi).min().orElse(0);
        maxMidi = songNotes.stream().mapToInt(Note::toMidi).max().orElse(minMidi + 1);

        pitchSlider.setMin(minMidi);
        pitchSlider.setMax(maxMidi);
        pitchSlider.setValue(minMidi);

        timer.start();
    }

    private void updateFrame(long now) {
        // Spawn next note every second
        if (currentNoteIndex < songNotes.size()
                && now - lastNoteTime > 1_000_000_000L) {
            spawnCircle(songNotes.get(currentNoteIndex));
            currentNoteIndex++;
            lastNoteTime = now;
        }
        moveCircles();

        // If all notes have been spawned and none remain on screen, finish
        if (currentNoteIndex >= songNotes.size() && activeNotes.isEmpty()) {
            timer.stop();
            showResults();
        }
    }

    private void spawnCircle(Note note) {
        int midi = note.toMidi();
        Circle c = new Circle(12, NOTE_COLORS[random.nextInt(NOTE_COLORS.length)]);
        c.setCenterX(rootPane.getWidth() + 20);
        c.setCenterY(mapMidiToY(midi));
        rootPane.getChildren().add(c);
        activeNotes.add(new NoteCircle(c, note));
    }

    private void moveCircles() {
        var toRemove = new ArrayList<NoteCircle>();
        for (NoteCircle nc : activeNotes) {
            Circle c = nc.getCircle();
            c.setCenterX(c.getCenterX() - SPEED);

            if (c.getCenterX() <= 70) {
                Note note = nc.getNote();
                // Always play the trombone note on arrival
                new Thread(() ->
                    MusicPlayer.playNoteWithInstrument(note.getPitch(), "Trombone")
                ).start();

                // Check hit or miss
                double sliderY = mapMidiToY((int) pitchSlider.getValue());
                boolean hit = Math.abs(sliderY - c.getCenterY()) < HIT_THRESHOLD;

                int prevStreak = streak;
                if (hit) {
                    hitCount++;
                    streak++;
                    int points = 10 * streak;
                    score += points;
                    scoreLabel.setText("Score: " + score);
                    flashPoints(c.getCenterX(), c.getCenterY(), "+" + points);

                    // every multiple of 5 in the streak shows a milestone
                    if (streak >= 5 && streak % 5 == 0) {
                        flashPopup(streak + "-Streak!");
                    }
                } else {
                    missCount++;
                    if (prevStreak >= 3) {
                        flashPopup("Streak ended");
                    }
                    streak = 0;
                }

                toRemove.add(nc);
            }
        }

        activeNotes.removeAll(toRemove);
        toRemove.forEach(nc -> rootPane.getChildren().remove(nc.getCircle()));
    }

    private double mapMidiToY(int midi) {
        double h = rootPane.getHeight();
        double usable = h - 2 * MARGIN;
        double pct = (double) (midi - minMidi) / (maxMidi - minMidi);
        pct = Math.max(0, Math.min(1, pct));
        return MARGIN + (1 - pct) * usable;
    }

    private void flashPoints(double x, double y, String text) {
        Label pop = new Label(text);
        pop.setFont(Font.font(24));
        pop.setTextFill(Color.LIMEGREEN);
        pop.setLayoutX(x);
        pop.setLayoutY(y - 20);
        rootPane.getChildren().add(pop);

        PauseTransition wait = new PauseTransition(Duration.seconds(0.6));
        wait.setOnFinished(e -> rootPane.getChildren().remove(pop));
        wait.play();
    }

    private void flashPopup(String message) {
        Label pop = new Label(message);
        pop.setFont(Font.font(32));
        pop.setTextFill(Color.WHITE);
        double approxWidth = pop.getText().length() * 10;
        pop.setLayoutX((rootPane.getWidth() - approxWidth) / 2);
        pop.setLayoutY(30);
        rootPane.getChildren().add(pop);

        PauseTransition wait = new PauseTransition(Duration.seconds(1.2));
        wait.setOnFinished(e -> rootPane.getChildren().remove(pop));
        wait.play();
    }

    /**
     * Displays the final results overlay when the song completes,
     * with buttons for Home and Library.
     */
    private void showResults() {
        // Dim background
        AnchorPane overlay = new AnchorPane();
        overlay.setStyle("-fx-background-color: rgba(0,0,0,0.75);");
        overlay.setPrefSize(rootPane.getWidth(), rootPane.getHeight());

        // Title
        Label title = new Label("Song Completed! Congrats!");
        title.setFont(Font.font(36));
        title.setTextFill(Color.GOLD);
        title.setLayoutX((rootPane.getWidth() - 400) / 2);
        title.setLayoutY(100);

        // Stats
        String statsText = String.format(
            "Final Score: %d\nHits: %d    Misses: %d",
            score, hitCount, missCount
        );
        Label stats = new Label(statsText);
        stats.setFont(Font.font(24));
        stats.setTextFill(Color.WHITE);
        stats.setLayoutX((rootPane.getWidth() - 300) / 2);
        stats.setLayoutY(180);

        // Home button
        Button homeBtn = new Button("Home");
        homeBtn.setFont(Font.font(18));
        homeBtn.setLayoutX((rootPane.getWidth() / 2) - 100);
        homeBtn.setLayoutY(260);
        homeBtn.setOnAction(e -> {
            timer.stop();
            try {
                App.setRoot("home");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        // Library button
        Button libBtn = new Button("Library");
        libBtn.setFont(Font.font(18));
        libBtn.setLayoutX((rootPane.getWidth() / 2) + 20);
        libBtn.setLayoutY(260);
        libBtn.setOnAction(e -> {
            timer.stop();
            try {
                App.setRoot("library");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        overlay.getChildren().addAll(title, stats, homeBtn, libBtn);
        rootPane.getChildren().add(overlay);
    }

    @FXML
    private void handleBackToLibrary() throws IOException {
        timer.stop();
        App.setRoot("library");
    }
}
