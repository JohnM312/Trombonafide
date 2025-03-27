package com.trombonafide;

import java.util.ArrayList;
import java.util.List;

import com.model.Artist;
import com.model.Lesson;
import com.model.LessonList;
import com.model.Song;
import com.model.SongList;
import com.model.User;
import com.model.UserList;
import com.trombonafide.util.MusicPlayer;


/**
 * this is the MusicSystemFacade class for trombonafide
 * 
 * @author Aiden Campbell
 */

public class MusicSystemFacade {
    private static MusicSystemFacade facade;
    private User currentUser;

    private MusicSystemFacade() {
        this.currentUser = new User("guest", "guest", "guest");
    }

    public static MusicSystemFacade getFacadeInstance() {
        if (facade == null) {
            facade = new MusicSystemFacade();
        }
        return facade;
    }

    // User methods
    public boolean register(String firstName, String lastName, String username, 
                        String password, String email, String phoneNumber, String type) {
        if (findUser(username, password) == null) {
            User newUser = new User(firstName, lastName, username,
                                password, email);
            UserList.getInstance().addUser(newUser);
            this.currentUser = newUser;
            return true;
        }
        return false;
    }

    public boolean login(String username, String password) {
        User user = findUser(username, password);
        if (user != null) {
            this.currentUser = user;
            return true;
        }
        return false;
    }

    public boolean logout() {
        if (currentUser == null) return false;
        currentUser = null;
        return true;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    private User findUser(String username, String password) {
        for (User user : UserList.getInstance().getUsers()) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    // Lesson methods
    public boolean addLesson(String lessonTitle, String content, String hint) {
        Lesson lesson = new Lesson(lessonTitle, content, hint);
        LessonList.getInstance().addLesson(lesson);
        return true;
    }

    public Lesson getLessonByTitle(String lessonTitle) {
        for (Lesson lesson : LessonList.getInstance().getLessons()) {
            if (lesson.getTitle().equalsIgnoreCase(lessonTitle)) {
                return lesson;
            }
        }
        return null;
    }

    public boolean startLesson(String lessonTitle) {
        Lesson lesson = getLessonByTitle(lessonTitle);
        if (lesson != null) {
            System.out.println("Starting lesson: " + lesson.getTitle());
            System.out.println(lesson.getContent());
            return true;
        }
        return false;
    }

    // Song methods
    public boolean addSong(String title, String[] notes) {
        Song song = new Song(title, notes);
        SongList.getInstance().addSong(song);
        return true;
    }

    public Song getSongByTitle(String title) {
        return SongList.getInstance().getSongByTitle(title);
    }

    public boolean playSong(String title) {
        Song song = SongList.getInstance().getSongByTitle(title);
        if (song != null && song.getNotes() != null) {
            System.out.println("Playing: " + song.getTitle());
            
            // Split notes and handle empty/null cases
            String[] notes = song.getNotes().split("\\s+"); // Split by whitespace
            for (String note : notes) {
                if (!note.trim().isEmpty()) {
                    MusicPlayer.playNote(note.trim());
                    
                    // Add delay between notes (adjust 200ms as needed)
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }

    public List<Song> searchSongsByArtist(Artist artist) {
        List<Song> result = new ArrayList<>();
        for (Song song : SongList.getInstance().getSongs()) {
            Artist songArtist = song.getArtist();
            if (songArtist.getFirstName().equalsIgnoreCase(artist.getFirstName()) &&
            songArtist.getLastName().equalsIgnoreCase(artist.getLastName())) {
                    result.add(song);
                }
            }
        
        return result;
    }
}