package com.model;

/**
 * Represents a music artist with a name and genre.
 * Holds basic details like first name, last name, and the type of music they perform.
 */
public class Artist {
    private String firstName;
    private String lastName;
    private String genre;

    /**
     * Empty constructor needed for certain frameworks or file loading.
     */
    public Artist() {}

    /**
     * Creates a new Artist with the given name and genre.
     *
     * @param firstName The artist’s first name
     * @param lastName  The artist’s last name
     * @param genre     The style of music the artist makes
     */
    public Artist(String firstName, String lastName, String genre) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.genre = genre;
    }

    /**
     * @return The artist’s first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @return The artist’s last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @return The genre of music the artist creates
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Returns a simple string showing the artist’s info.
     *
     * @return A formatted string with the artist’s details
     */
    @Override
    public String toString() {
        return "Artist{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", genre='" + genre + '\'' +
                '}';
    }
}
