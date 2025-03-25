package com.model;

import java.util.ArrayList;
import java.util.List;

public class SongList {
    private List<Song> songs;
    private static SongList instance;

    private SongList() {
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

    public Song getSongByTitle(String title) {
        return songs.stream()
            .filter(s -> s.getTitle().equalsIgnoreCase(title))
            .findFirst()
            .orElse(null);
    }

    @Override
    public String toString() {
        return "SongList{" +
                "songs=" + songs +
                '}';
    }
}