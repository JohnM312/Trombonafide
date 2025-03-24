package com.trombonafide;

/**
 * Represents a musical note with pitch, octave, duration, and velocity.
 * 
 * @author John Mai
 */
public class Note {
    private String pitch;
    private int octave;
    private float duration;
    private int velocity;

    /**
     * Constructs a new Note instance.
     *
     * @param pitch the pitch of the note (e.g., "C#", "A")
     * @param octave the octave in which the note is played
     * @param duration the duration of the note in beats
     * @param velocity the velocity (intensity) of the note
     */
    public Note(String pitch, int octave, float duration, int velocity) {
        this.pitch = pitch;
        this.octave = octave;
        this.duration = duration;
        this.velocity = velocity;
    }

    /**
     * Plays the note.
     */
    public void play() {
        // Play the note
    }

    /**
     * Transposes the note by a given interval.
     *
     * @param interval the interval by which to transpose the note
     */
    public void transpose(int interval) {
        // Adjust pitch by interval
    }

    /**
     * Gets the frequency of the note.
     *
     * @return the frequency in Hz
     */
    public float getFrequency() {
        return 0;
    }
}
