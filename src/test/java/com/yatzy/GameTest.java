package com.yatzy;

import com.yatzy.gameplay.Game;
import com.yatzy.gameplay.Player;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class GameTest {
    private static final String playerName = "";

    private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private static final ByteArrayInputStream inContent = new ByteArrayInputStream("".getBytes());
    private static final PrintStream originalOut = System.out;
    private static final InputStream originalIn = System.in;

    @BeforeAll
    static void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setIn(inContent);
    }

    @AfterAll
    static void restoreStreams() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    @Test
    void play() {
        Game game = new Game(System.in, System.out);
        Player player = new Player(playerName);
        //assertTrue(game.play(player));
    }
}