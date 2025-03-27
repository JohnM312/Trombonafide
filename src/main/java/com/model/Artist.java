package com.model;

public class Artist {
    private String firstName;
    private String lastName;
    private String genre;

    public Artist() {}

    public Artist(String firstName, String lastName, String genre) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.genre = genre;
    }

    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getGenre() { return genre; }

    @Override
    public String toString() {
        return "Artist{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", genre='" + genre + '\'' +
                '}';
    }
}
