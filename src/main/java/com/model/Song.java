package com.model;

import java.io.Serializable;
import java.util.UUID;

/**
 * Represents a song with various details like artist, genre, difficulty, and more.
 */
public class Song implements Serializable {
    private UUID uuid;
    private String title;
    private Artist artist;
    private String genre;
    private String notes;
    private int difficultyRate;
    private double melodyStructure;

    /**
     * Default constructor.
     * Automatically generates a unique ID.
     */
    public Song() {
        this.uuid = UUID.randomUUID();
    }

    /**
     * Full constructor for setting all the fields.
     *
     * @param title Title of the song
     * @param artist Artist who made the song
     * @param genre Genre of the song
     * @param notes Notes related to the song
     * @param difficultyRate A number rating how hard the song is
     * @param melodyStructure A value representing the structure of the melody
     */
    public Song(String title, Artist artist, String genre, String notes,
                int difficultyRate, double melodyStructure) {
        this.uuid = UUID.randomUUID();
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.notes = notes;
        this.difficultyRate = difficultyRate;
        this.melodyStructure = melodyStructure;
    }

    /**
     * Simplified constructor that takes a title and an array of notes.
     * Fills in default values for everything else.
     *
     * @param title Title of the song
     * @param notes Array of musical notes
     */
    public Song(String title, String[] notes) {
        this.uuid = UUID.randomUUID();
        this.title = title;
        this.artist = new Artist("Unknown", "Unknown", "Unknown");
        this.genre = "Unspecified";
        this.difficultyRate = 1;
        this.melodyStructure = 1.0;
        this.notes = String.join(", ", notes);
    }

    // Getters and Setters
    public UUID getUuid() { return uuid; }
    public void setUuid(UUID uuid) { this.uuid = uuid; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public Artist getArtist() { return artist; }
    public void setArtist(Artist artist) { this.artist = artist; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public int getDifficultyRate() { return difficultyRate; }
    public void setDifficultyRate(int difficultyRate) { this.difficultyRate = difficultyRate; }

    public double getMelodyStructure() { return melodyStructure; }
    public void setMelodyStructure(double melodyStructure) { this.melodyStructure = melodyStructure; }

    /**
     * Returns a quick summary of the song and its key details.
     *
     * @return formatted string of song details
     */
    @Override
    public String toString() {
        return "Song{" +
                "uuid=" + uuid +
                ", title='" + title + '\'' +
                ", artist='" + artist + '\'' +
                ", genre='" + genre + '\'' +
                ", notes='" + notes + '\'' +
                '}';
    }
}
