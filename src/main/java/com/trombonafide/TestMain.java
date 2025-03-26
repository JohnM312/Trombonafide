package com.trombonafide;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import com.model.User;
import com.model.Song;
import com.model.Artist;
import com.model.Lesson;
import com.trombonafide.util.DataWriter;
import com.trombonafide.util.DataLoader;

public class TestMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n What would you like to do?");
            System.out.println("1. Add a User");
            System.out.println("2. Add a Song");
            System.out.println("3. Add a Lesson");
            System.out.println("4. View Data");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();
                    System.out.print("Enter email: ");
                    String email = scanner.nextLine();

                    List<User> newUsers = new ArrayList<>();
                    newUsers.add(new User(username, password, email));
                    DataWriter.saveUsers(newUsers);
                    System.out.println("User added successfully!");
                    break;

                case 2:
                System.out.print("Enter song title: ");
                String songTitle = scanner.nextLine();
                System.out.print("Enter artist first name: ");
                String artistFirstName = scanner.nextLine();
                System.out.print("Enter artist last name: ");
                String artistLastName = scanner.nextLine();
                System.out.print("Enter artist genre: ");
                String artistGenre = scanner.nextLine();
                System.out.print("Enter song genre: ");
                String songGenre = scanner.nextLine();
                System.out.print("Enter song notes (comma-separated): ");
                String songNotes = scanner.nextLine();
                System.out.print("Enter difficulty rating (1–5): ");
                int songDifficulty = Integer.parseInt(scanner.nextLine());
            
                System.out.print("Enter melody structure rating (0.0–1.0): ");
                double songMelody = Double.parseDouble(scanner.nextLine());
            
                // Create artist and song
                Artist songArtist = new Artist(artistFirstName, artistLastName,
                artistGenre);
                Song newSong = new Song(songTitle, songArtist, songGenre,
                songNotes, songDifficulty, songMelody);
            
                List<Song> newSongs = new ArrayList<>();
                newSongs.add(newSong);
                DataWriter.saveSongs(newSongs);
            
                System.out.println("Song added successfully!");
                break;
            

                case 3:
                    System.out.print("Enter lesson title: ");
                    String lessonTitle = scanner.nextLine();
                    System.out.print("Enter lesson content: ");
                    String content = scanner.nextLine();
                    System.out.print("Enter hint: ");
                    String hint = scanner.nextLine();

                    List<Lesson> newLessons = new ArrayList<>();
                    newLessons.add(new Lesson(lessonTitle, content, hint));
                    DataWriter.saveLessons(newLessons);
                    System.out.println("Lesson added successfully!");
                    break;

                case 4:
                    System.out.println("\n Users: " + DataLoader.loadUsers());
                    System.out.println(" Songs: " + DataLoader.loadSongs());
                    System.out.println(" Lessons: " + DataLoader.loadLessons());
                    break;

                case 5:
                    System.out.println("Goodbye!");
                    running = false;
                    break;

                default:
                    System.out.println(" Invalid choice. Please enter a number between 1-5.");
            }
        }

        scanner.close();
    }
}