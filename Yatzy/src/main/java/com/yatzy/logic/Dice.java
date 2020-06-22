package com.yatzy.logic;

import java.util.Random;

public class Dice {
    private int score;

    public Dice() {
        roll();
    }

    public Dice roll() {
        score = new Random().nextInt(6) + 1;
        return this;
    }

    public int getScore() {
        return score;
    }
}
