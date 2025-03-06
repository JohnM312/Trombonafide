package com.trombonafide.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.User;
import com.model.Song;
import com.model.Lesson;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class DataWriter {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static void saveUsers(List<User> users) {
        try {
            objectMapper.writeValue(new File(Constants.USER_FILE), users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveSongs(List<Song> songs) {
        try {
            objectMapper.writeValue(new File(Constants.SONG_FILE), songs);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveLessons(List<Lesson> lessons) {
        try {
            objectMapper.writeValue(new File(Constants.LESSON_FILE), lessons);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}