package com.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Tracks a user's progress in the app.
 * Stores the lessons they've completed and their total points earned.
 */
public class UserProgress implements Serializable {
    private List<UUID> completedLessons;
    private int totalPoints;

    /**
     * Default constructor that starts with no completed lessons and 0 points.
     */
    public UserProgress() {
        this.completedLessons = new ArrayList<>();
        this.totalPoints = 0;
    }

    /**
     * Gets the list of completed lesson IDs.
     *
     * @return a list of UUIDs for finished lessons
     */
    public List<UUID> getCompletedLessons() {
        return completedLessons;
    }

    /**
     * Sets the list of completed lesson IDs.
     *
     * @param completedLessons list of lesson UUIDs
     */
    public void setCompletedLessons(List<UUID> completedLessons) {
        this.completedLessons = completedLessons;
    }

    /**
     * Gets the user's total points.
     *
     * @return the total point count
     */
    public int getTotalPoints() {
        return totalPoints;
    }

    /**
     * Sets the user's total points.
     *
     * @param totalPoints points to assign
     */
    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    /**
     * Returns a summary of the user's progress.
     *
     * @return string with completed lesson IDs and point count
     */
    @Override
    public String toString() {
        return "UserProgress{" +
                "completedLessons=" + completedLessons +
                ", totalPoints=" + totalPoints +
                '}';
    }
}

