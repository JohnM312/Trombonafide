package trombonafide;

import org.junit.Test;
import static org.junit.Assert.*;

import com.trombonafide.SheetMusic;
import com.trombonafide.KeySignature;
import com.trombonafide.TimeSignature;
import com.trombonafide.Measure;
import com.trombonafide.Note;

import java.util.ArrayList;
import java.util.List;

/**
 * @author John Mai
 */
public class SheetMusicTest {

    @Test
    public void testSheetMusicCreation() {
        KeySignature keySignature = new KeySignature("C", "major");
        TimeSignature timeSignature = new TimeSignature(4, 4);
        SheetMusic sheetMusic = new SheetMusic("Test Song", "Test Composer", 120, keySignature, timeSignature);

        assertNotNull("SheetMusic object should not be null", sheetMusic);
    }

    @Test
    public void testAdjustTempo() {
        KeySignature keySignature = new KeySignature("C", "major");
        TimeSignature timeSignature = new TimeSignature(4, 4);
        SheetMusic sheetMusic = new SheetMusic("Test Song", "Test Composer", 120, keySignature, timeSignature);

        // Simulate a change in tempo
        sheetMusic.adjustTempo(140);

        //Assert that the tempo has changed.
    }

    @Test
    public void testTransposeKey() {
        KeySignature initialKeySignature = new KeySignature("C", "major");
        TimeSignature timeSignature = new TimeSignature(4, 4);
        SheetMusic sheetMusic = new SheetMusic("Test Song", "Test Composer", 120, initialKeySignature, timeSignature);

        //Verify the notes have transposed successfully

    }

    @Test
    public void testRemoveNotation() {
        KeySignature keySignature = new KeySignature("C", "major");
        TimeSignature timeSignature = new TimeSignature(4, 4);
        SheetMusic sheetMusic = new SheetMusic("Test Song", "Test Composer", 120, keySignature, timeSignature);
    }

     @Test
    public void testSheetMusicCreationNullArguments() {
        try {
        SheetMusic sheetMusic = new SheetMusic(null, null, 0, null, null);
        }
        catch (IllegalArgumentException e){
            assertEquals("Error Message should be correct", "Arguments cannot be empty", e.getMessage());
        }
    }
}