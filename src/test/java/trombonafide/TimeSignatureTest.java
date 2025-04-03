package trombonafide;

import org.junit.Test;
import static org.junit.Assert.*;

import com.trombonafide.TimeSignature;

/**
 * @author John Mai
 */
public class TimeSignatureTest {

    @Test
    public void testValidTimeSignatureCreation() {
        TimeSignature timeSignature = new TimeSignature(4, 4);
        assertEquals("Beats should be 4", 4, timeSignature.getBeats());
        assertEquals("Beat unit should be 4", 4, timeSignature.getBeatUnit());
        assertEquals("Time signature string should be 4/4", "4/4", timeSignature.getTimeSignature());
    }

    @Test
    public void testInvalidTimeSignatureCreationZeroBeats() {
        TimeSignature timeSignature = new TimeSignature(0, 4);
        assertFalse("Time signature should be invalid", timeSignature.isValid());
    }

    @Test
    public void testInvalidTimeSignatureCreationNegativeBeats() {
        TimeSignature timeSignature = new TimeSignature(-3, 4);
        assertFalse("Time signature should be invalid", timeSignature.isValid());
    }
    @Test
    public void testGettersAndSetters() {
        TimeSignature timeSignature = new TimeSignature(4, 4);

        timeSignature.setBeats(3);
        assertEquals("Beats should be 3", 3, timeSignature.getBeats());

        timeSignature.setBeatUnit(8);
        assertEquals("Beat unit should be 8", 8, timeSignature.getBeatUnit());
    }

    @Test
    public void testNonStandardTimeSignature() {
        TimeSignature timeSignature = new TimeSignature(7, 8);
        assertEquals("Beats should be 7", 7, timeSignature.getBeats());
        assertEquals("Beat unit should be 8", 8, timeSignature.getBeatUnit());
        assertEquals("Time signature string should be 7/8", "7/8", timeSignature.getTimeSignature());
        assertTrue("Time signature should be valid", timeSignature.isValid());
    }

}