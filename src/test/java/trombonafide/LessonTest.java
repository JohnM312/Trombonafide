package trombonafide;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import com.model.Lesson;

import java.util.UUID;

/**
 * @author Aiden Campbell
 */
public class LessonTest {
    private Lesson lesson;

    @Before
    public void setUp() {
        lesson = new Lesson("Intro to Music", "This is the content", "Try clapping the rhythm!");
    }

    @Test
    public void testConstructorSetsFieldsCorrectly() {
        assertEquals("Intro to Music", lesson.getTitle());
        assertEquals("This is the content", lesson.getContent());
        assertEquals("Try clapping the rhythm!", lesson.getHint());
        assertNotNull(lesson.getUuid());
    }

    @Test
    public void testToStringOutput() {
        String output = lesson.toString();
        assertTrue(output.contains("Intro to Music"));
        assertTrue(output.contains("content=21 chars")); //error on this test
        assertTrue(output.contains("clapping the rhythm".substring(0, 20)));
    }

    @Test
    public void testDefaultConstructorGeneratesUUID() {
        Lesson blank = new Lesson();
        assertNotNull(blank.getUuid());
    }

    @Test
    public void testNullContentDoesNotCrashToString() {
        Lesson blank = new Lesson("Title", null, null);
        try {
            blank.toString();
        } catch (Exception e) {
            fail("toString() should not throw an exception for null content or hint");
        }
    }

    @Test
    public void testSettersAndGetters() {
        UUID uuid = UUID.randomUUID();
        lesson.setUuid(uuid);
        lesson.setTitle("New Title");
        lesson.setContent("New content");
        lesson.setHint("New hint");

        assertEquals(uuid, lesson.getUuid());
        assertEquals("New Title", lesson.getTitle());
        assertEquals("New content", lesson.getContent());
        assertEquals("New hint", lesson.getHint());
    }
}
