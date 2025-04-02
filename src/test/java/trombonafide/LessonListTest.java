package trombonafide;

import static org.junit.Assert.*;

import com.model.Lesson;
import com.model.LessonList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LessonListTest {
    private LessonList lessonList;

    @Before
    public void setUp() {
        lessonList = LessonList.getInstance();
        lessonList.getLessons().clear();
    }

    @After
    public void tearDown() {
        lessonList.getLessons().clear();
    }

    // Tests for addLesson()

    @Test
    public void testAddSingleLesson() {
        Lesson lesson = new Lesson("Title1", "Content1", "Hint1");
        lessonList.addLesson(lesson);
        assertEquals(1, lessonList.getLessons().size());
    }

    @Test
    public void testAddMultipleLessons() {
        lessonList.addLesson(new Lesson("Title1", "Content1", "Hint1"));
        lessonList.addLesson(new Lesson("Title2", "Content2", "Hint2"));
        assertEquals(2, lessonList.getLessons().size());
    }

    @Test
    public void testAddSameLessonTwice() {
        Lesson lesson = new Lesson("Repeat", "Same", "Same");
        lessonList.addLesson(lesson);
        lessonList.addLesson(lesson);
        assertEquals(2, lessonList.getLessons().size()); // Allowed by default
    }

    @Test
    public void testAddLessonWithEmptyFields() {
        Lesson lesson = new Lesson("", "", "");
        lessonList.addLesson(lesson);
        assertEquals(1, lessonList.getLessons().size());
        assertEquals("", lessonList.getLessons().get(0).getTitle());
    }

    @Test
    public void testAddNullLesson() {
        try {
            lessonList.addLesson(null);
            assertEquals(1, lessonList.getLessons().size()); // Might be allowed by default
        } catch (Exception e) {
            fail("Adding null should not throw an exception unless explicitly handled.");
        }
    }

    
    // Tests for removeLesson()

    @Test
    public void testRemoveExistingLesson() {
        Lesson lesson = new Lesson("Title", "Content", "Hint");
        lessonList.addLesson(lesson);
        lessonList.removeLesson(lesson);
        assertEquals(0, lessonList.getLessons().size());
    }

    @Test
    public void testRemoveNonExistentLesson() {
        Lesson lesson1 = new Lesson("Real", "Real", "Real");
        Lesson lesson2 = new Lesson("Fake", "Fake", "Fake");
        lessonList.addLesson(lesson1);
        lessonList.removeLesson(lesson2);
        assertEquals(1, lessonList.getLessons().size());
    }

    @Test
    public void testRemoveFromEmptyList() {
        Lesson lesson = new Lesson("Nothing", "No", "Nope");
        lessonList.removeLesson(lesson); // Should not break
        assertEquals(0, lessonList.getLessons().size());
    }

    @Test
    public void testRemoveSameLessonTwice() {
        Lesson lesson = new Lesson("Double", "Try", "Again");
        lessonList.addLesson(lesson);
        lessonList.removeLesson(lesson);
        lessonList.removeLesson(lesson); // Second removal should do nothing
        assertEquals(0, lessonList.getLessons().size());
    }

    @Test
    public void testRemoveNullLesson() {
        try {
            lessonList.removeLesson(null);
            assertEquals(0, lessonList.getLessons().size());
        } catch (Exception e) {
            fail("Removing null should not throw exception unless explicitly checked.");
        }
    }

    // Tests for getLessons()

    @Test
    public void testGetLessonsInitiallyEmpty() {
        assertTrue(lessonList.getLessons().isEmpty());
    }

    @Test
    public void testGetLessonsAfterAdding() {
        Lesson lesson = new Lesson("Title", "Content", "Hint");
        lessonList.addLesson(lesson);
        assertEquals(lesson, lessonList.getLessons().get(0));
    }

    @Test
    public void testGetLessonsReturnsSameListReference() {
        lessonList.addLesson(new Lesson("Ref", "Test", "List"));
        lessonList.getLessons().clear(); // Clearing the returned list
        // should not affect the original list
        assertEquals(0, lessonList.getLessons().size());
    }

    @Test
    public void testGetLessonsSizeReflectsAdditions() {
        lessonList.addLesson(new Lesson("One", "Two", "Three"));
        lessonList.addLesson(new Lesson("Four", "Five", "Six"));
        assertEquals(2, lessonList.getLessons().size());
    }

    @Test
    public void testGetLessonsAfterRemove() {
        Lesson lesson = new Lesson("Keep", "Track", "Me");
        lessonList.addLesson(lesson);
        lessonList.removeLesson(lesson);
        assertTrue(lessonList.getLessons().isEmpty());
    }
}
