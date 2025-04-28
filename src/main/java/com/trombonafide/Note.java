package com.trombonafide;

import java.io.Serializable;

/**
 * Represents a single musical note with pitch, octave, duration, and velocity.
 */
public class Note implements Serializable {
    private String pitch;
    private int octave;
    private double duration; // in beats
    private int velocity;    // volume/intensity

    /**
     * Default constructor needed for frameworks like Jackson.
     */
    public Note() {}

    /**
     * Constructs a Note with pitch, octave, duration, and velocity.
     */
    public Note(String pitch, int octave, double duration, int velocity) {
        this.pitch = pitch;
        this.octave = octave;
        this.duration = duration;
        this.velocity = velocity;
    }

    // Getters
    public String getPitch() {
        return pitch;
    }

    public int getOctave() {
        return octave;
    }

    public double getDuration() {
        return duration;
    }

    public int getVelocity() {
        return velocity;
    }

    // Setters
    public void setPitch(String pitch) {
        this.pitch = pitch;
    }

    public void setOctave(int octave) {
        this.octave = octave;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    @Override
    public String toString() {
        return pitch + octave;
    }

    public void transpose(int interval) {
        this.octave += interval / 12;
    }

    /**
 * @return the MIDI‐style semitone number for this pitch+octave
 */
public int toSemitone() {
    String p = pitch.toUpperCase();
    int base;
    switch (p) {
        case "C":  base = 0;  break;
        case "C#": case "DB": base = 1;  break;
        case "D":  base = 2;  break;
        case "D#": case "EB": base = 3;  break;
        case "E":  base = 4;  break;
        case "F":  base = 5;  break;
        case "F#": case "GB": base = 6;  break;
        case "G":  base = 7;  break;
        case "G#": case "AB": base = 8;  break;
        case "A":  base = 9;  break;
        case "A#": case "BB": base = 10; break;
        case "B":  base = 11; break;
        default:   base = 0;  break;
    }
    // octave × 12 + base
    return base + octave * 12;
}

/**
 * Convert this note’s pitch+octave into a standard MIDI number.
 * (Here we assume C4=60, so (octave+1)*12 + step).
 */
public int toMidi() {
    String p = pitch.replaceAll("[^A-G#]", "");
    int step;
    switch (p) {
        case "C":  step = 0;  break;
        case "C#": step = 1;  break;
        case "D":  step = 2;  break;
        case "D#": step = 3;  break;
        case "E":  step = 4;  break;
        case "F":  step = 5;  break;
        case "F#": step = 6;  break;
        case "G":  step = 7;  break;
        case "G#": step = 8;  break;
        case "A":  step = 9;  break;
        case "A#": step = 10; break;
        case "B":  step = 11; break;
        default:   step = 0;  break;
    }
    // C4 is MIDI 60 → (4+1)*12 + 0
    return (octave + 1) * 12 + step;
}

public class TromboneRange {
    /** Low E2 (MIDI 40) up to B♭4 (MIDI 70) is a typical tenor trombone range */
    public static final int LOW  = 40;
    public static final int HIGH = 70;
}

}
