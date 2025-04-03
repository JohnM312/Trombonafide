package trombonafide;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import com.model.User;
import com.model.Lesson;
import com.model.Song;
import com.trombonafide.*;

import java.util.List;

public class MusicSystemFacadeTest {
    private MusicSystemFacade facade;

    @Before
    public void setUp() {
        facade = MusicSystemFacade.getFacadeInstance();
        // Reset state as much as possible without accessing internal lists
        facade.logout();
        facade.stopSong();
        facade.stopLesson();
    }

    @After
    public void tearDown() {
        // Clean up after each test
        facade.logout();
        facade.stopSong();
        facade.stopLesson();
    }

    // --- User Tests ---
    @Test
    public void testSingletonInstance() {
        MusicSystemFacade anotherInstance = MusicSystemFacade.getFacadeInstance();
        assertSame(facade, anotherInstance);
    }

    @Test
    public void testRegisterNewUser() {
        boolean result = facade.register("John", "Doe", "johndoe", "password", 
                                      "john@example.com", "1234567890", "student");
        assertTrue(result);
        assertNotNull(facade.getCurrentUser());
        assertEquals("johndoe", facade.getCurrentUser().getUsername());
    }

    @Test
    public void testRegisterExistingUser() {
        facade.register("John", "Doe", "johndoe", "password", 
                       "john@example.com", "1234567890", "student");
        boolean result = facade.register("John", "Doe", "johndoe", "password", 
                                       "john@example.com", "1234567890", "student");
        assertFalse(result);
    }

    @Test
    public void testLoginSuccess() {
        facade.register("John", "Doe", "johndoe", "password", 
                       "john@example.com", "1234567890", "student");
        facade.logout();
        boolean result = facade.login("johndoe", "password");
        assertTrue(result);
        assertNotNull(facade.getCurrentUser());
    }

    @Test
    public void testLoginFailure() {
        facade.register("John", "Doe", "johndoe", "password", 
                       "john@example.com", "1234567890", "student");
        facade.logout();
        
        boolean result = facade.login("johndoe", "wrongpassword");
        assertFalse(result);
        
        if (facade.getCurrentUser() != null) {
            assertNotEquals("johndoe", facade.getCurrentUser().getUsername());
        }
    }

    @Test
    public void testLogout() {
        facade.register("John", "Doe", "johndoe", "password", 
                       "john@example.com", "1234567890", "student");
        boolean result = facade.logout();
        assertTrue(result);
        assertNull(facade.getCurrentUser());
    }

    @Test
    public void testLogoutWhenNotLoggedIn() {
        boolean result = facade.logout();
        assertFalse(result);
    }

    @Test
    public void testIsUserLoggedIn() {
        facade.register("John", "Doe", "johndoe", "password", 
                       "john@example.com", "1234567890", "student");
        assertTrue(facade.isUserLoggedIn("johndoe"));
        assertFalse(facade.isUserLoggedIn("nonexistent"));
    }

    // --- Lesson Tests ---
    @Test
    public void testAddLesson() {
        // Count existing lessons first
        int initialCount = facade.getAllLessons().size();
        
        boolean result = facade.addLesson("Guitar Basics", "Learn the basics of guitar", "Start with simple chords");
        assertTrue(result);
        assertEquals(initialCount + 1, facade.getAllLessons().size());
    }

    @Test
    public void testGetLessonByTitle() {
        facade.addLesson("Guitar Basics", "Learn the basics of guitar", "Start with simple chords");
        Lesson lesson = facade.getLessonByTitle("Guitar Basics");
        assertNotNull(lesson);
        assertEquals("Guitar Basics", lesson.getTitle());
    }

    @Test
    public void testGetLessonByTitleNotFound() {
        Lesson lesson = facade.getLessonByTitle("Nonexistent Lesson");
        assertNull(lesson);
    }

    @Test
    public void testStartLesson() {
        facade.addLesson("Guitar Basics", "Learn the basics of guitar", "Start with simple chords");
        boolean result = facade.startLesson("Guitar Basics");
        assertTrue(result);
    }

    @Test
    public void testStartLessonNotFound() {
        boolean result = facade.startLesson("Nonexistent Lesson");
        assertFalse(result);
    }

    @Test
    public void testStopLesson() {
        facade.addLesson("Guitar Basics", "Learn the basics of guitar", "Start with simple chords");
        facade.startLesson("Guitar Basics");
        boolean result = facade.stopLesson();
        assertTrue(result);
    }

    @Test
    public void testStopLessonWhenNoneActive() {
        boolean result = facade.stopLesson();
        assertFalse(result);
    }

    @Test
    public void testGetAllLessons() {
        int initialCount = facade.getAllLessons().size();
        facade.addLesson("Lesson 1", "Content 1", "Hint 1");
        facade.addLesson("Lesson 2", "Content 2", "Hint 2");
        List<Lesson> lessons = facade.getAllLessons();
        assertEquals(initialCount + 2, lessons.size());
    }

    // --- Song Tests ---
    @Test
    public void testAddSong() {
        // Count existing songs first
        int initialCount = facade.getAllSongs().size();
        
        String[] notes = {"C", "D", "E"};
        boolean result = facade.addSong("Simple Song", notes);
        assertTrue(result);
        assertEquals(initialCount + 1, facade.getAllSongs().size());
    }

    @Test
    public void testGetSongByTitle() {
        String[] notes = {"C", "D", "E"};
        facade.addSong("Simple Song", notes);
        Song song = facade.getSongByTitle("Simple Song");
        assertNotNull(song);
        assertEquals("Simple Song", song.getTitle());
    }

    @Test
    public void testGetSongByTitleNotFound() {
        Song song = facade.getSongByTitle("Nonexistent Song");
        assertNull(song);
    }

    @Test
    public void testPlaySong() {
        String[] notes = {"C", "D", "E"};
        facade.addSong("Simple Song", notes);
        boolean result = facade.playSong("Simple Song");
        assertTrue(result);
    }

    @Test
    public void testPlaySongNotFound() {
        boolean result = facade.playSong("Nonexistent Song");
        assertFalse(result);
    }

    @Test
    public void testStopSong() {
        String[] notes = {"C", "D", "E"};
        facade.addSong("Simple Song", notes);
        facade.playSong("Simple Song");
        boolean result = facade.stopSong();
        assertTrue(result);
    }

    @Test
    public void testStopSongWhenNonePlaying() {
        boolean result = facade.stopSong();
        assertFalse(result);
    }

    @Test
    public void testGetAllSongs() {
        int initialCount = facade.getAllSongs().size();
        String[] notes1 = {"C", "D", "E"};
        String[] notes2 = {"F", "G", "A"};
        facade.addSong("Song 1", notes1);
        facade.addSong("Song 2", notes2);
        List<Song> songs = facade.getAllSongs();
        assertEquals(initialCount + 2, songs.size());
    }
}