package com.model;

import java.io.Serializable;
import java.util.UUID;

public class Lesson implements Serializable {
    private UUID uuid;
    private String lessonName;
    private String description;

    public Lesson() {
        this.uuid = UUID.randomUUID();
    }

    public Lesson(String lessonName, String description) {
        this.uuid = UUID.randomUUID();
        this.lessonName = lessonName;
        this.description = description;
    }

    public UUID getUuid() { return uuid; }
    public void setUuid(UUID uuid) { this.uuid = uuid; }

    public String getLessonName() { return lessonName; }
    public void setLessonName(String lessonName) { this.lessonName = lessonName; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    @Override
    public String toString() {
        return "Lesson{" +
                "uuid=" + uuid +
                ", lessonName='" + lessonName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}