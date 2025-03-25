package com.model;

import java.util.ArrayList;
import java.util.List;

public class SongList {
    private List<Song> songs;

    public SongList() {
        this.songs = new ArrayList<>();
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