package com.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages all the lessons in the app.
 * Uses a singleton pattern so there's only one shared list.
 */
public class LessonList {
    private List<Lesson> lessons;
    private static LessonList instance;

    /**
     * Private constructor to make sure other classes can't make new LessonLists.
     * Starts off with an empty list of lessons.
     */
    private LessonList() {
        this.lessons = new ArrayList<>();
    }

    /**
     * Gets the one and only instance of LessonList.
     * If it doesn’t exist yet, it creates one.
     *
     * @return the shared LessonList instance
     */
    public static LessonList getInstance() {
        if (instance == null) {
            instance = new LessonList();
        }
        return instance;
    }

    /**
     * Gets the full list of lessons.
     *
     * @return a list of Lesson objects
     */
    public List<Lesson> getLessons() {
        return lessons;
    }

    /**
     * Adds a new lesson to the list.
     *
     * @param lesson the lesson to add
     */
    public void addLesson(Lesson lesson) {
        lessons.add(lesson);
    }

    /**
     * Removes a lesson from the list.
     *
     * @param lesson the lesson to remove
     */
    public void removeLesson(Lesson lesson) {
        lessons.remove(lesson);
    }

    /**
     * Returns a string showing all lessons in the list.
     *
     * @return a summary of the LessonList contents
     */
    @Override
    public String toString() {
        return "LessonList{" +
                "lessons=" + lessons +
                '}';
    }
}
