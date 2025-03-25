package com.model;

import java.io.Serializable;
import java.util.UUID;

public class Song implements Serializable {
    private UUID uuid;
    private String title;
    private String artist;
    private String genre;
    private String notes;

    public Song() {
        this.uuid = UUID.randomUUID();
    }

    public Song(String title, String artist, String genre, String notes) {
        this.uuid = UUID.randomUUID();
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.notes = notes;
    }

    public Song(String title, String[] notes) {
        this(title, "Unknown", "Unspecified", String.join(" ", notes));
    }

    public UUID getUuid() { return uuid; }
    public void setUuid(UUID uuid) { this.uuid = uuid; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getArtist() { return artist; }
    public void setArtist(String artist) { this.artist = artist; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

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