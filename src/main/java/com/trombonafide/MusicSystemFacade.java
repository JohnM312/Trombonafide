package com.trombonafide;

import com.model.Lesson;
import com.model.LessonList;
import com.model.Song;
import com.model.SongList;
import com.model.User;
import com.model.UserList;

/**
 * Facade class for music system
 * @author Aiden Campbell
 */

    public class MusicSystemFacade {
    
    private static MusicSystemFacade facade; // Singleton instance

    private static UserList userList = UserList.getInstance();
    private static LessonList lessonList = LessonList.getInstance();
    private static SongList songList = SongList.getInstance();


    private User currentUser;

    private MusicSystemFacade() {
        this.currentUser = new User("guest", "guest", 
        "guest");
    }

    // Singleton accessor
    public static MusicSystemFacade getFacadeInstance() {
        if (facade == null) {
            facade = new MusicSystemFacade();
        }
        return facade;
    }

    // Users part
    public boolean register(String firstName, String lastName, String username, String password, String email,
                            String phoneNumber, String type) {
        if (findUser(username, password) == null) {
            User newUser = new User(firstName, lastName, username,
            password, email, phoneNumber, type);
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

    private User findUser(String username, String password) {
        for (User user : userList.getUsers()) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    // lesson part
    public boolean addLesson(String lessonTitle, String content) {
        Lesson lesson = new Lesson(lessonTitle, content);
        lessonList.addLesson(lesson);
        return true;
    }

    public Lesson getLessonByTitle(String title) {
        for (Lesson lesson : lessonList.getLessons()) {
            if (lesson.getTitle().equalsIgnoreCase(title)) {
                return lesson;
            }
        }
        return null;
    }

    public boolean startLesson(String title) {
        Lesson lesson = getLessonByTitle(title);
        if (lesson != null) {
            System.out.println("Starting lesson: " + lesson.getTitle());
            System.out.println(lesson.getContent());
            return true;
        }
        return false;
    }
    //song part
    public boolean addSong(String title, String[] notes) {
        Song song = new Song(title, notes);
        songList.addSong(song);
        return true;
    }
    public Song getSongByTitle(String title) {
        return songList.getSongByTitle(title);
    }
    public boolean playSong(String title) {
        Song song = songList.getSongByTitle(title);
        if (song != null) {
            for (String note : song.getNotes()) {
                Music.playNote(note);
                try {
                    Thread.sleep(50); // Can Adjust playback speed later
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return true;
        }
        return false;
    }

}
