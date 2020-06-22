package com.yatzy.gameplay;

import java.util.ArrayList;

public class Player {
    private int score;
    private int turnNumber;
    private ArrayList<Integer> pastChoices;
    private String name;

    public Player(String name) {
        this.name = name;
        score = 0;
        turnNumber = 1;
        pastChoices = new ArrayList<>();
    }

    public int getScore() {
        return score;
    }

    public void addScore(int addScore) {
        score += addScore;
        turnNumber++;
    }

    public void reset() {
        score = 0;
        turnNumber = 1;
    }

    String getName() {
        if (name.equals(""))
            return name;
        return name + " ";
    }

    int getTurnNumber() {
        return turnNumber;
    }

    ArrayList<Integer> getPastChoices() {
        return new ArrayList<>(pastChoices);
    }

    void addChoice(int choice) {
        pastChoices.add(choice);
    }
}
