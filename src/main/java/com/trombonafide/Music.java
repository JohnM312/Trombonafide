package com.trombonafide;

import org.jfugue.player.Player;
/**
 * this class provides a simple interface for playing individual musicnotes 
 * using the JFugue music library
 * 
 * depends on: {@link org.jfugue.player.Player)}
 * 
 * @author Andrew Lim
 */
public class Music {
    /**
     * plays a single musical note using the JFugue {@link Player}
     * @param note a string representating the musical note in JFugue format
     */
    public static void playNote(String note) {
        Player player = new Player();
        player.play(note);
    }
}
