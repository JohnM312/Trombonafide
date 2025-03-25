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
 * this is the UI class for trombonafide
 * 
 * @author Andrew Lim
 */
public class UI {   
    public static void main (String[] args){
        MusicSystemFacade system = MusicSystemFacade.getFacadeInstance();

        System.out.println("\n---USERS BEFORE REGISTRATION ---");
        List<User> users = DataLoader.loadUsers();
        users.forEach(u -> System.out.println(u.getUsername()));

        System.out.println("\nAttempting to register 'ffredrickson'...");
        boolean fredFail = system.register("Fred", "Fredrickson", "ffredrickson", "pass123", "fred@example.com", "1234567890", "student");
        System.out.println("Registration succesful? " + fredFail);

        System.out.println("\nRegistering 'ffred'...");
        boolean fresSuccess = system.register("Fred", "Fredrickson", "ffred", "pass123", "fred@example.com", "1234567890", "student");
        System.out.println("Registration successful? " + fresSuccess);

        system.logout();
        DataWriter.saveUsers(UserList.getInstance().getUsers());

        System.out.println("\nUSERS AFTER REGISTRATION");
        DataLoader.loadUsers().forEach(u -> System.out.println(u.getUsername()));

        System.out.println("\nFred logging in...");
        boolean fredLoggedIn = system.login("ffred", "pass123");
        System.out.println("Login success: " + fredLoggedIn);

        System.out.println("\nFred searching for songs by 'Tom Pretty'...");
        Artist tomPetty = new Artist("Tom", "Petty", "Rock");
        List<Song> pettySongs = system.searchSongsByArtist(tomPetty);
        pettySongs.forEach(song -> System.out.println(song.getTitle()));

        System.out.println("\nFred plays 'Free Fallin'...");
        system.playSong("Free Fallin");

        System.out.println("\nExporting sheet music to text file...");
        Song freeFallin = system.getSongByTitle("Free Fallin");
        exportSheetMusic(freeFallin);

        System.out.println("\nFellicia logs in...");
        boolean felliciaLoggedIn = system.login("ffredrickson", "pass123");
        System.out.println("Login success: " + felliciaLoggedIn);
        
        System.out.println("\nFellicia creates 'A Horses Journey'...");
        String[] notes = {"C", "E", "G", "C", "E", "G"};
        system.addSong("A Horses Journey, notes", notes);
        system.playSong("A Horses Journey");
        DataWriter.saveSongs(SongList.getInstance().getSongs());
        DataWriter.saveUsers(UserList.getInstance().getUsers());
        system.logout();

        system.login("ffred", "pass123");
        System.out.println("\nFred searches and plays 'A Horses Journey'...");
        system.playSong("A Horses Journey");

    }

    private static void exportSheetMusic(Song song) {
        if (song == null || song.getNotes() == null) {
            System.out.println("No song data to export");
            return;
        }
        
        try (FileWriter writer = new FileWriter("src/main/resources/SheetMusicOutput.txt")) {
            writer.write("Sheet Music For: " + song.getTitle() + "\n");
            writer.write(song.getNotes());
            System.out.println("Sheet music exported successfully");
        } catch (IOException e) {
            System.out.println("Export failed: " + e.getMessage());
        }
    }
}
    

