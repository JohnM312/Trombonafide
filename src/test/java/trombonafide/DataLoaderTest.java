package trombonafide;

import static org.junit.Assert.*;

import com.model.*;
import com.trombonafide.util.Constants;
import com.trombonafide.util.DataWriter;
import com.trombonafide.util.DataLoader;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Unit tests for the {@link DataLoader} class.
 * These tests validate the functionality of loading Users, Songs, and Lessons
 * from JSON files, including tests for valid data, corrupted files, empty files,
 * missing files, and partial/null data entries.
 */
public class DataLoaderTest {
    private List<User> userList;
    private List<Song> songList;
    private List<Lesson> lessonList;

    /**
     * Initializes test data lists and ensures JSON files are cleared before each test.
     */
    @Before
    public void setUp() {
        userList = new ArrayList<>();
        songList = new ArrayList<>();
        lessonList = new ArrayList<>();

        new File(Constants.USER_FILE).delete();
        new File(Constants.SONG_FILE).delete();
        new File(Constants.LESSON_FILE).delete();
    }

    // Tests for loadUsers()
    /**
     * Tests that valid user data is correctly deserialized from the JSON file.
     */
    @Test
    public void testLoadUsersWithValidData() {
        userList.add(new User("Amy", "Smith", "asmith", "password", "amy@example.com"));
        DataWriter.saveUsers(userList);

        List<User> users = DataLoader.loadUsers();
        assertEquals(1, users.size());
        assertEquals("asmith", users.get(0).getUsername());
        assertEquals("Amy", users.get(0).getFirstName());
        assertEquals("Smith", users.get(0).getLastName());
    }

    /**
     * Tests that loading from a non-existent user file returns an empty list.
     */
    @Test
    public void testLoadUsersFromMissingFile() {
        List<User> users = DataLoader.loadUsers();
        assertNotNull(users);
        assertEquals(0, users.size());
    }

    /**
     * Tests that loading from an empty user file returns an empty list.
     */
    @Test
    public void testLoadUsersFromEmptyFile() throws IOException {
        new FileWriter(Constants.USER_FILE).close();
        List<User> users = DataLoader.loadUsers();
        assertNotNull(users);
        assertEquals(0, users.size());
    }

    /**
     * Tests that a user with only partial data can still be loaded.
     */
    @Test
    public void testLoadUsersWithPartialFields() throws IOException {
        FileWriter writer = new FileWriter(Constants.USER_FILE);
        writer.write("[{\"username\":\"testuser\"}]");
        writer.close();

        List<User> users = DataLoader.loadUsers();
        assertEquals(1, users.size());
        assertEquals("testuser", users.get(0).getUsername());
    }

    // Tests for loadSongs()

    /**
     * Tests that a fully valid song is correctly loaded from JSON.
     */
    @Test
    public void testLoadSongsWithValidData() {
        songList.add(new Song("Cool Tune", new Artist("John", "Doe", "Jazz"), "Jazz", "C, D", 3, 2.5));
        DataWriter.saveSongs(songList);

        List<Song> songs = DataLoader.loadSongs();
        assertEquals(1, songs.size());
        assertEquals("Cool Tune", songs.get(0).getTitle());
        assertEquals("Jazz", songs.get(0).getGenre());
        assertEquals("John", songs.get(0).getArtist().getFirstName());
    }

    /**
     * Tests that loading from a missing song file returns an empty list.
     */
    @Test
    public void testLoadSongsFromMissingFile() {
        List<Song> songs = DataLoader.loadSongs();
        assertNotNull(songs);
        assertEquals(0, songs.size());
    }

    /**
     * Tests that loading from an empty song file returns an empty list.
     */
    @Test
    public void testLoadSongsFromEmptyFile() throws IOException {
        new FileWriter(Constants.SONG_FILE).close();
        List<Song> songs = DataLoader.loadSongs();
        assertNotNull(songs);
        assertEquals(0, songs.size());
    }


    /**
     * Tests that a song with a null artist field can still be loaded.
     */
    @Test
    public void testLoadSongsWithNullArtist() throws IOException {
        FileWriter writer = new FileWriter(Constants.SONG_FILE);
        writer.write("[{\"title\":\"No Artist Song\",\"artist\":null,\"genre\":\"Jazz\",\"notes\":\"A, B\",\"difficultyRate\":2,\"melodyStructure\":1.0}]");
        writer.close();

        List<Song> songs = DataLoader.loadSongs();
        assertEquals(1, songs.size());
        assertEquals("No Artist Song", songs.get(0).getTitle());
        assertNull(songs.get(0).getArtist());
    }

    // Tests for loadLessons()

    /**
     * Tests that a complete and valid lesson is correctly loaded from JSON.
     */
    @Test
    public void testLoadLessonsWithValidData() {
        lessonList.add(new Lesson("Lesson 1", "Content A", "Hint A"));
        DataWriter.saveLessons(lessonList);

        List<Lesson> lessons = DataLoader.loadLessons();
        assertEquals(1, lessons.size());
        assertEquals("Lesson 1", lessons.get(0).getTitle());
        assertEquals("Content A", lessons.get(0).getContent());
        assertEquals("Hint A", lessons.get(0).getHint());
    }

    /**
     * Tests that loading from a missing lesson file returns an empty list.
     */
    @Test
    public void testLoadLessonsFromMissingFile() {
        List<Lesson> lessons = DataLoader.loadLessons();
        assertNotNull(lessons);
        assertEquals(0, lessons.size());
    }

    /**
     * Tests that loading from an empty lesson file does not throw and returns an empty list.
     */
    @Test
    public void testLoadLessonsFromEmptyFile() throws IOException {
        new FileWriter(Constants.LESSON_FILE).close();
        List<Lesson> lessons = DataLoader.loadLessons();
        assertNotNull(lessons);
        assertEquals(0, lessons.size());
    }

    /**
     * Tests that a lesson with all null fields can be loaded without errors.
     */
    @Test
    public void testLoadLessonsWithNullFields() throws IOException {
        FileWriter writer = new FileWriter(Constants.LESSON_FILE);
        writer.write("[{\"title\":null,\"content\":null,\"hint\":null}]");
        writer.close();

        List<Lesson> lessons = DataLoader.loadLessons();
        assertEquals(1, lessons.size());
        assertNull(lessons.get(0).getTitle());
        assertNull(lessons.get(0).getContent());
    }
}
