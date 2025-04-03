package com.trombonafide;

import java.util.List;

/**
 * Represents a musical chord, consisting of multiple notes played together.
 * @author John Mai
 */
@SuppressWarnings("unused")
public class Chord {
    private String name;
    private List<Note> notes;
    private String quality;
    private int inversion;

    /**
     * Constructs a new Chord instance.
     * @param name the name of the chord
     * @param notes the list of notes forming the chord
     * @param quality the chord quality (e.g., major, minor)
     * @param inversion the inversion level of the chord
     */
    public Chord(String name, List<Note> notes, String quality, int inversion) {
        this.name = name;
        this.notes = notes;
        this.quality = quality;
        this.inversion = inversion;
    }

    /**
     * Plays the chord.
     */
    public void play() {
        // Play the chord
    }

    /**
     * Transposes the chord by a given interval.
     * @param interval the interval by which to transpose the chord
     */
    public void transpose(int interval) {
        for (Note note : notes) {
            note.transpose(interval);
        }
    }

    /**
     * Gets the list of notes forming the chord.
     * @return the list of notes
     */
    public List<Note> getNotes() {
        return notes;
    }
}