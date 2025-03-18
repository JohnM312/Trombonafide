import java.util.List;

public class Chord {
    private String name;
    private List<Note> notes;
    private String quality;
    private int inversion;

    public Chord(String name, List<Note> notes, String quality, int inversion) {
        this.name = name;
        this.notes = notes;
        this.quality = quality;
        this.inversion = inversion;
    }

    public void play() {
        // Play the chord
    }

    public void transpose(int interval) {
        for (Note note : notes) {
            note.transpose(interval);
        }
    }

    public List<Note> getNotes() {
        return notes;
    }
}
