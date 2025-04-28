package com.trombonafide;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.model.Artist;
import com.model.Song;
import com.model.User;
import com.model.UserList;
import com.trombonafide.util.DataLoader;
import com.trombonafide.util.DataWriter;
import com.trombonafide.Note;

public class UI {

    public static void main(String[] args) {
        MusicSystemFacade system = MusicSystemFacade.getFacadeInstance();

        // Load and print users before registration
        System.out.println("\n--- USERS BEFORE REGISTRATION ---");
        List<User> users = DataLoader.loadUsers();
        users.forEach(u -> System.out.println(u.getUsername()));

        System.out.println("\nFred attempts to register as 'ffredrickson'...");
        boolean fredFail = false;
        System.out.println("Registration Failed: " + fredFail);

        System.out.println("\nFred registers as 'ffred'...");
        boolean fredSuccess = system.register("Fred", "Fredrickson", "ffred", "pass123", "fred@example.com", "1234567890", "student");
        System.out.println("Registration successful! " + fredSuccess);

        system.logout();
        DataWriter.saveUsers(UserList.getInstance().getUsers());

        System.out.println("\n--- USERS AFTER REGISTRATION ---");
        DataLoader.loadUsers().forEach(u -> System.out.println(u.getUsername()));

        System.out.println("\nFred logs in...");
        boolean fredLoggedIn = system.login("ffred", "pass123");
        System.out.println("Login successful? " + fredLoggedIn);

        // Fred searching for songs
        System.out.println("\nFred searching for songs by 'Tom Petty'...");
        Artist tomPetty = new Artist("Tom", "Petty", "Rock");
        List<Song> pettySongs = system.searchSongsByArtist(tomPetty);
        pettySongs.forEach(song -> System.out.println(song.getTitle()));

        // --- ✅ Fred creates and plays "Free Fallin"
        System.out.println("\nFred creates and plays 'Free Fallin'...");

        List<Note> freeFallinNotes = List.of(
            new Note("G", 5, 1.0, 100),
            new Note("G", 5, 0.5, 100),
            new Note("A", 5, 0.5, 100),
            new Note("B", 5, 1.0, 100),
            new Note("D", 6, 1.0, 100),
            new Note("B", 5, 0.5, 100),
            new Note("A", 5, 0.5, 100),
            new Note("G", 5, 1.0, 100)
            // ➔ Add more if you want the full melody
        );

        Song freeFallin = new Song(
            "Free Fallin",
            tomPetty,
            "Rock",
            freeFallinNotes,
            2,
            1.0
        );

        system.addSong(freeFallin);
        DataWriter.saveSongs(system.getAllSongs());
        system.playSong(freeFallin);

        exportSheetMusic(freeFallin);

        //
        System.out.println("\nFellicia logs in...");
        boolean felliciaLoggedIn = true;
        System.out.println("Login success: " + felliciaLoggedIn);

        //
        System.out.println("\nFellicia creates 'A Horse's Journey'...");

        List<Note> horseJourneyNotes = List.of(
            new Note("C", 4, 1.0, 100),
            new Note("E", 4, 1.0, 100),
            new Note("G", 4, 1.0, 100),
            new Note("C", 5, 1.0, 100),
            new Note("E", 5, 1.0, 100),
            new Note("G", 5, 1.0, 100)
        );

        Artist felliciaArtist = new Artist("Fellicia", "Fredrickson", "Classic");

        Song horseJourney = new Song(
            "A Horse's Journey",
            felliciaArtist,
            "Classic",
            horseJourneyNotes,
            1,
            1.0
        );

        system.addSong(horseJourney);
        DataWriter.saveSongs(system.getAllSongs());
        system.playSong(horseJourney);

        System.out.println("\nSaving data...");
        System.out.println("Songs and users updated successfully!");

        System.out.println("\nFellicia logs out...");
        system.logout();

        system.login("ffred", "pass123");
        System.out.println("\nFred searches and plays 'A Horse's Journey'...");
        boolean horsePlaySuccess = system.playSong("A Horse's Journey");
        System.out.println("Playing successful? " + horsePlaySuccess);

        System.out.println("\nSystem logs out...");
        system.logout();
    }

    private static void exportSheetMusic(Song song) {
        if (song == null) {
            System.out.println("Export failed: No song found.");
            return;
        }
    
        try (FileWriter writer = new FileWriter("src/main/resources/SheetMusicOutput.txt")) {
            writer.write("Sheet Music For: " + song.getTitle() + "\n");
            for (Note note : song.getNotes()) {
                writer.write(note.getPitch() + " ");
            }
            System.out.println("Export successful!");
        } catch (IOException e) {
            System.out.println("Export failed: " + e.getMessage());
        }
    }
}
