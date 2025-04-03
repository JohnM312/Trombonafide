package trombonafide;

import org.junit.Test;
import static org.junit.Assert.*;

import com.trombonafide.*;

/**
 * @author Trent Petersen
 */
public class MusicTest {

    @Test
    public void testPlayNoteWithValidInput() {
        try {
            // Test with various valid note formats
            Music.playNote("C5q");  // C in octave 5, quarter note
            Music.playNote("G6h");  // G in octave 6, half note
            Music.playNote("A4w");  // A in octave 4, whole note
            Music.playNote("Bb3i"); // B-flat in octave 3, eighth note
            assertTrue(true); // If we get here, all notes played successfully
        } catch (Exception e) {
            fail("Valid notes should not throw exceptions");
        }
    }

    @Test
    public void testPlayNoteWithDifferentDurations() {
        String[] durations = {"q", "i", "h", "w"};
        for (String duration : durations) {
            try {
                Music.playNote("C5" + duration);
                assertTrue(true);
            } catch (Exception e) {
                fail("Failed to play note with duration " + duration);
            }
        }
    }

    @Test
    public void testPlayNoteWithDifferentOctaves() {
        // Test a reasonable range of octaves
        for (int octave = 3; octave <= 7; octave++) {
            try {
                Music.playNote("C" + octave + "q");
                assertTrue(true);
            } catch (Exception e) {
                fail("Failed to play note in octave " + octave);
            }
        }
    }
}