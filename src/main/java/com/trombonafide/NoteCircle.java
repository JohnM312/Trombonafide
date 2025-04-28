package com.trombonafide;

import javafx.scene.shape.Circle;

public class NoteCircle {
    private Circle circle;
    private Note note;

    public NoteCircle(Circle circle, Note note) {
        this.circle = circle;
        this.note = note;
    }

    public Circle getCircle() {
        return circle;
    }

    public Note getNote() {
        return note;
    }
}
