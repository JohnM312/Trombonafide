package com.model;

import java.util.ArrayList;
import java.util.List;

public class SongList {
    private static SongList instance = null;
    private List<Song> songs;

    public SongList() {
        this.songs = new ArrayList<>();
    }

    public static SongList getInstance() {
        if (instance == null) {
            instance = new SongList();
        }
        return instance;
    }
    
    public List<Song> getSongs() {
        return songs;
    }

    public void addSong(Song song) {
        songs.add(song);
    }

    public void removeSong(Song song) {
        songs.remove(song);
    }

    @Override
    public String toString() {
        return "SongList{" +
                "songs=" + songs +
                '}';
    }
}