package com.trombonafide;

/**
 * Represents the time signature of a piece of music, defining beats per measure.
 *
 * @author John Mai
 */
public class TimeSignature {
    private int numerator;
    private int denominator;

    /**
     * Constructs a new TimeSignature instance.
     *
     * @param numerator the number of beats per measure
     * @param denominator the note value representing one beat
     */
    public TimeSignature(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    /**
     * Gets the number of beats per measure.
     *  Renamed from getBeatsPerMeasure() to getBeats() to be consistent with the test class
     * @return the beats per measure
     */
    public int getBeats() {
        return numerator;
    }

    /**
     * Gets the beat unit (the note value representing one beat).
     *
     * @return the beat unit
     */
    public int getBeatUnit() {
        return denominator;
    }

    /**
     * Gets the time signature as a string (e.g., "4/4").
     *
     * @return the time signature string
     */
    public String getTimeSignature() {
        return numerator + "/" + denominator;
    }

    /**
     * Checks if the time signature is valid (e.g., both numerator and denominator are positive).
     *
     * @return true if the time signature is valid, false otherwise
     */
    public boolean isValid() {
        return numerator > 0 && denominator > 0;
    }

    /**
     * Sets the number of beats per measure.
     *
     * @param numerator the new number of beats per measure
     */
    public void setBeats(int numerator) {
        this.numerator = numerator;
    }

    /**
     * Sets the beat unit (the note value representing one beat).
     *
     * @param denominator the new beat unit
     */
    public void setBeatUnit(int denominator) {
        this.denominator = denominator;
    }
}