package com.trombonafide;

import javafx.scene.shape.Circle;

/**
 * A simple link between a visual circle and a musical note.
 * <p>
 * This class connects a JavaFX Circle shape to a Note so they can be managed together.
 * </p>
 */
public class NoteCircle {

    /**
     * The circle displayed on the screen.
     */
    private Circle circle;

    /**
     * The note connected to the circle.
     */
    private Note note;

    /**
     * Creates a new NoteCircle that ties a circle to a note.
     *
     * @param circle The visual circle.
     * @param note   The note linked to the circle.
     */
    public NoteCircle(Circle circle, Note note) {
        this.circle = circle;
        this.note = note;
    }

    /**
     * Gets the circle part of this NoteCircle.
     *
     * @return The circle that's shown on the screen.
     */
    public Circle getCircle() {
        return circle;
    }

    /**
     * Gets the note linked to this NoteCircle.
     *
     * @return The note tied to the circle.
     */
    public Note getNote() {
        return note;
    }
}
