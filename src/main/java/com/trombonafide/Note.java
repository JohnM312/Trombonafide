package com.trombonafide;

public class Note {
    private String pitch;
    private double duration;
    private int octave; // Added octave
    private int velocity; // Added velocity

    public Note(String pitch, double duration) {
        this.pitch = pitch;
        this.duration = duration;
    }

    public Note(String pitch, int octave, double duration, int velocity) {
        this.pitch = pitch;
        this.octave = octave;
        this.duration = duration;
        this.velocity = velocity;
    }

    public String getPitch() {
        return pitch;
    }

    public double getDuration() {
        return duration;
    }

    public void transpose(int interval) {
        // This is a VERY basic implementation.  It only shifts the pitch name, and may not be musically correct.
        String[] notes = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};
        for (int i = 0; i < notes.length; i++) {
            if (pitch.startsWith(notes[i])) {  //If the note pitch begins with the notes at this index.
                int newIndex = (i + interval) % notes.length;
                if (newIndex < 0) {
                    newIndex += notes.length;  // Handle negative indices
                }

                String newPitch = notes[newIndex];
                // If the note has an octave number, preserve it.
                if (pitch.length() > notes[i].length()) {
                    newPitch += pitch.substring(notes[i].length());
                }
                pitch = newPitch;
                break;
            }
        }
    }
}