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

public class DataWriter {
    private static final ObjectMapper objectMapper = new ObjectMapper();

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