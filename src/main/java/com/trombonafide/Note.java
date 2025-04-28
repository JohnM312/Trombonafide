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
}
