public class Note {
    private String pitch;
    private int octave;
    private float duration;
    private int velocity;

    public Note(String pitch, int octave, float duration, int velocity) {
        this.pitch = pitch;
        this.octave = octave;
        this.duration = duration;
        this.velocity = velocity;
    }

    public void play() {
        // Play the note
    }

    public void transpose(int interval) {
        // Adjust pitch by interval
    }

    public float getFrequency() {
        // Calculate frequency of the note
        return 0;
    }
}
