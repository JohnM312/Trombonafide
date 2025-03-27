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
 * Facade class that provides a simplified interface to interact with the music system.
 * It manages users, songs, and lessons. It follows the Singleton design pattern.
 * 
 * @author Aiden Campbell
 */
public class MusicSystemFacade {
    private static MusicSystemFacade facade;
    private User currentUser;
    private Song currentSong;
    private Lesson currentLesson;

    // Singleton references
    private final UserList userList;
    private final LessonList lessonList;
    private final SongList songList;

    /**
     * Private constructor to initialize the system with default user and singleton lists.
     */
    private MusicSystemFacade() {
        this.currentUser = new User("guest", "guest", "guest");
        this.userList = UserList.getInstance();
        this.lessonList = LessonList.getInstance();
        this.songList = SongList.getInstance();
    }

    /**
     * Returns the singleton instance of the MusicSystemFacade.
     * 
     * @return the singleton instance of MusicSystemFacade
     */
    public static MusicSystemFacade getFacadeInstance() {
        if (facade == null) {
            facade = new MusicSystemFacade();
        }
        return facade;
    }

    // --- User Methods ---

    /**
     * Registers a new user in the system.
     * 
     * @param firstName the first name of the user
     * @param lastName the last name of the user
     * @param username the username for the new user
     * @param password the password for the new user
     * @param email the email address of the user
     * @param phoneNumber the phone number of the user
     * @param type the type of user (e.g., student, teacher)
     * @return true if registration is successful, false if the username is already taken
     */
    public boolean register(String firstName, String lastName, String username, 
                            String password, String email, String phoneNumber, String type) {
        if (findUser(username, password) == null) {
            User newUser = new User(firstName, lastName, username, password, email);
            userList.addUser(newUser);
            this.currentUser = newUser;
            return true;
        }
        return false;
    }

    /**
     * Logs a user into the system.
     * 
     * @param username the username of the user
     * @param password the password of the user
     * @return true if login is successful, false if the username or password is incorrect
     */
    public boolean login(String username, String password) {
        User user = findUser(username, password);
        if (user != null) {
            this.currentUser = user;
            return true;
        }
        return false;
    }

    /**
     * Logs the current user out of the system.
     * 
     * @return true if the user was logged out successfully, false if no user is currently logged in
     */
    public boolean logout() {
        if (currentUser == null) return false;
        currentUser = null;
        return true;
    }

    /**
     * Retrieves the current logged-in user.
     * 
     * @return the current logged-in user
     */
    public User getCurrentUser() {
        return currentUser;
    }

    /**
     * Checks if a user with the given username is currently logged in.
     * 
     * @param username The username to check.
     * @return true if the user is logged in, false otherwise.
     */
    public boolean isUserLoggedIn(String username) {
        return currentUser != null && currentUser.getUsername().equals(username);
    }

    /**
     * Finds a user by their username and password.
     * 
     * @param username the username to search for
     * @param password the password to match
     * @return the user if found, null otherwise
     */
    private User findUser(String username, String password) {
        for (User user : userList.getUsers()) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    // --- Lesson Methods ---
    /**
     * Adds a new lesson to the system.
     * 
     * @param lessonTitle the title of the lesson
     * @param content the content of the lesson
     * @param hint a helpful hint for the lesson
     * @return {@code true} if the lesson was successfully added
     */
    public boolean addLesson(String lessonTitle, String content, String hint) {
        Lesson lesson = new Lesson(lessonTitle, content, hint);
        lessonList.addLesson(lesson);
        return true;
    }
    /**
     * Retrieves a lesson by its title.
     * 
     * @param lessonTitle the title of the lesson
     * @return the {@link Lesson} if found, or {@code null} if not found
     */
    public Lesson getLessonByTitle(String lessonTitle) {
        for (Lesson lesson : lessonList.getLessons()) {
            if (lesson.getTitle().equalsIgnoreCase(lessonTitle)) {
                return lesson;
            }
        }
        return null;
    }
    /**
     * Starts a lesson with the specified title.
     * 
     * @param lessonTitle the title of the lesson to start
     * @return {@code true} if the lesson starts successfully, {@code false} if the lesson is not found
     */
    public boolean startLesson(String lessonTitle) {
        Lesson lesson = getLessonByTitle(lessonTitle);
        if (lesson != null) {
            this.currentLesson = lesson;
            System.out.println("Starting lesson: " + lesson.getTitle());
            System.out.println(lesson.getContent());
            return true;
        }
        return false;
    }
    /**
     * Stops the currently active lesson.
     * 
     * @return {@code true} if the lesson was successfully stopped, {@code false} if no lesson was active
     */
    public boolean stopLesson() {
        if (currentLesson != null) {
            System.out.println("Lesson '" + currentLesson.getTitle() + "' stopped.");
            currentLesson = null;
            return true;
        }
        return false;
    }
    /**
     * Retrieves all available lessons.
     * 
     * @return a list of all available {@link Lesson} objects
     */
    public List<Lesson> getAllLessons() {
        return lessonList.getLessons();
    }

    // --- Song Methods ---
    /**
     * Adds a new song to the system.
     * 
     * @param title the title of the song
     * @param notes the notes of the song as an array of strings
     * @return {@code true} if the song was successfully added
     */
    public boolean addSong(String title, String[] notes) {
        Song song = new Song(title, notes);
        songList.addSong(song);
        return true;
    }
    /**
     * Retrieves a song by its title.
     * 
     * @param title the title of the song
     * @return the {@link Song} if found, or {@code null} if not found
     */
    public Song getSongByTitle(String title) {
        return songList.getSongByTitle(title);
    }
    /**
     * Plays a song by its title.
     * 
     * @param title the title of the song to play
     * @return {@code true} if the song was found and played successfully, {@code false} otherwise
     */
    public boolean playSong(String title) {
        Song song = songList.getSongByTitle(title);
        if (song != null && song.getNotes() != null) {
            this.currentSong = song;
            System.out.println("Playing: " + song.getTitle());

            String[] notes = song.getNotes().split("\\s+");
            for (String note : notes) {
                if (!note.trim().isEmpty()) {
                    MusicPlayer.playNote(note.trim());
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
    /**
     * Stops the currently playing song.
     * 
     * @return {@code true} if the song was stopped successfully, {@code false} if no song was playing
     */
    public boolean stopSong() {
        if (currentSong != null) {
            System.out.println("Stopped playing: " + currentSong.getTitle());
            currentSong = null;
            return true;
        }
        return false;
    }
    /**
     * Retrieves all available songs.
     * 
     * @return a list of all available {@link Song} objects
     */
    public List<Song> getAllSongs() {
        return songList.getSongs();
    }
    /**
     * Searches for songs by a given artist.
     * 
     * @param artist the {@link Artist} whose songs to search for
     * @return a list of songs by the specified artist
     */
    public List<Song> searchSongsByArtist(Artist artist) {
        List<Song> result = new ArrayList<>();
        for (Song song : songList.getSongs()) {
            Artist songArtist = song.getArtist();
            if (songArtist.getFirstName().equalsIgnoreCase(artist.getFirstName()) &&
                songArtist.getLastName().equalsIgnoreCase(artist.getLastName())) {
                    result.add(song);
                }
            }
        return result;
    }
}
