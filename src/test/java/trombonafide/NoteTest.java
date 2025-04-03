package trombonafide;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import com.trombonafide.Note;

/**
 * @author John Mai
 */
public class NoteTest {

    @Test
    public void testNoteCreation() {
        Note note = new Note("C4", 0.5);
        assertEquals("Pitch should be set correctly", "C4", note.getPitch());
        assertEquals("Duration should be set correctly", 0.5, note.getDuration(), 0.0001); // Add delta for double comparison
    }

    @Test
    public void testGetPitch() {
        Note note = new Note("D4", 1.0);
        assertEquals("Pitch should be retrieved correctly", "D4", note.getPitch());
    }

    @Test
    public void testGetDuration() {
        Note note = new Note("E4", 0.25);
        assertEquals("Duration should be retrieved correctly", 0.25, note.getDuration(), 0.0001); // Add delta for double comparison
    }

    @Test
    public void testTransposeNote() {
        Note note = new Note("C4", 0.5);
        note.transpose(2); // Transpose up by 2 semitones
        assertEquals("Transposed pitch should be D4", "D4", note.getPitch());
    }

    @Test
    public void testNoteCreationWithDifferentDuration() {
        Note note = new Note("A#5", 1.5);
        assertEquals("Pitch should be set correctly", "A#5", note.getPitch());
        assertEquals("Duration should be set correctly", 1.5, note.getDuration(), 0.0001);
    }
}