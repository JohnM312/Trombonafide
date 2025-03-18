import java.util.List;

public class Measure {
    private List<Note> notes;
    private List<Chord> chords;
    private TimeSignature timeSignature;
    private KeySignature keySignature;

    public Measure(TimeSignature timeSignature, KeySignature keySignature) {
        this.timeSignature = timeSignature;
        this.keySignature = keySignature;
    }

    public void addNote(Note note) {
        notes.add(note);
    }

    public void addChord(Chord chord) {
        chords.add(chord);
    }

    public float getDuration() {
        // Compute measure duration
        return 0;
    }
}
