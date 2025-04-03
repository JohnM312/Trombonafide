package trombonafide;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Test;

import com.model.Artist;

/**
 * Tests for the Artist class to verify data handling and formatting.
 * @author Julio Amezcua
 */
public class ArtistTest {

    /**
     * Tests the constructor properly and sets the artist's first name, last name, and genre.
     */
    @Test
    public void testArtistConstructor() {
        Artist artist = new Artist("John", "Coltrane", "Jazz");
        assertEquals("John", artist.getFirstName());
        assertEquals("Coltrane", artist.getLastName());
        assertEquals("Jazz", artist.getGenre());
    }

    /**
     * Tests the default constructor.
     */
    @Test
    public void testArtistDefaultConstructor() {
        Artist artist = new Artist();
        assertNull(artist.getFirstName());
        assertNull(artist.getLastName());
        assertNull(artist.getGenre());
    }

    /**
     * Tests the output of the toString method to make sure it returns the expected format.
     */
    @Test
    public void testToString() {
        Artist artist = new Artist("Ella", "Fitzgerald", "Swing");
        String expected = "Artist{firstName='Ella', lastName='Fitzgerald', genre='Swing'}";
        assertEquals(expected, artist.toString());
    }

    /**
     * Tests the toString output when things are null.
     */
    @Test
    public void testToStringWithNulls() {
        Artist artist = new Artist();
        String expected = "Artist{firstName='null', lastName='null', genre='null'}";
        assertEquals(expected, artist.toString());
    }

    /**
     * Tests the toString output when its empty strings.
     */
    @Test
    public void testToStringWithEmptyFields() {
        Artist artist = new Artist("", "", "");
        String expected = "Artist{firstName='', lastName='', genre=''}";
        assertEquals(expected, artist.toString());
    }
}
