package com.trombonafide.util;

import org.jfugue.player.Player;
import com.model.Song;

public class MusicPlayer {
    public static void playSong(Song song) {
        if (song.getNotes() == null || song.getNotes().isEmpty()) {
            System.out.println("No notes found for this song.");
            return;
        }

        System.out.println("Now playing: " + song.getTitle() + " by " + song.getArtist());
        Player player = new Player();
        player.play(song.getNotes());
    }
}