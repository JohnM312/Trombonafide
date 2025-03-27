package com.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains and manages the list of songs by using a singleton.
 */
public class SongList {
    private List<Song> songs;
    private static SongList instance;
    
    /**
     * Constructor that starts up the song list.
     */
    private SongList() {
        this.songs = new ArrayList<>();
    }

    /**
     * Returns a shared instance of SongList.
     * 
     * @return the songList instance
     */
    public static SongList getInstance() {
        if (instance == null) {
            instance = new SongList();
        }
        return instance;
    }

    /**
     * Returns the list of all the songs.
     * 
     * @return a list of song objects
     */
    public List<Song> getSongs() {
        return songs;
    }

    /**
     * Adds a song to the list.
     * 
     * @param song - A song object is added
     */
    public void addSong(Song song) {
        songs.add(song);
    }

    /**
     * Removes a song from the list.
     * 
     * @param song - A song object is removed
     */
    public void removeSong(Song song) {
        songs.remove(song);
    }
    
    /**
     * Allows a song to be found using the title.
     * 
     * @param title the title of the song
     * @return a song if it matches otherwise null
     */
    public Song getSongByTitle(String title) {
        return songs.stream()
            .filter(s -> s.getTitle().equalsIgnoreCase(title))
            .findFirst()
            .orElse(null);
    }

    /**
     * Shows all the songs in the list uising strings.
     * 
     * @return song list as a string
     */
    @Override
    public String toString() {
        return "SongList{" +
                "songs=" + songs +
                '}';
    }
}