package com.yatzy;

import com.yatzy.gameplay.Player;
import org.junit.jupiter.api.Test;

import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    private static final int bound = 100;
    private static final String playerName = "";

    @Test
    void getScore() {
        Player player = new Player(playerName);
        assertEquals(0, player.getScore());
    }

    @Test
    void addScore() {
        Player player = new Player(playerName);
        Random random = new Random();
        int toAdd, total = 0;
        for (int i = 0; i < 10; i++) {
            toAdd = random.nextInt(bound);
            player.addScore(toAdd);
            total += toAdd;
            assertEquals(total, player.getScore());
        }
    }

    @Test
    void resetScore() {
        Player player = new Player(playerName);
        int random = new Random().nextInt(bound);
        player.addScore(random);
        player.reset();
        assertEquals(0, player.getScore());
    }
}