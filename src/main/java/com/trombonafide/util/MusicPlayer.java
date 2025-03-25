package com.trombonafide.util;

import org.jfugue.player.Player;
import com.model.Song;

public class MusicPlayer {
    private static final Player player = new Player();
    
    public static void playSong(Song song) {
        if (song.getNotes() == null || song.getNotes().isEmpty()) {
            System.out.println("No notes found for this song.");
            return;
        }
        System.out.println("Now playing: " + song.getTitle() + " by " + song.getArtist());
        player.play(song.getNotes());
    }

    public static void playNote(String note) {
        if (note == null || note.trim().isEmpty()) {
            return;
        }
        player.play(note);
    }
}