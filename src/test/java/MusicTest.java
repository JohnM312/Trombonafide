package com.trombonafide;

import org.jfugue.player.Player;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.MockedConstruction;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for {@link Music} class.
 */
class MusicTest {

    @Test
    void playNote_validNote_callsPlayerPlayMethod() {
        try (MockedConstruction<Player> mocked = mockConstruction(Player.class)) {
            // Arrange
            String inputNote = "C5q";  // valid JFugue note

            // Act
            Music.playNote(inputNote);

            // Assert
            Player mockPlayer = mocked.constructed().get(0);
            verify(mockPlayer).play(inputNote);
        }
    }

    @Test
    void playNote_emptyNote_stillCallsPlayerPlay() {
        try (MockedConstruction<Player> mocked = mockConstruction(Player.class)) {
            // Arrange
            String inputNote = "";  // empty string

            // Act
            Music.playNote(inputNote);

            // Assert
            Player mockPlayer = mocked.constructed().get(0);
            verify(mockPlayer).play(inputNote);
        }
    }

    @Test
    void playNote_nullNote_throwsNullPointerException() {
        // Arrange
        String inputNote = null;

        // Act & Assert
        assertThrows(NullPointerException.class, () -> {
            Music.playNote(inputNote);
        });
    }

    @Test
    void playNote_invalidNote_doesNotCrash() {
        try (MockedConstruction<Player> mocked = mockConstruction(Player.class)) {
            // Arrange
            String inputNote = "INVALID_NOTE"; // Invalid format for JFugue

            // Act
            Music.playNote(inputNote);

            // Assert
            Player mockPlayer = mocked.constructed().get(0);
            verify(mockPlayer).play(inputNote);
        }
    }
}
