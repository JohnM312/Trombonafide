package com.model;

import java.io.Serializable;
import java.util.UUID;
/**
 * test if the branch was right
 */
public class Song implements Serializable {
    private UUID uuid;
    private String title;
    private Artist artist;
    private String genre;
    private String notes;
    private int difficultyRate;
    private double melodyStructure;

    public Song() {
        this.uuid = UUID.randomUUID();
    }

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

    public Song(String title, String[] notes) {
        this.uuid = UUID.randomUUID();
        this.title = title;
        this.artist = new Artist("Unknown", "Unknown", "Unknown");
        this.genre = "Unspecified";
        this.difficultyRate = 1;
        this.melodyStructure = 1.0;
        this.notes = String.join(", ", notes);
    }

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

    public int getDifficultyRate() {return difficultyRate; }
    
    public void setDifficultyRate(int difficultyRate)
    {this.difficultyRate = difficultyRate; }
    
    public double getMelodyStructure() {return melodyStructure;}
    
    public void setMelodyStructure(double melodyStructure)
    {this.melodyStructure = melodyStructure;}
    

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