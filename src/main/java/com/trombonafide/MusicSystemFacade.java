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
import com.trombonafide.Note;

public class MusicSystemFacade {
    private static MusicSystemFacade facade;
    private User currentUser;
    private Song currentSong;
    private Lesson currentLesson;

    private final UserList userList;
    private final LessonList lessonList;
    private final SongList songList;

    private MusicSystemFacade() {
        this.currentUser = new User("guest", "guest", "guest");
        this.userList = UserList.getInstance();
        this.lessonList = LessonList.getInstance();
        this.songList = SongList.getInstance();
    }

    public static MusicSystemFacade getFacadeInstance() {
        if (facade == null) {
            facade = new MusicSystemFacade();
        }
        return facade;
    }

    // --- User Methods ---

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

    public boolean isUserLoggedIn(String username) {
        return currentUser != null && currentUser.getUsername().equals(username);
    }

    private User findUser(String username, String password) {
        for (User user : userList.getUsers()) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    // --- Lesson Methods ---

    public boolean addLesson(String lessonTitle, String content, String hint) {
        Lesson lesson = new Lesson(lessonTitle, content, hint);
        lessonList.addLesson(lesson);
        return true;
    }

    public Lesson getLessonByTitle(String lessonTitle) {
        for (Lesson lesson : lessonList.getLessons()) {
            if (lesson.getTitle().equalsIgnoreCase(lessonTitle)) {
                return lesson;
            }
        }
        return null;
    }

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

    public boolean stopLesson() {
        if (currentLesson != null) {
            System.out.println("Lesson '" + currentLesson.getTitle() + "' stopped.");
            currentLesson = null;
            return true;
        }
        return false;
    }

    public List<Lesson> getAllLessons() {
        return lessonList.getLessons();
    }

    // --- Song Methods ---

    public boolean addSong(String title, String[] notes) {
        Artist artist = new Artist("Unknown", "Artist", "Unknown");
        String joinedNotes = String.join(" ", notes);
        Song song = new Song(title, artist, "Unknown", joinedNotes, 1, 1.0);
        songList.addSong(song);
        return true;
    }

    public Song getSongByTitle(String title) {
        return songList.getSongByTitle(title);
    }

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
    

    public boolean playSong(Song song) {
        if (song != null) {
            this.currentSong = song;
            return playSong(song.getTitle());
        }
        return false;
    }

    public void addSong(Song song) {
        songList.addSong(song);
    }

    public boolean stopSong() {
        if (currentSong != null) {
            System.out.println("Stopped playing: " + currentSong.getTitle());
            currentSong = null;
            return true;
        }
        return false;
    }

    public List<Song> getAllSongs() {
        return songList.getSongs();
    }

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

    public Lesson getLessonById(UUID id) {
        for (Lesson lesson : lessonList.getLessons()) {
            if (lesson.getUuid().equals(id)) {
                return lesson;
            }
        }
        return null;
    }
}
