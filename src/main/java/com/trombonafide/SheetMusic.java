import java.util.List;

public class SheetMusic {
    private String title;
    private String composer;
    private int tempo;
    private KeySignature keySignature;
    private TimeSignature timeSignature;
    private List<Measure> measures;

    public SheetMusic(String title, String composer, int tempo, KeySignature keySignature, TimeSignature timeSignature) {
        this.title = title;
        this.composer = composer;
        this.tempo = tempo;
        this.keySignature = keySignature;
        this.timeSignature = timeSignature;
    }

    public void displaySheetMusic() {
        // Implementation for displaying sheet music
    }

    public void transposeKey(String key) {
        keySignature.transpose(0); // Example transpose call
    }

    public void adjustTempo(int newTempo) {
        this.tempo = newTempo;
    }

    public void removeNotation(Note note) {
        // Implementation for removing notation
    }

    public void displayChord(Chord chord) {
        // Implementation for displaying chord
    }

    public void displayNote(Note note) {
        // Implementation for displaying note
    }

    public void displayMeasure(Measure measure) {
        // Implementation for displaying measure
    }
}
