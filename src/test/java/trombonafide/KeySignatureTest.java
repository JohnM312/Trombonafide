package trombonafide;

import org.junit.Test;
import static org.junit.Assert.*;

import com.trombonafide.KeySignature;

public class KeySignatureTest {

    @Test
    public void testCMajorKeySignatureCreation() {
        KeySignature keySignature = new KeySignature("C", "major");
        assertEquals("Key should be C", "C", keySignature.getKey());
        assertEquals("Mode should be major", "major", keySignature.getMode());
        assertEquals("Sharps should be 0", 0, keySignature.getSharps());
        assertEquals("Flats should be 0", 0, keySignature.getFlats());
        assertEquals("Key signature string should be C major", "C major", keySignature.getKeySignature());
    }

    @Test
    public void testGMajorKeySignatureCreation() {
        KeySignature keySignature = new KeySignature("G", "major");
        assertEquals("Sharps should be 1", 1, keySignature.getSharps());
        assertEquals("Flats should be 0", 0, keySignature.getFlats());
    }

    @Test
    public void testFMajorKeySignatureCreation() {
        KeySignature keySignature = new KeySignature("F", "major");
        assertEquals("Sharps should be 0", 0, keySignature.getSharps());
        assertEquals("Flats should be 1", 1, keySignature.getFlats());
    }

    @Test
    public void testAMinorKeySignatureCreation() {
        KeySignature keySignature = new KeySignature("A", "minor");
        assertEquals("Sharps should be 0", 0, keySignature.getSharps());
        assertEquals("Flats should be 0", 0, keySignature.getFlats());
    }

    @Test
    public void testTransposeKeySignature() {
        KeySignature keySignature = new KeySignature("C", "major");
        keySignature.transpose(2);
        assertEquals("Transposed key should be D", "D", keySignature.getKey());
        assertEquals("Transposed mode should still be major", "major", keySignature.getMode());
    }
}