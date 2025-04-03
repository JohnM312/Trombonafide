package trombonafide;

import com.model.UserProgress;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

/**
 * Tests the UserProgress class for lesson tracking and point updates.
 * @author Julio Amezcua
 */
public class UserProgressTest {

    private UserProgress progress;

    /**
     * Tests start with a fresh UserProgress instance.
     */
    @Before
    public void setUp() {
        progress = new UserProgress();
    }

    /**
     * Tests that a new UserProgress starts with no points and no completed lessons.
     */
    @Test
    public void testDefaultValues() {
        assertEquals(0, progress.getTotalPoints());
        assertTrue(progress.getCompletedLessons().isEmpty());
    }

    /**
     * Tests that setting total points works correctly.
     */
    @Test
    public void testSetTotalPoints() {
        progress.setTotalPoints(150);
        assertEquals(150, progress.getTotalPoints());
    }

    /**
     * Tests that completed lesson UUIDs can be added and retrieved.
     */
    @Test
    public void testSetAndGetCompletedLessons() {
        UUID lesson1 = UUID.randomUUID();
        UUID lesson2 = UUID.randomUUID();

        List<UUID> lessons = new ArrayList<>();
        lessons.add(lesson1);
        lessons.add(lesson2);

        progress.setCompletedLessons(lessons);

        List<UUID> result = progress.getCompletedLessons();
        assertEquals(2, result.size());
        assertTrue(result.contains(lesson1));
        assertTrue(result.contains(lesson2));
    }

    /**
     * Tests that toString returns a valid summary of the progress.
     */
    @Test
    public void testToStringOutput() {
        progress.setTotalPoints(50);
        String output = progress.toString();

        assertNotNull(output);
        assertTrue(output.contains("totalPoints=50"));
    }
}

