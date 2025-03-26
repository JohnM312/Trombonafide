package com.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserProgress implements Serializable {
    private List<UUID> completedLessons;
    private int totalPoints;

    public UserProgress() {
        this.completedLessons = new ArrayList<>();
        this.totalPoints = 0;
    }
    
    public List<UUID> getCompletedLessons() {
        return completedLessons;
    }

    public void setCompletedLessons(List<UUID> completedLessons) {
        this.completedLessons = completedLessons;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    @Override
    public String toString() {
        return "UserProgress{" +
                "completedLessons=" + completedLessons +
                ", totalPoints=" + totalPoints +
                '}';
    }
}
