package trombonafide;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import com.trombonafide.Measure;
import com.trombonafide.Note;

/**
 * @author John Mai
 */
public class MeasureTest { 

    @Test
    public void testAddNote() {
        Measure measure = new Measure(4, 4, 120);
        Note note = new Note("C4", 1.0);
        measure.addNote(note);
        assertEquals("Note should be added to the measure", 1, measure.getNotes().size()); // Reordered arguments
    }

    @Test
    public void testGetNotesEmpty() { 
        Measure measure = new Measure(4, 4, 120);
        List<Note> notes = measure.getNotes();
        assertNotNull("Notes list should not be null", notes); // Reordered arguments
        assertTrue("Notes list should be empty initially", notes.isEmpty()); // Reordered arguments
    }

    @Test
    public void testGetTimeSignatureTop() { 
        Measure measure = new Measure(4, 4, 120);
        assertEquals("Time signature top should be correct", 4, measure.getTimeSignatureTop()); // Reordered arguments
    }

    @Test
    public void testGetTimeSignatureBottom() { 
        Measure measure = new Measure(4, 4, 120);
        assertEquals("Time signature bottom should be correct", 4, measure.getTimeSignatureBottom()); // Reordered arguments
    }

    @Test
    public void testGetTempo() { 
        Measure measure = new Measure(4, 4, 120);
        assertEquals("Tempo should be correct", 120, measure.getTempo()); // Reordered arguments
    }
}