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
     * Gets the list of accidentals in the key signature.
     *
     * @return the list of accidentals
     */
    public List<String> getAccidentals() {
        return accidentals;
    }

    /**
     * Gets the key of the key signature.
     *
     * @return the key as a string
     */
    public String getKey() {
        return key;
    }

    /**
     * Transposes the key signature by a given interval.
     *
     * @param interval the interval by which to transpose
     */
    public void transpose(int interval) {
        // Example: Use accidentals in transposition logic
        System.out.println("Transposing key: " + key + " by " + interval + " steps.");
    }

    /**
     * Gets the relative minor key.
     *
     * @return the relative minor key as a string
     */
    public String getRelativeMinor() {
        return key + " Minor"; // Example logic
    }
}
