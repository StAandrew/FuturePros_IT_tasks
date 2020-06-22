package com.yatzy.gameplay;

import com.yatzy.logic.Turn;

import java.io.InputStream;
import java.io.PrintStream;

public class Game {
    private final PrintStream printStream;
    private final InputStream inputStream;

    public Game(InputStream inputStream, PrintStream printStream) {
        this.inputStream = inputStream;
        this.printStream = printStream;
    }

    boolean play(Player player) {
        Turn turn = new Turn(inputStream, printStream);
        printStream.println(player.getName() + "Turn: " + player.getTurnNumber());
        turn.go().display().chooseToRollAgain().displayChoices();

        player.addChoice(turn.chooseCategory(player.getPastChoices()));

        int turnScore;
        int choice = player.getPastChoices().get(player.getTurnNumber() - 1);
        if (choice == 1)
            turnScore = turn.calculateChance();
        else if (choice == 2)
            turnScore = turn.calculateOnePair();
        else if (choice == 3)
            turnScore = turn.calculateFives();
        else
            return false;
        player.addScore(turnScore);

        printStream.println("This turn's score " + turnScore);
        printStream.println("Total score: " + player.getScore() + "\n");
        return true;
    }
}
