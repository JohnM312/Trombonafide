package com.model;

import java.util.ArrayList;
import java.util.List;

public class UserList {
    private static UserList userList = null;
    private List<User> users;

    public UserList() {
        this.users = new ArrayList<>();
    }

    public static UserList getInstance() {
        if (userList == null) {
            userList = new UserList();

        }
        return userList;
    }
    
    public List<User> getUsers() {
        return users;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void removeUser(User user) {
        users.remove(user);
    }

    @Override
    public String toString() {
        return "UserList{" +
                "users=" + users +
                '}';
    }
}