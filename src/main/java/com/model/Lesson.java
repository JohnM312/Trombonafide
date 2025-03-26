package com.model;

import java.io.Serializable;
import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Represents a music lesson with content and learning hints.
 * 
 * @author Trent Petersen
 * @since 1.0
 */
@JsonPropertyOrder({"uuid", "title", "content", "hint"})
public class Lesson implements Serializable {
    private UUID uuid;
    private String lessonTitle;
    private String content;
    private String hint;    

    /**
     * Default constructor for Jackson deserialization
     */
    public Lesson() {
        this.uuid = UUID.randomUUID();
    }

    /**
     * Creates a new lesson with all fields
     * @param lessonTitle The title of the lesson
     * @param content The main instructional content
     * @param hint Helpful learning tip (optional)
     */
    public Lesson(String lessonTitle, String content, String hint) {
        this();
        this.lessonTitle = lessonTitle;
        this.content = content;
        this.hint = hint;
    }

    // JSON Property Getters
    @JsonProperty("uuid")
    public UUID getUuid() { return uuid; }

    @JsonProperty("title")
    public String getTitle() { return lessonTitle; }

    @JsonProperty("content")
    public String getContent() { return content; }

    @JsonProperty("hint")
    public String getHint() { return hint; }

    // Setters
    public void setUuid(UUID uuid) { this.uuid = uuid; }
    public void setTitle(String lessonTitle) { this.lessonTitle = lessonTitle; }
    public void setContent(String content) { this.content = content; }
    public void setHint(String hint) { this.hint = hint; }

    /**
     * Debug-friendly string representation
     */
    @Override
    public String toString() {
        return String.format(
            "Lesson[uuid=%s, title='%s', content=%d chars, hint='%s']",
            uuid.toString().substring(0, 8),
            lessonTitle,
            content != null ? content.length() : 0,
            hint != null ? hint.substring(0, Math.min(hint.length(), 20)) : ""
        );
    }
}