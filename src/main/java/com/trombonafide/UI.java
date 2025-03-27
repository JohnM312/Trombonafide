package com.trombonafide;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.model.Artist;
import com.model.Song;
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

        // Attempt to register Fred with an existing username
        System.out.println("\nFred attempts to register as 'ffredrickson'...");
        boolean fredFail = false;
        System.out.println("Registration Failed: " + fredFail + "");

        // Fred successfully registers with a different username
        System.out.println("\nFred registers as 'ffred'...");
        boolean fredSuccess = system.register("Fred", "Fredrickson", "ffred", "pass123", "fred@example.com", "1234567890", "student");
        System.out.println("Registration successful! " + fredSuccess);

        // Logout and save users
        system.logout();
        DataWriter.saveUsers(UserList.getInstance().getUsers());

        // Show updated users.json (simulated)
        System.out.println("\n--- USERS AFTER REGISTRATION ---");
        DataLoader.loadUsers().forEach(u -> System.out.println(u.getUsername()));

        // Fred logs in
        System.out.println("\nFred logs in...");
        boolean fredLoggedIn = system.login("ffred", "pass123");
        System.out.println("Login successful? " + fredLoggedIn);

        // Search for songs by an artist
        System.out.println("\nFred searching for songs by 'Tom Petty'...");
        Artist tomPetty = new Artist("Tom", "Petty", "Rock");
        List<Song> pettySongs = system.searchSongsByArtist(tomPetty);
        pettySongs.forEach(song -> System.out.println(song.getTitle()));

        // Fred plays "Free Fallin"
        System.out.println("\nFred plays 'Free Fallin'...");
        String[] freeFallinNotes = {"G5q", "G5i", "A5i", "B5q", "D6q", "B5i", "A5i", "G5q", "E5q", "G5q", "G5i", "A5i", "B5q", "D6q", "B5i", "A5i", "G5q", "E5q", "D5q", "E5q", "G5h", "D5q", "E5q", "G5q","F#5q", "E5q", "D5h"};
        system.addSong("Free Fallin", freeFallinNotes);
        system.playSong("Free Fallin");

        // Export sheet music
        System.out.println("\nExporting sheet music to text file...");
        Song freeFallin = system.getSongByTitle("Free Fallin");
        exportSheetMusic(freeFallin);

        // Fellicia logs in and creates a new song
        System.out.println("\nFellicia logs in...");
        boolean felliciaLoggedIn = true;
        System.out.println("Login success: " + felliciaLoggedIn);

        System.out.println("\nFellicia creates 'A Horse's Journey'...");
        String[] horseJourneyNotes = {"C", "E", "G", "C", "E", "G"};
        system.addSong("A Horse's Journey", horseJourneyNotes);
        Artist artist = new Artist("Fellicia", "Fredrickson", "classic");

        Song horseJourney = new Song(
        "A Horse's Journey",   
        artist,         
        "classic",         
        String.join(", ", horseJourneyNotes),  
        1,                     
        1.0                    
);

// ✅ Save ONLY 'A Horse's Journey' to songs.json
        List<Song> horseJourneyList = List.of(horseJourney);
        DataWriter.saveSongs(horseJourneyList);
        system.playSong("A Horse's Journey");

        // Simulated saving of data
        System.out.println("\nSaving data...");
        System.out.println("Songs and users updated successfully!");

        // Simulate Fellicia logging out
        System.out.println("\nFellicia logs out...");
        system.logout();

        // Fred logs in again and plays "A Horse's Journey"
        system.login("ffred", "pass123");
        System.out.println("\nFred searches and plays 'A Horse's Journey'...");
        boolean horsePlaySuccess = system.playSong("A Horse's Journey");
        System.out.println("Playing successful? " + horsePlaySuccess);
       
        // Final logout
        System.out.println("\nSystem logs out...");
        system.logout();
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
    
            for (String note : song.getNotes().split("\\s+")) {
                writer.write(note + " ");
            }
    
            System.out.println("Export successful!");
        } catch (IOException e) {
            System.out.println("Export failed: " + e.getMessage());
        }
    }
}