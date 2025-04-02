package trombonafide;

import static org.junit.Assert.*;

import com.model.*;
import com.trombonafide.util.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Unit tests for the DataWriter class.
 * These tests verify that Users, Songs, and Lessons are correctly written
 * including edge cases such as null values, empty fields,
 * and extreme input values.
 */
public class DataWriterTest {
    private List<User> userList;
    private List<Song> songList;
    private List<Lesson> lessonList;

    /**
     * Sets up fresh test lists and clears the JSON files before each test.
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

    /**
     * Cleans up any leftover data in the JSON files after each test.
     */
    @After
    public void tearDown() {
        DataWriter.saveUsers(new ArrayList<>());
        DataWriter.saveSongs(new ArrayList<>());
        DataWriter.saveLessons(new ArrayList<>());
    }

    // Tests for saveUsers

    /**
     * Tests that saving an empty list of users results in no data being saved.
     */
    @Test
    public void testSaveZeroUsers() {
        DataWriter.saveUsers(userList);
        assertEquals(0, DataLoader.loadUsers().size());
    }

    /**
     * Tests saving a single valid user.
     */
    @Test
    public void testSaveOneUser() {
        userList.add(new User("Amy", "Smith", "test1", "password", "amy@example.com"));
        DataWriter.saveUsers(userList);
        assertEquals("test1", DataLoader.loadUsers().get(0).getUsername());
    }

    /**
     * Tests saving multiple users.
     */
    @Test
    public void testSaveMultipleUsers() {
        userList.add(new User("Amy", "S", "a", "pass1", "a@example.com"));
        userList.add(new User("Bob", "T", "b", "pass2", "b@example.com"));
        DataWriter.saveUsers(userList);
        assertEquals(2, DataLoader.loadUsers().size());
    }

    /**
     * Tests saving a user with empty fields.
     */
    @Test
    public void testSaveEmptyUser() {
        userList.add(new User("", "", "", "", ""));
        DataWriter.saveUsers(userList);
        assertEquals("", DataLoader.loadUsers().get(0).getUsername());
    }

    /**
     * Tests saving a user with a null username.
     */
    @Test
    public void testSaveNullUsernameUser() {
        userList.add(new User("Amy", "S", null, "password", "email@example.com"));
        DataWriter.saveUsers(userList);
        assertNull(DataLoader.loadUsers().get(0).getUsername());
    }

    /**
     * Tests saving a user with a very long email address.
     */
    @Test
    public void testSaveUserWithExtremelyLongEmail() {
        String longEmail = "a".repeat(5000) + "@test.com";
        userList.add(new User("First", "Last", "longuser", "password", longEmail));
        DataWriter.saveUsers(userList);

        List<User> result = DataLoader.loadUsers();
        assertEquals("longuser", result.get(0).getUsername());
        assertTrue(result.get(0).getEmail().startsWith("a"));
    }

    /**
     * Tests saving a user with a null progress object.
     */
    @Test
    public void testSaveUserWithNullProgress() {
        User user = new User("First", "Last", "nullprogress", "pass", "email");
        user.setProgress(null);
        userList.add(user);
        DataWriter.saveUsers(userList);

        List<User> result = DataLoader.loadUsers();
        assertEquals("nullprogress", result.get(0).getUsername());
    }

    // Tests for saveSongs

    /**
     * Tests saving an empty song list.
     */
    @Test
    public void testSaveZeroSongs() {
        DataWriter.saveSongs(songList);
        assertEquals(0, DataLoader.loadSongs().size());
    }

    /**
     * Tests saving a single song with valid data.
     */
    @Test
    public void testSaveOneSong() {
        songList.add(new Song("Test Song", new Artist("John", "Doe", "Jazz"), "Jazz", "C, D, E", 3, 1.2));
        DataWriter.saveSongs(songList);
        assertEquals("Test Song", DataLoader.loadSongs().get(0).getTitle());
    }

    /**
     * Tests saving multiple songs.
     */
    @Test
    public void testSaveMultipleSongs() {
        songList.add(new Song("S1", new Artist("A", "B", "C"), "Rock", "A, B", 2, 1.0));
        songList.add(new Song("S2", new Artist("X", "Y", "Z"), "Pop", "C, D", 4, 2.0));
        DataWriter.saveSongs(songList);
        assertEquals(2, DataLoader.loadSongs().size());
    }

    /**
     * Tests saving a song with empty title, genre, and artist name.
     */
    @Test
    public void testSaveSongWithEmptyFields() {
        songList.add(new Song("", new Artist("", "", ""), "", "", 0, 0.0));
        DataWriter.saveSongs(songList);
        assertEquals("", DataLoader.loadSongs().get(0).getTitle());
    }

    /**
     * Tests saving a song with a null artist object.
     */
    @Test
    public void testSaveSongWithNullArtist() {
        Song song = new Song("No Artist", null, "Jazz", "notes", 1, 1.0);
        songList.add(song);
        DataWriter.saveSongs(songList);
        assertEquals("No Artist", DataLoader.loadSongs().get(0).getTitle());
    }

    // Tests for saveLessons

    /**
     * Tests saving an empty lesson list.
     */
    @Test
    public void testSaveZeroLessons() {
        DataWriter.saveLessons(lessonList);
        assertEquals(0, DataLoader.loadLessons().size());
    }

    /**
     * Tests saving a single valid lesson.
     */
    @Test
    public void testSaveOneLesson() {
        lessonList.add(new Lesson("Intro", "Play notes", "Use metronome"));
        DataWriter.saveLessons(lessonList);
        assertEquals("Intro", DataLoader.loadLessons().get(0).getTitle());
    }

    /**
     * Tests saving multiple lessons.
     */
    @Test
    public void testSaveMultipleLessons() {
        lessonList.add(new Lesson("L1", "C D", "Hint1"));
        lessonList.add(new Lesson("L2", "E F", "Hint2"));
        DataWriter.saveLessons(lessonList);
        assertEquals(2, DataLoader.loadLessons().size());
    }

    /**
     * Tests saving a lesson where all fields are null.
     */
    @Test
    public void testSaveLessonWithNullFields() {
        lessonList.add(new Lesson(null, null, null));
        DataWriter.saveLessons(lessonList);
        assertNull(DataLoader.loadLessons().get(0).getTitle());
    }

    /**
     * Tests saving duplicate lessons (same object added twice).
     */
    @Test
    public void testSaveDuplicateLessons() {
        Lesson l1 = new Lesson("Dup", "Same", "Hint");
        lessonList.add(l1);
        lessonList.add(l1);
        DataWriter.saveLessons(lessonList);
        assertEquals(2, DataLoader.loadLessons().size());
    }
}
