package com.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Stores and manages a list of lessons.
 * Uses a singleton pattern so that only one object exists
 */

public class LessonList {
    // List of lesson objects
    private List<Lesson> lessons;
    private static LessonList instance;

    /**
     * Starts up the lessonList.
     * Private constructors that prohibts object creation.
     */

    private LessonList() {
        this.lessons = new ArrayList<>();
    }

    /**
     * Retrieves the insatnce of lessonList.
     * 
     * @return the lessonList instance
     */
    public static LessonList getInstance() {
        if (instance == null) {
            instance = new LessonList();
        }
        return instance;
    }

    /**
     * Returns the list of all lessons.
     * 
     * @return a list of lesson objects
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
     * @param lesson the lesson needed to be removed
     */
    public void removeLesson(Lesson lesson) {
        lessons.remove(lesson);
    }

    /**
     * Returns all lessons in a list using a string.
     * 
     * @return string of the lessons list
     */
    @Override
    public String toString() {
        return "LessonList{" +
                "lessons=" + lessons +
                '}';
    }
}