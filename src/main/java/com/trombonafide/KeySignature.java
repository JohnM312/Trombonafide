package com.trombonafide;

import java.util.List;

/**
 * Represents the key signature of a piece of music, including accidentals.
 * Supports transposition and relative minor identification.
 * 
 * @author John Mai
 */
public class KeySignature {
    private String key;
    private List<String> accidentals;

    /**
     * Constructs a new KeySignature instance.
     *
     * @param key the key of the music (e.g., "C Major", "G Minor")
     * @param accidentals the list of accidentals present in the key signature
     */
    public KeySignature(String key, List<String> accidentals) {
        this.key = key;
        this.accidentals = accidentals;
    }

    /**
     * Transposes the key signature by a given interval.
     *
     * @param interval the interval by which to transpose
     */
    public void transpose(int interval) {
        // Transpose key signature
    }

    /**
     * Gets the relative minor key.
     *
     * @return the relative minor key as a string
     */
    public String getRelativeMinor() {
        return "";
    }
}
