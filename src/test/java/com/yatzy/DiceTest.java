package com.yatzy;

import com.yatzy.logic.Dice;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiceTest {
    private static final int min = 1;
    private static final int max = 6;

    @Test
    void roll() {
        Dice dice = new Dice();
        dice.roll();
        assertNotNull(dice);
    }

    @Test
    void getScore() {
        Dice dice = new Dice();
        int result = dice.getScore();
        assertTrue(result >= min && result <= max, "Dice roll is out of range: " + result);
    }
}