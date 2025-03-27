package com.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages a list of songs using a singleton pattern so there’s only one instance.
 */
public class SongList {
    private List<Song> songs;
    private static SongList instance;

    /**
     * Private constructor so it can’t be created from outside the class.
     * Initializes the song list.
     */
    private SongList() {
        this.songs = new ArrayList<>();
    }

    /**
     * Returns the shared instance of SongList.
     * Creates it if it doesn’t already exist.
     */
    public static SongList getInstance() {
        if (instance == null) {
            instance = new SongList();
        }
        return instance;
    }

    /**
     * Returns the full list of songs.
     */
    public List<Song> getSongs() {
        return songs;
    }

    /**
     * Adds a new song to the list.
     *
     * @param song the Song to add
     */
    public void addSong(Song song) {
        songs.add(song);
    }

    /**
     * Removes a song from the list.
     *
     * @param song the Song to remove
     */
    public void removeSong(Song song) {
        songs.remove(song);
    }

    /**
     * Finds and returns a song by its title, ignoring case.
     *
     * @param title the title to search for
     * @return the first matching Song or null if nothing is found
     */
    public Song getSongByTitle(String title) {
        return songs.stream()
            .filter(s -> s.getTitle().equalsIgnoreCase(title))
            .findFirst()
            .orElse(null);
    }

    /**
     * Gives a string summary of all the songs in the list.
     */
    @Override
    public String toString() {
        return "SongList{" +
                "songs=" + songs +
                '}';
    }
}
