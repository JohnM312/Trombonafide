package trombonafide;

import static org.junit.Assert.*;

import com.model.Artist;
import com.model.Song;

import org.junit.Test;

import java.util.UUID;

public class SongTest {


    // Tests for Full Constructor

    @Test
    public void testFullConstructorSetsTitle() {
        Song song = new Song("My Song", new Artist("A", "B", "C"), "Jazz", "Nice notes", 3, 2.5);
        assertEquals("My Song", song.getTitle());
    }

    @Test
    public void testFullConstructorSetsArtist() {
        Artist artist = new Artist("A", "B", "C");
        Song song = new Song("My Song", artist, "Jazz", "Nice notes", 3, 2.5);
        assertEquals(artist, song.getArtist());
    }

    @Test
    public void testFullConstructorSetsGenre() {
        Song song = new Song("My Song", new Artist("A", "B", "C"), "Rock", "notes", 2, 1.0);
        assertEquals("Rock", song.getGenre());
    }

    @Test
    public void testFullConstructorSetsDifficultyRate() {
        Song song = new Song("Test", new Artist("A", "B", "C"), "Pop", "notes", 5, 4.0);
        assertEquals(5, song.getDifficultyRate());
    }

    @Test
    public void testFullConstructorGeneratesUniqueUUID() {
        Song s1 = new Song("One", new Artist("A", "B", "C"), "Genre", "Notes", 1, 1.0);
        Song s2 = new Song("Two", new Artist("X", "Y", "Z"), "Other", "Data", 2, 2.0);
        assertNotEquals(s1.getUuid(), s2.getUuid());
    }

    // Tests for Simplified Constructor

    @Test
    public void testSimplifiedConstructorSetsDefaultArtist() {
        Song song = new Song("Simple", new String[]{"C", "D"});
        assertEquals("Unknown", song.getArtist().getFirstName());
    }

    @Test
    public void testSimplifiedConstructorJoinsNotes() {
        Song song = new Song("Simple", new String[]{"C", "D", "E"});
        assertEquals("C, D, E", song.getNotes());
    }

    @Test
    public void testSimplifiedConstructorDefaultsDifficulty() {
        Song song = new Song("Easy", new String[]{"A", "B"});
        assertEquals(1, song.getDifficultyRate());
    }

    @Test
    public void testSimplifiedConstructorDefaultsMelodyStructure() {
        Song song = new Song("Simple", new String[]{"A"});
        assertEquals(1.0, song.getMelodyStructure(), 0.001);
    }

    @Test
    public void testSimplifiedConstructorSetsTitle() {
        Song song = new Song("Quick", new String[]{"G"});
        assertEquals("Quick", song.getTitle());
    }

    // Tests for toString()

    @Test
    public void testToStringIncludesTitle() {
        Song song = new Song("Cool Song", new Artist("A", "B", "C"), "Genre", "notes", 2, 3.0);
        assertTrue(song.toString().contains("Cool Song"));
    }

    @Test
    public void testToStringIncludesUUID() {
        Song song = new Song("Song", new Artist("A", "B", "C"), "Genre", "notes", 2, 3.0);
        assertTrue(song.toString().contains(song.getUuid().toString()));
    }

    @Test
    public void testToStringHandlesNullUserId() {
        Song song = new Song("Song", new Artist("A", "B", "C"), "Genre", "notes", 2, 3.0);
        try {
            song.toString();
        } catch (Exception e) {
            fail("toString should not throw with null userId");
        }
    }

    @Test
    public void testToStringIncludesUserIdIfSet() {
        Song song = new Song("WithUser", new Artist("A", "B", "C"), "Genre", "notes", 2, 3.0);
        song.setUserId("user123");
        assertTrue(song.toString().contains("user123"));
    }

    @Test
    public void testToStringIncludesArtist() {
        Artist artist = new Artist("John", "Doe", "Jazz");
        Song song = new Song("Song", artist, "Jazz", "notes", 3, 2.5);
        assertTrue(song.toString().contains("John"));
    }
}
