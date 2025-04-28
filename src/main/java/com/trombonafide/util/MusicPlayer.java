package com.trombonafide.util;

import org.jfugue.player.Player;
import org.jfugue.pattern.Pattern;
import com.model.Song;
import com.trombonafide.Note;

/**
 * This class provides static methods to play songs and individual notes
 * using the JFugue music library.
 * 
 * It uses a single static {@link Player} instance to handle playback of song notes or
 * individual musical notes formatted as JFugue strings.
 * 
 * @author Andrew Lim
 */
public class MusicPlayer {
    /**
     * A single shared Player instance used for all playback operations.
     */
    private static final Player player = new Player();

    /**
     * Plays the musical notes associated with a given {@link Song}.
     * 
     * @param song the song object containing the notes and metadata to be played
     */
    public static void playSong(Song song) {
        if (song.getNotes() == null || song.getNotes().isEmpty()) {
            System.out.println("No notes found for this song.");
            return;
        }
        System.out.println("Now playing: " + song.getTitle() + " by " + song.getArtist());

        Pattern pattern = new Pattern();
        for (Note note : song.getNotes()) {
            pattern.add(note.getPitch() + note.getOctave() + "q "); // You can adjust "q" to match note duration
        }

        player.play(pattern);
    }

    /**
     * Plays a single musical note provided in JFugue string format.
     * 
     * @param note the musical note string to be played (e.g., "C5q")
     */
    public static void playNote(String note) {
        if (note == null || note.trim().isEmpty()) {
            return;
        }
        player.play(note);
    }

    public static void playNoteWithInstrument(String note, String instrument) {
        if (note == null || note.trim().isEmpty() || instrument == null) {
            return;
        }
        player.play("I[" + instrument + "] " + note);
    }
    
}
