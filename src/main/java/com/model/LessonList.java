package com.model;

import java.util.ArrayList;
import java.util.List;

public class LessonList {
    private List<Lesson> lessons;

    public LessonList() {
        this.lessons = new ArrayList<>();
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