package com.model;

import com.trombonafide.*;

public class MusicPlayer {
    public static void main(String[] args) {
        // Notes for "Mary Had a Little Lamb"
        String[] melody = {
            "E", "D", "C", "D", "E", "E", "E", 
            "D", "D", "D", "E", "G", "G", 
            "E", "D", "C", "D", "E", "E", "E", 
            "E", "D", "D", "E", "D", "C"
        };

        // Duration for each note
        int noteDuration = 50;

        // Duration for the pause between phrases
        int phrasePause = 600;

        // Play each note in the melody
        for (int i = 0; i < melody.length; i++) {
            Music.playNote(melody[i]);
            try {
                // Add a longer pause after specific phrases
                if (i == 6 || i == 12 || i == 19) { // Pause after the 7th, 13th, and 20th notes
                    Thread.sleep(phrasePause);
                } else {
                    Thread.sleep(noteDuration); // Normal pause between notes
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}