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
     *
     * @return the beats per measure
     */
    public int getBeatsPerMeasure() {
        return numerator;
    }

    /**
     * Checks if the time signature is compound.
     *
     * @return true if the time signature is compound, false otherwise
     */
    public boolean isCompound() {
        return numerator % 3 == 0;
    }
}
