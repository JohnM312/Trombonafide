package com.trombonafide;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.model.Artist;
import com.model.Lesson;
import com.model.LessonList;
import com.model.Song;
import com.model.SongList;
import com.model.User;
import com.model.UserList;
import com.trombonafide.util.MusicPlayer;

/**
 * Facade class for managing users, lessons, and songs in the music system.
 * <p>
 * Provides a simplified interface for user authentication, lesson management,
 * song playback, and other related operations.
 * </p>
 */
public class MusicSystemFacade {

    private static MusicSystemFacade facade;
    private User currentUser;
    private Song currentSong;
    private Lesson currentLesson;

    private final UserList userList;
    private final LessonList lessonList;
    private final SongList songList;

    /**
     * Private constructor to enforce singleton pattern.
     * Initializes user, lesson, and song lists.
     */
    private MusicSystemFacade() {
        this.currentUser = new User("guest", "guest", "guest");
        this.userList = UserList.getInstance();
        this.lessonList = LessonList.getInstance();
        this.songList = SongList.getInstance();
    }

    /**
     * Returns the singleton instance of the facade.
     *
     * @return The MusicSystemFacade instance.
     */
    public static MusicSystemFacade getFacadeInstance() {
        if (facade == null) {
            facade = new MusicSystemFacade();
        }
        return facade;
    }


    /**
     * Registers a new user if the username is not already taken.
     *
     * @return true if registration succeeds; false otherwise.
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
     * Logs in an existing user.
     *
     * @return true if login succeeds; false otherwise.
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
     * Logs out the current user.
     *
     * @return true if logout succeeds; false otherwise.
     */
    public boolean logout() {
        if (currentUser == null) return false;
        currentUser = null;
        return true;
    }

    /**
     * Returns the currently logged-in user.
     *
     * @return The current User, or null if no user is logged in.
     */
    public User getCurrentUser() {
        return currentUser;
    }

    /**
     * Checks if a specific user is currently logged in.
     *
     * @param username The username to check.
     * @return true if the given username matches the logged-in user; false otherwise.
     */
    public boolean isUserLoggedIn(String username) {
        return currentUser != null && currentUser.getUsername().equals(username);
    }

    /**
     * Finds a user by username and password.
     *
     * @return The matching User, or null if not found.
     */
    private User findUser(String username, String password) {
        for (User user : userList.getUsers()) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    

    /**
     * Adds a new lesson to the system.
     *
     * @return true if the lesson was successfully added.
     */
    public boolean addLesson(String lessonTitle, String content, String hint) {
        Lesson lesson = new Lesson(lessonTitle, content, hint);
        lessonList.addLesson(lesson);
        return true;
    }

    /**
     * Finds a lesson by its title.
     *
     * @param lessonTitle The title to search for.
     * @return The Lesson if found; otherwise null.
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
     * Starts a lesson based on its title.
     *
     * @return true if the lesson was found and started; false otherwise.
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
     * @return true if a lesson was active and stopped; false otherwise.
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
     * Returns a list of all available lessons.
     *
     * @return A list of all lessons.
     */
    public List<Lesson> getAllLessons() {
        return lessonList.getLessons();
    }

    // --- Song Methods ---

    /**
     * Adds a new song to the system using note strings.
     *
     * @return true if the song was successfully added.
     */
    public boolean addSong(String title, String[] notes) {
        Artist artist = new Artist("Unknown", "Artist", "Unknown");
        String joinedNotes = String.join(" ", notes);
        Song song = new Song(title, artist, "Unknown", joinedNotes, 1, 1.0);
        songList.addSong(song);
        return true;
    }

    /**
     * Finds a song by its title.
     *
     * @param title The title to search for.
     * @return The Song if found; otherwise null.
     */
    public Song getSongByTitle(String title) {
        return songList.getSongByTitle(title);
    }

    /**
     * Plays a song based on its title.
     *
     * @return true if the song was found and played; false otherwise.
     */
    public boolean playSong(String title) {
        Song song = songList.getSongByTitle(title);
        if (song != null && song.getNotes() != null) {
            this.currentSong = song;
            System.out.println("Playing: " + song.getTitle());

            for (Note note : song.getNotes()) {
                if (note != null) {
                    MusicPlayer.playNote(note.getPitch());
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
     * Plays a given song object.
     *
     * @param song The Song to play.
     * @return true if the song was played; false otherwise.
     */
    public boolean playSong(Song song) {
        if (song != null) {
            this.currentSong = song;
            return playSong(song.getTitle());
        }
        return false;
    }

    /**
     * Adds a complete Song object to the system.
     *
     * @param song The Song to add.
     */
    public void addSong(Song song) {
        songList.addSong(song);
    }

    /**
     * Stops the currently playing song.
     *
     * @return true if a song was active and stopped; false otherwise.
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
     * Returns a list of all available songs.
     *
     * @return A list of all songs.
     */
    public List<Song> getAllSongs() {
        return songList.getSongs();
    }

    /**
     * Searches for songs by a given artist.
     *
     * @param artist The Artist to search for.
     * @return A list of matching songs.
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

    /**
     * Finds a lesson by its UUID.
     *
     * @param id The UUID to search for.
     * @return The Lesson if found; otherwise null.
     */
    public Lesson getLessonById(UUID id) {
        for (Lesson lesson : lessonList.getLessons()) {
            if (lesson.getUuid().equals(id)) {
                return lesson;
            }
        }
        return null;
    }
}
