package com.trombonafide.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.model.User;
import com.model.Song;
import com.model.Lesson;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 * This class provides static methods to persist application data
 * such as {@link User}, {@link Song}, and {@link Lesson} objects into JSON files
 * using the Jackson library.
 * 
 * Files are defined by constants in the {@link Constants} class.
 * 
 * @author Andrew Lim
 */
public class DataWriter {
    /** 
     * Jackson ObjectMapper for serializing and deserializing JSON content. 
     * 
     */
    private static final ObjectMapper objectMapper = new ObjectMapper();
    /**
     * Saves a list of new users to the user JSON file. If the file already exists,
     * the existing users are preserved and new users are appended.
     *
     * @param newUsers the list of new {@link User} objects to be saved
     */
    public static void saveUsers(List<User> newUsers) {
        try {
            File file = new File(Constants.USER_FILE);
            List<User> users = new ArrayList<>();

            if (file.exists()) {
                users = objectMapper.readValue(file, new TypeReference<List<User>>() {});
            }

            users.addAll(newUsers);

            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Saves a list of new songs to the song JSON file. If the file already exists,
     * existing songs are retained and the new ones are appended.
     *
     * @param newSongs the list of new {@link Song} objects to be saved
     */
    public static void saveSongs(List<Song> newSongs) {
        try {
            File file = new File(Constants.SONG_FILE);
            List<Song> songs = new ArrayList<>();

            if (file.exists()) {
                songs = objectMapper.readValue(file, new TypeReference<List<Song>>() {});
            }

            songs.addAll(newSongs);

            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, songs);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Saves a list of new lessons to the lesson JSON file. If the file already exists,
     * existing lessons are preserved and new ones are appended.
     *
     * @param newLessons the list of new {@link Lesson} objects to be saved
     */
    public static void saveLessons(List<Lesson> newLessons) {
        try {
            File file = new File(Constants.LESSON_FILE);
            List<Lesson> lessons = new ArrayList<>();

            if (file.exists()) {
                lessons = objectMapper.readValue(file, new TypeReference<List<Lesson>>() {});
            }

            lessons.addAll(newLessons);

            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, lessons);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}