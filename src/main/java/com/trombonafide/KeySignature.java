package com.trombonafide;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Represents the key signature of a piece of music, including accidentals.
 * Supports transposition and relative minor identification.
 *
 * @author John Mai
 */
public class KeySignature {
    private String key;
    private String mode; // Add a mode (e.g., "major", "minor")

    private static final List<String> MAJOR_KEYS = Arrays.asList("C", "G", "D", "A", "E", "B", "F#", "C#", "F", "Bb", "Eb", "Ab", "Db", "Gb");
    private static final List<String> MINOR_KEYS = Arrays.asList("A", "E", "B", "F#", "C#", "G#", "D#", "A#", "D", "G", "C", "F", "Bb", "Eb");

    /**
     * Constructs a new KeySignature instance.
     *
     * @param key the key of the music (e.g., "C", "G")
     * @param mode the mode of the key signature (e.g., "major", "minor")
     */
    public KeySignature(String key, String mode) {
        this.key = key;
        this.mode = mode;
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
     * Gets the mode of the key signature.
     *
     * @return the mode as a string
     */
    public String getMode() {
        return mode;
    }

    /**
     * Gets the key signature as a string (e.g., "C major")
     *
     * @return the key signature as a String
     */
    public String getKeySignature() {
        return key + " " + mode;
    }

    /**
     * Gets the number of sharps in the key signature.
     *
     * @return the number of sharps
     */
    public int getSharps() {
        if (mode.equals("major")) {
            int index = MAJOR_KEYS.indexOf(key);
            if (index != -1 && index < 8) {  //Up to C# Major
                return index;
            }
        } else if (mode.equals("minor")) {
            int index = MINOR_KEYS.indexOf(key);
            if (index != -1 && index < 8) {  //Up to A# Minor
                return index;
            }
        }

        return 0;
    }

   /**
     * Gets the number of flats in the key signature.
     *
     * @return the number of flats
     */
    public int getFlats() {
        if (mode.equals("major")) {
            int index = MAJOR_KEYS.indexOf(key);
            // Check if the index is valid
            if (index != -1) {
                if (key.equals("F")) {
                    return 1; // F major has 1 flat
                }
                // For other major keys with flats
               else if (index >= 9)
               {
                return (14 - index) % 14;
               }
            }
        }
        return 0;
    }

    /**
     * Gets the list of accidentals in the key signature.
     *
     * @return the list of accidentals
     */
    public List<String> getAccidentals() {
        List<String> accidentals = new ArrayList<>();
        int sharps = getSharps();
        int flats = getFlats();

        if (sharps > 0) {
            List<String> sharpAccidentals = Arrays.asList("F#", "C#", "G#", "D#", "A#", "E#", "B#");
            for (int i = 0; i < sharps; i++) {
                accidentals.add(sharpAccidentals.get(i));
            }
        } else if (flats > 0) {
            List<String> flatAccidentals = Arrays.asList("Bb", "Eb", "Ab", "Db", "Gb", "Cb", "Fb");
            for (int i = 0; i < flats; i++) {
                accidentals.add(flatAccidentals.get(i));
            }
        }
        return accidentals;
    }

    /**
     * Transposes the key signature by a given interval.
     *
     * @param interval the interval by which to transpose (in semitones)
     */
   public void transpose(int interval) {
        if (mode.equals("major")) {
            int index = MAJOR_KEYS.indexOf(key);
            if (index != -1) {
                int newIndex = (index + interval) % MAJOR_KEYS.size();
                if (newIndex < 0) {
                    newIndex += MAJOR_KEYS.size();
                }
                key = MAJOR_KEYS.get(newIndex);
            }
        } else if (mode.equals("minor")) {
            int index = MINOR_KEYS.indexOf(key);
            if (index != -1) {
                int newIndex = (index + interval) % MINOR_KEYS.size();
                if (newIndex < 0) {
                    newIndex += MINOR_KEYS.size();
                }
                key = MINOR_KEYS.get(newIndex);
            }
        }
    }
}