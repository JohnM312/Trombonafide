package com.model;

/**
 * Represents the musical artist. Contains the first and last name as well as music genre.
 */

public class Artist {
    private String firstName;
    private String lastName;
    private String genre;

    /**
     * Builds the artists's information by specifying their first name, last name, and genre.
     */

    public Artist(String firstName, String lastName, String genre) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.genre = genre;
    }

    /**
     * Returns the full name of the artist.
     * 
     * @return the artist's full name in "First name" order.
     */

    public String getName() {
        return firstName + " " + lastName;
    }

}
