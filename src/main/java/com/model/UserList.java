package com.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages a list of users in the app.
 * Uses a singleton so there's only one shared list across the program.
 */
public class UserList {
    private List<User> users;
    private static UserList instance;

    /**
     * Private constructor to make sure other classes can't make a new UserList.
     * Starts with an empty user list.
     */
    private UserList() {
        this.users = new ArrayList<>();
    }

    /**
     * Gets the shared instance of UserList.
     * If it doesn't exist yet, it creates one.
     *
     * @return the single UserList instance
     */
    public static UserList getInstance() {
        if (instance == null) {
            instance = new UserList();
        }
        return instance;
    }

    /**
     * Returns the full list of users.
     *
     * @return list of User objects
     */
    public List<User> getUsers() {
        return users;
    }

    /**
     * Adds a new user to the list.
     *
     * @param user the user to add
     */
    public void addUser(User user) {
        users.add(user);
    }

    /**
     * Removes a user from the list.
     *
     * @param user the user to remove
     */
    public void removeUser(User user) {
        users.remove(user);
    }

    /**
     * Gives a summary of all users in the list.
     *
     * @return a formatted string of users
     */
    @Override
    public String toString() {
        return "UserList{" +
                "users=" + users +
                '}';
    }
}
