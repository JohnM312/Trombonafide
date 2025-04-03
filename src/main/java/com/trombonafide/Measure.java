package com.trombonafide; 
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a musical measure that contains notes and is defined by a time signature and tempo.
 * 
 * @author John Mai
 */
public class Measure {
    private int timeSignatureTop;
    private int timeSignatureBottom;
    private int tempo;
    private List<Note> notes = new ArrayList<>(); 

    /**
     * Constructs a Measure with a specified time signature and tempo.
     *
     * @param timeSignatureTop the numerator of the time signature 
     * @param timeSignatureBottom the denominator of the time signature
     * @param tempo the tempo in beats per minute (BPM)
     */
    public Measure(int timeSignatureTop, int timeSignatureBottom, int tempo) {
        this.timeSignatureTop = timeSignatureTop;
        this.timeSignatureBottom = timeSignatureBottom;
        this.tempo = tempo;
    }

    /**
     * Adds a note to the measure.
     *
     * @param note the Note object to be added
     */
    public void addNote(Note note) {
        this.notes.add(note);
    }

    /**
     * Returns the list of notes in the measure.
     *
     * @return a List of Note objects
     */
    public List<Note> getNotes() {
        return notes;
    }

     /**
     * Returns the top number (numerator) of the time signature.
     *
     * @return the time signature top number
     */
    public int getTimeSignatureTop() {
        return timeSignatureTop;
    }

    /**
     * Returns the bottom number (denominator) of the time signature.
     *
     * @return the time signature bottom number
     */
    public int getTimeSignatureBottom() {
        return timeSignatureBottom;
    }

    /**
     * Returns the tempo of the measure in beats per minute (BPM).
     *
     * @return the tempo
     */
    public int getTempo() {
        return tempo;
    }

}