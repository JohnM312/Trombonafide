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

    @Test
    public void testSingletonInstanceIsSame() {
        LessonList list1 = LessonList.getInstance();
        LessonList list2 = LessonList.getInstance();
        assertSame(list1, list2);
    }

    @Test
    public void testAddLessonIncreasesListSize() {
        Lesson lesson = new Lesson("Title", "Content", "Hint");
        lessonList.addLesson(lesson);
        assertEquals(1, lessonList.getLessons().size());
        assertEquals(lesson, lessonList.getLessons().get(0));
    }

    @Test
    public void testRemoveLessonDecreasesListSize() {
        Lesson lesson = new Lesson("Title", "Content", "Hint");
        lessonList.addLesson(lesson);
        lessonList.removeLesson(lesson);
        assertEquals(0, lessonList.getLessons().size());
    }

    @Test
    public void testRemoveNonExistentLessonDoesNothing() {
        Lesson lesson1 = new Lesson("Real", "Real Content", "Real Hint");
        Lesson lesson2 = new Lesson("Fake", "Fake Content", "Fake Hint");
        lessonList.addLesson(lesson1);
        lessonList.removeLesson(lesson2);
        assertEquals(1, lessonList.getLessons().size());
    }

    @Test
    public void testToStringIncludesLessonTitle() {
        Lesson lesson = new Lesson("TestTitle", "Content", "Hint");
        lessonList.addLesson(lesson);
        assertTrue(lessonList.toString().contains("TestTitle"));
    }
}
