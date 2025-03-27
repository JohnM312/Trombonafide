package com.trombonafide.util;
/**
 * this class contains file paths used throughout the 
 * Trombonafide application for loading and saving persisten data
 * 
 * these reference by utility classes such as {@link DataLoader} and {@link DataWriter}
 * 
 * @author Andrew Lim
 */
public class Constants {
    /** 
     * File path for storing and loading user data in JSON format. 
     */
    public static final String USER_FILE = "src/main/resources/User.json";
    /**
     * File path for storing and loading lesson data in JSON format.
     */
    public static final String LESSON_FILE = "src/main/resources/Lesson.json";
    /**
     * File path for storing and loading song data in JSON format.
     */
    public static final String SONG_FILE = "src/main/resources/Song.json";
}