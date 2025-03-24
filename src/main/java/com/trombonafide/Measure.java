package com.trombonafide;

import java.util.List;

/**
 * Represents a measure in sheet music, containing notes and chords.
 * It also holds time and key signature information.
 * 
 * @author John Mai
 */
public class Measure {
    private List<Note> notes;
    private List<Chord> chords;
    private TimeSignature timeSignature;
    private KeySignature keySignature;

    /**
     * Constructs a new Measure instance.
     *
     * @param timeSignature the time signature of the measure
     * @param keySignature the key signature of the measure
     */
    public Measure(TimeSignature timeSignature, KeySignature keySignature) {
        this.timeSignature = timeSignature;
        this.keySignature = keySignature;
    }

    /**
     * Adds a note to the measure.
     *
     * @param note the note to add
     */
    public void addNote(Note note) {
        notes.add(note);
    }

    /**
     * Adds a chord to the measure.
     *
     * @param chord the chord to add
     */
    public void addChord(Chord chord) {
        chords.add(chord);
    }

    /**
     * Gets the total duration of the measure.
     *
     * @return the duration of the measure
     */
    public float getDuration() {
        return 0;
    }
}
