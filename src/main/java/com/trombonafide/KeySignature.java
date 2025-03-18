import java.util.List;

public class KeySignature {
    private String key;
    private List<String> accidentals;

    public KeySignature(String key, List<String> accidentals) {
        this.key = key;
        this.accidentals = accidentals;
    }

    public void transpose(int interval) {
        // Transpose key signature
    }

    public String getRelativeMinor() {
        // Return relative minor key
        return "";
    }
}
