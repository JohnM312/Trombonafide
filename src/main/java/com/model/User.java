package com.model;

import java.io.Serializable;
import java.util.UUID;

/**
 * Represents a user of the app.
 * Stores login info, personal details, and their progress.
 */
public class User implements Serializable {
    private UUID uuid;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private UserProgress progress;

    /**
     * Default constructor that auto-generates a UUID
     * and sets up an empty progress tracker.
     */
    public User() {
        this.uuid = UUID.randomUUID();
        this.progress = new UserProgress();
    }

    /**
     * Creates a user with just login and contact info.
     */
    public User(String username, String password, String email) {
        this();
        this.username = username;
        this.password = password;
        this.email = email;
    }

    /**
     * Creates a user with full details including name.
     */
    public User(String firstName, String lastName, String username,
            String password, String email) {
        this();
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    // Basic getters and setters
    public UUID getUuid() { return uuid; }
    public void setUuid(UUID uuid) { this.uuid = uuid; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public UserProgress getProgress() { return progress; }
    public void setProgress(UserProgress progress) { this.progress = progress; }

    /**
     * Gives a readable summary of the user.
     * Doesn't include the password for privacy.
     */
    @Override
    public String toString() {
        return "User{" +
                "uuid=" + uuid +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", totalPoints=" + (progress != null ? progress.getTotalPoints() : "N/A") +
                '}';
    }
}
