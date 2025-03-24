package com.trombonafide;

import java.util.List;

/**
 * Represents a sheet of music, containing information about title, composer, tempo, 
 * key signature, time signature, and measures.
 * 
 * @author John Mai
 */
public class SheetMusic {
    private String title;
    private String composer;
    private int tempo;
    private KeySignature keySignature;
    private TimeSignature timeSignature;
    private List<Measure> measures;

    /**
     * Constructs a new SheetMusic instance.
     *
     * @param title the title of the sheet music
     * @param composer the name of the composer
     * @param tempo the tempo of the music
     * @param keySignature the key signature
     * @param timeSignature the time signature
     */
    public SheetMusic(String title, String composer, int tempo, KeySignature keySignature, TimeSignature timeSignature) {
        this.title = title;
        this.composer = composer;
        this.tempo = tempo;
        this.keySignature = keySignature;
        this.timeSignature = timeSignature;
    }

    /**
     * Displays the sheet music.
     */
    public void displaySheetMusic() {
        // Implementation for displaying sheet music
    }

    /**
     * Transposes the sheet music to a new key.
     *
     * @param key the new key to transpose to
     */
    public void transposeKey(String key) {
        keySignature.transpose(0);
    }

    /**
     * Adjusts the tempo of the sheet music.
     *
     * @param newTempo the new tempo value
     */
    public void adjustTempo(int newTempo) {
        this.tempo = newTempo;
    }

    /**
     * Removes a notation (note) from the sheet music.
     *
     * @param note the note to remove
     */
    public void removeNotation(Note note) {
        // Implementation for removing notation
    }
}
