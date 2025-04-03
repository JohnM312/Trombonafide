package com.trombonafide;

import com.model.Song;
import com.model.Artist;
import com.model.SongList;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Tests the SongList class to make sure songs are managed correctly
 * uses singleton pattern.
 */
public class SongListTest {

    private SongList songList;

    /**
     * Tests SongList by clearing existing songs.
     */
    @Before
    public void setUp() {
        songList = SongList.getInstance();
        songList.getSongs().clear();
    }

    /**
     * Tests that songs can be added to the list successfully.
     */
    @Test
    public void testAddSong() {
        Song song = new Song("Midnight Blues", new Artist("Miles", "Davis", "Jazz"), "Jazz", "A, B", 3, 2.0);
        songList.addSong(song);

        assertEquals(1, songList.getSongs().size());
        assertEquals("Midnight Blues", songList.getSongs().get(0).getTitle());
    }

    /**
     * Tests that songs can be removed from the list.
     */
    @Test
    public void testRemoveSong() {
        Song song = new Song("Morning Light", new Artist("Ella", "Fitzgerald", "Swing"), "Swing", "C, D", 2, 1.5);
        songList.addSong(song);
        songList.removeSong(song);

        assertTrue(songList.getSongs().isEmpty());
    }

    /**
     * Tests that getSongByTitle finds a song by its title, ignoring case.
     */
    @Test
    public void testGetSongByTitle() {
        Song song = new Song("Ocean Breeze", new Artist("Sarah", "Vaughan", "Soul"), "Soul", "E, F", 4, 3.0);
        songList.addSong(song);

        Song found = songList.getSongByTitle("ocean breeze");
        assertNotNull(found);
        assertEquals("Ocean Breeze", found.getTitle());
    }

    /**
     * Tests that getSongByTitle returns null when no matching song is found.
     */
    @Test
    public void testGetSongByTitleNotFound() {
        Song result = songList.getSongByTitle("No Such Song");
        assertNull(result);
    }

    /**
     * Tests that the singleton instance always returns the same object.
     */
    @Test
    public void testSingletonInstance() {
        SongList anotherInstance = SongList.getInstance();
        assertSame(songList, anotherInstance);
    }

    /**
     * Tests that toString returns a non-null string.
     */
    @Test
    public void testToString() {
        Song song = new Song("Evening Glow", new Artist("Louis", "Armstrong", "Jazz"), "Jazz", "G, A", 1, 1.0);
        songList.addSong(song);
        assertNotNull(songList.toString());
    }
}
