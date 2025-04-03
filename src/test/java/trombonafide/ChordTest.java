package trombonafide;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import com.trombonafide.Chord;
import com.trombonafide.Note;

/**
 * @author John Mai
 */
public class ChordTest {

    @Test
    public void testChordCreation() {
        List<Note> notes = new ArrayList<>();
        notes.add(new Note("C4", 0.5));
        notes.add(new Note("E4", 0.5));
        notes.add(new Note("G4", 0.5));
        Chord chord = new Chord("C Major", notes, "Major", 0);
        assertNotNull("Chord should not be null", chord);
        assertEquals("Chord notes should match", notes, chord.getNotes());
    }

    @Test
    public void testGetNotes() {
        List<Note> notes = new ArrayList<>();
        notes.add(new Note("C4", 0.5));
        notes.add(new Note("E4", 0.5));
        Chord chord = new Chord("Some Chord", notes, "Unknown", 0);
        List<Note> retrievedNotes = chord.getNotes();
        assertEquals("Retrieved notes should match original notes", notes, retrievedNotes);
    }

    @Test
    public void testTransposeChord() {
        List<Note> notes = new ArrayList<>();
        notes.add(new Note("C4", 0.5));
        notes.add(new Note("E4", 0.5));
        Chord chord = new Chord("Test Chord", notes, "Test", 0);
        chord.transpose(2);

        assertEquals("First note should be transposed to D4", "D4", chord.getNotes().get(0).getPitch());
        assertEquals("Second note should be transposed to F#4", "F#4", chord.getNotes().get(1).getPitch());
    }

    @Test
    public void testGetNotesWithNullElement() {
        List<Note> notes = new ArrayList<>();
        notes.add(new Note("C4", 0.5));
        notes.add(null); 
        notes.add(new Note("G4", 0.5));
        Chord chord = new Chord("Chord with Null", notes, "Test", 0);
        List<Note> retrievedNotes = chord.getNotes();

        assertEquals("Retrieved notes size should match", notes.size(), retrievedNotes.size());

        assertEquals("First note should match", notes.get(0), retrievedNotes.get(0));
        assertNull("Second note should be null", retrievedNotes.get(1)); // Assert that the null element is still null
        assertEquals("Third note should match", notes.get(2), retrievedNotes.get(2));
    }

    @Test
    public void testGetNotesEmpty() {
        List<Note> notes = new ArrayList<>(); 
        Chord chord = new Chord("Empty Chord", notes, "Empty", 0);
        List<Note> retrievedNotes = chord.getNotes();
        assertNotNull("Retrieved notes should not be null", retrievedNotes);
        assertTrue("Retrieved notes should be empty", retrievedNotes.isEmpty());
    }
}