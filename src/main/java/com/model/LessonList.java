package com.model;

import java.util.ArrayList;
import java.util.List;

public class LessonList {
    private List<Lesson> lessons;
    private static LessonList instance;

    private LessonList() {
        this.lessons = new ArrayList<>();
    }

    public static LessonList getInstance() {
        if (instance == null) {
            instance = new LessonList();
        }
        return instance;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void addLesson(Lesson lesson) {
        lessons.add(lesson);
    }

    public void removeLesson(Lesson lesson) {
        lessons.remove(lesson);
    }

    @Override
    public String toString() {
        return "LessonList{" +
                "lessons=" + lessons +
                '}';
    }
}