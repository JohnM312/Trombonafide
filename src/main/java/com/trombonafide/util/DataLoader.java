package com.trombonafide.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.model.User;
import com.model.UserList;
import com.model.Song;
import com.model.Lesson;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.ArrayList;
import java.util.List;
/**
 * This class is responsible for reading and deserializing application data 
 * from JSON files into Java objects using the Jackson library.
 * 
 * It loads collections of {@link User}, {@link Song}, and {@link Lesson} objects from 
 * files defined in the {@link Constants} class.
 * 
 * 
 * @author Andrew Lim
 */
public class DataLoader {
    /** 
     * Jackson ObjectMapper used for reading JSON content. 
     */
    private static final ObjectMapper objectMapper = new ObjectMapper();
    /**
     * Loads a list of users from the user JSON file.
     * 
     * @return a list of {@link User} objects; returns an empty list if the file is missing or unreadable
     */
    public static List<User> loadUsers() {
        try {
            InputStream inputStream = DataLoader.class.getClassLoader().getResourceAsStream("User.json");
    
            if (inputStream == null) {
                System.out.println("User.json not found in resources!");
                return new ArrayList<>();
            }
    
            List<User> users = objectMapper.readValue(inputStream, new TypeReference<List<User>>() {});
            System.out.println("Loaded users: " + users.size());
            return users;
    
        } catch (com.fasterxml.jackson.databind.JsonMappingException mappingError) {
            System.out.println("JSON mapping error: " + mappingError.getMessage());
            mappingError.printStackTrace();
            return new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    

    /**
     * Loads a list of songs from the song JSON file.
     * 
     * @return a list of {@link Song} objects; returns an empty list if the file is missing or unreadable
     */
    public static List<Song> loadSongs() {
        try {
            File file = new File(Constants.SONG_FILE);
            if (!file.exists()) return new ArrayList<>();
            return objectMapper.readValue(file, new TypeReference<List<Song>>() {});
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    /**
     * Loads a list of lessons from the lesson JSON file.
     * 
     * @return a list of {@link Lesson} objects; returns an empty list if the file is missing or unreadable
     */
    public static List<Lesson> loadLessons() {
        try {
            File file = new File(Constants.LESSON_FILE);
            if (!file.exists()) return new ArrayList<>();
            return objectMapper.readValue(file, new TypeReference<List<Lesson>>() {});
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    /**
     * Loads users from JSON and populates the shared UserList instance.
     */
    public static void populateUserList() {
        List<User> users = loadUsers();
        UserList.getInstance().getUsers().addAll(users);
        System.out.println("Loaded users: " + users.size());
    }
    
}