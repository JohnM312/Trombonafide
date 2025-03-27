package com.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages and contains a list of users using a singleton pattern.
 */
public class UserList {
    private List<User> users;
    private static UserList instance;

    /**
     * Constructor that starts up the user list.
     */
    private UserList() {
        this.users = new ArrayList<>();
    }

    /**
     * Returns a shared instance of the UserList.
     * 
     * @return the UserList instance
     */
    public static UserList getInstance() {
        if (instance == null) {
            instance = new UserList();
        }
        return instance;
    }

    /**
     * Returns the list of all users.
     * 
     * @return a list of User objects
     */
    public List<User> getUsers() {
        return users;
    }

    /**
     * Adds a user to the list.
     * 
     * @param user the User to be added
     */
    public void addUser(User user) {
        users.add(user);
    }

    /**
     * Removes a user from the list.
     * 
     * @param user to be removed
     */
    public void removeUser(User user) {
        users.remove(user);
    }

    /**
     * Returns the user list using a string.
     * 
     * @return all users using a string
     */
    @Override
    public String toString() {
        return "UserList{" +
                "users=" + users +
                '}';
    }
}