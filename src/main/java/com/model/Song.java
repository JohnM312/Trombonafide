package com.model;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import com.trombonafide.Note;

/**
 * Represents a song with various details.
 */
public class Song implements Serializable {
    private UUID uuid;
    private String title;
    private Artist artist;
    private String genre;
    private List<Note> notes;
    private int difficultyRate;
    private double melodyStructure;
    private String userId; // Added userId

    /**
     * Default constructor.
     */
    public Song() {
        this.uuid = UUID.randomUUID();
    }

    /**
     * Full constructor for setting all the fields.
     */
    public Song(String title, Artist artist, String genre, List<Note> notes,
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
     * NEW constructor added — title, artist, genre, **notes (String)**, difficultyRate, melodyStructure
     */
    public Song(String title, Artist artist, String genre, String noteString,
                int difficultyRate, double melodyStructure) {
        this.uuid = UUID.randomUUID();
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.notes = List.of(new Note(noteString, 4, 1.0, 100)); // quick basic conversion
        this.difficultyRate = difficultyRate;
        this.melodyStructure = melodyStructure;
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

    public List<Note> getNotes() { return notes; }
    public void setNotes(List<Note> notes) { this.notes = notes; }

    public int getDifficultyRate() { return difficultyRate; }
    public void setDifficultyRate(int difficultyRate) { this.difficultyRate = difficultyRate; }

    public double getMelodyStructure() { return melodyStructure; }
    public void setMelodyStructure(double melodyStructure) { this.melodyStructure = melodyStructure; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    /**
     * Returns a quick summary of the song and its key details.
     */
    @Override
    public String toString() {
        return "Song{" +
                "uuid=" + uuid +
                ", title='" + title + '\'' +
                ", artist=" + artist +
                ", genre='" + genre + '\'' +
                ", notes=" + notes +
                ", userId='" + userId + '\'' +
                '}';
    }
}
