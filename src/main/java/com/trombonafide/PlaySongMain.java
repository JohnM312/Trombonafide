package com.trombonafide;

import com.model.Song;
import com.trombonafide.util.DataLoader;
import com.trombonafide.util.MusicPlayer;

import java.util.List;
import java.util.Scanner;

public class PlaySongMain {
    public static void main(String[] args) {
        List<Song> songs = DataLoader.loadSongs();

        if (songs.isEmpty()) {
            System.out.println("No songs found in Song.json.");
            return;
        }

        System.out.println("Available Songs:");
        for (int i = 0; i < songs.size(); i++) {
            System.out.println((i + 1) + ". " + songs.get(i).getTitle() + " by " + songs.get(i).getArtist());
        }

        System.out.print("\nEnter the number of the song to play: ");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice >= 1 && choice <= songs.size()) {
            Song selectedSong = songs.get(choice - 1);
            MusicPlayer.playSong(selectedSong);
        } else {
            System.out.println("Invalid selection.");
        }

        scanner.close();
    }
}