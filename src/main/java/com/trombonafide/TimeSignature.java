package com.trombonafide;

public class TimeSignature {
    private int numerator;
    private int denominator;

    public TimeSignature(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public int getBeatsPerMeasure() {
        return numerator;
    }

    public boolean isCompound() {
        return numerator % 3 == 0;
    }
}
