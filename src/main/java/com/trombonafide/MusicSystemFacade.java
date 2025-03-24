package com.trombonafide;

import com.model.LessonList;
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
    private static ProgressTracker progressTracker = ProgressTracker.getInstance();

    private User currentUser;

    private MusicSystemFacade() {
        this.currentUser = new User("guest", "guest", "guest");
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
}
