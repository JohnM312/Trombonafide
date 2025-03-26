package com.model;

public class Artist {
    private String firstName;
    private String lastName;
    private String genre;

    public Artist(String firstName, String lastName, String genre) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.genre = genre;
    }

    public String getName() {
        return firstName + " " + lastName;
    }

    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getGenre() { return genre; }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", genre='" + genre + '\'' +
                '}';
    }
}
