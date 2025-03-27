package com.trombonafide;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.model.Artist;
import com.model.Song;
import com.model.SongList;
import com.model.User;
import com.model.UserList;
import com.trombonafide.util.DataLoader;
import com.trombonafide.util.DataWriter;

/**
 * The UI class serves as the main entry point for interacting with the Trombonafide music system.
 * It provides functionality for user registration, login, song search, playback, and sheet music export.
 * 
 * This class demonstrates various operations using the MusicSystemFacade.
 * 
 * @author Andrew Lim
 */
public class UI {
    
    /**
     * The main method initializes the system and demonstrates various user interactions.
     * 
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        MusicSystemFacade system = MusicSystemFacade.getFacadeInstance();

        // Load and print users before registration
        System.out.println("\n--- USERS BEFORE REGISTRATION ---");
        List<User> users = DataLoader.loadUsers();
        users.forEach(u -> System.out.println(u.getUsername()));

        // Register a new user
        System.out.println("\nAttempting to register 'ffredrickson'...");
        boolean fredFail = system.register("Fred", "Fredrickson", "ffredrickson", "pass123", "fred@example.com", "1234567890", "student");
        System.out.println("Registration successful? " + fredFail);

        // Register another user
        System.out.println("\nRegistering 'ffred'...");
        boolean fredSuccess = system.register("Fred", "Fredrickson", "ffred", "pass123", "fred@example.com", "1234567890", "student");
        System.out.println("Registration successful? " + fredSuccess);

        // Logout and save users
        system.logout();
        DataWriter.saveUsers(UserList.getInstance().getUsers());

        // Load and print users after registration
        System.out.println("\n--- USERS AFTER REGISTRATION ---");
        DataLoader.loadUsers().forEach(u -> System.out.println(u.getUsername()));

        // User login
        System.out.println("\nFred logging in...");
        boolean fredLoggedIn = system.login("ffred", "pass123");
        System.out.println("Login success: " + fredLoggedIn);

        // Search for songs by an artist
        System.out.println("\nFred searching for songs by 'Tom Petty'...");
        Artist tomPetty = new Artist("Tom", "Petty", "Rock");
        List<Song> pettySongs = system.searchSongsByArtist(tomPetty);
        pettySongs.forEach(song -> System.out.println(song.getTitle()));

        // Plays Free Fallin
        System.out.println("\n Fred plays 'Free Fallin'...");
        String[] freeFallinNotes = {"G5q", "G5i", "A5i", "B5q", "D6q", "B5i", "A5i", "G5q", "E5q", "G5q", "G5i", "A5i", "B5q", "D6q", "B5i", "A5i", "G5q", "E5q", "D5q", "E5q", "G5h", "D5q", "E5q", "G5q","F#5q", "E5q", "D5h"};
        system.addSong("Free Fallin", freeFallinNotes);
        system.playSong("Free Fallin");

        // Export sheet music
        System.out.println("\nExporting sheet music to text file...");
        Song freeFallin = system.getSongByTitle("Free Fallin");
        exportSheetMusic(freeFallin);

        // Another user logs in
        System.out.println("\nFellicia logs in...");
        boolean felliciaLoggedIn = system.login("ffredrickson", "pass123");
        System.out.println("Login success: " + felliciaLoggedIn);
        
        // Create and play a new song
        System.out.println("\nFellicia creates 'A Horses Journey'...");
        String[] notes = {"C", "E", "G", "C", "E", "G"};
        system.addSong("A Horses Journey", notes);
        system.playSong("A Horses Journey");

        // Save data
        DataWriter.saveSongs(SongList.getInstance().getSongs());
        DataWriter.saveUsers(UserList.getInstance().getUsers());
        system.logout();

        // Fred logs in again and plays "A Horses Journey"
        system.login("ffred", "pass123");
        System.out.println("\nFred searches and plays 'A Horses Journey'...");
        boolean horsePlaySuccess = system.playSong("A Horses Journey");
        System.out.println("Playing successful! " + horsePlaySuccess);
    }

    /**
     * Exports the sheet music of a given song to a text file.
     * 
     * @param song The song whose sheet music is to be exported.
     */
    private static void exportSheetMusic(Song song) {
        if (song == null) {
            System.out.println("Export failed: No song found.");
            return;
        }
    
        try (FileWriter writer = new FileWriter("src/main/resources/SheetMusicOutput.txt")) {
            writer.write("Sheet Music For: " + song.getTitle() + "\n");
    
            // Splitting the notes string into an array before iterating
            for (String note : song.getNotes().split("\\s+")) {
                writer.write(note + " ");
            }
    
            System.out.println("Export successful!");
        } catch (IOException e) {
            System.out.println("Export failed: " + e.getMessage());
        }
    }
}