package com.yatzy.logic;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Turn {
    private List<Integer> rolled;
    private Dice dice;
    private int rollNumber;
    private final PrintStream printStream;
    private final InputStream inputStream;

    private static final int min = 1;
    private static final int max = 6;
    private static final int numberOfRolls = 5;
    private static final int numberOfCategories = 3;

    public Turn(InputStream inputStream, PrintStream printStream) {
        rolled = new ArrayList<>();
        dice = new Dice();
        this.inputStream = inputStream;
        this.printStream = printStream;
    }

    public Turn go() {
        for (int i = 0; i < 5; i++)
            rolled.add(dice.roll().getScore());
        rollNumber = 1;
        return this;
    }

    public Turn chooseToRollAgain() {
        ArrayList<Integer> choices = getInputToRollAgain(new IntegerArrayListAsker(inputStream, printStream));
        int i = 0;
        if (choices.size() > 0) {
            for (int choice : choices) {
                rolled.remove(choice - i - 1);
                i++;
            }
            for (; i > 0; i--)
                rolled.add(dice.roll().getScore());
            rollNumber++;
            this.display();
            if (rollNumber <= 2) {
                this.chooseToRollAgain();
            }
        }
        return this;
    }

    public int chooseCategory(ArrayList<Integer> pastChoices) {
        String error;
        ArrayList<Integer> choices = new IntegerArrayListAsker(inputStream, printStream).ask("Choose a category for this turn: ");
        while (choices.size() > 1 || !inputChooseCategoryChecker(choices) || choices.isEmpty() || pastChoices.contains(choices.get(0))) {
            if (choices.isEmpty()) {
                error = "Choose a category";
            }
            else if (choices.size() > 1)
                error = "You can choose only one category";
            else if (pastChoices.contains(choices.get(0)))
                error = "Category already chosen";
            else
                error = "Wrong number";
            choices = new IntegerArrayListAsker(inputStream, printStream).ask(error + ", try again: ");
        }
        return choices.get(0);
    }

    private ArrayList<Integer> getInputToRollAgain(IntegerArrayListAsker asker) {
        ArrayList<Integer> choices = asker.ask("Choose which dice to roll again (space separated, enter for none):");
        while (choices.size() > numberOfRolls || !inputToRollAgainChecker(choices)) {
            choices = asker.ask("Wrong number, try again: ");
        }
        return choices;
    }

    private boolean inputToRollAgainChecker(ArrayList<Integer> input) {
        for (int integer : input) {
            if (integer < min || integer > max)
                return false;
        }
        return true;
    }

    private boolean inputChooseCategoryChecker(ArrayList<Integer> input) {
        for (int integer : input) {
            if (integer < 1 || integer > numberOfCategories)
                return false;
        }
        return true;
    }

    public List<Integer> outputRolledValues() {
        return new ArrayList<>(rolled);
    }
    
    public Turn display() {
        //this.sort();
        if (rolled.isEmpty())
            printStream.println("List is empty");
        else
            rolled.forEach(printStream::println);
        return this;
    }

    public Turn displayChoices() {
        printStream.println("\n(1) Chance: " + this.calculateChance());
        printStream.println("(2) Pair: " + this.calculateOnePair());
        printStream.println("(3) Fives: " + this.calculateFives() + "\n");
        return this;
    }

    private Turn sort() {
        rolled.sort(Collections.reverseOrder());
        return this;
    }

    public int calculateChance() {
        int score = 0;
        for (int roll : rolled)
            score += roll;
        return score;
    }

    public int calculateOnePair() {
        int score = 0;
        List<Integer> sortedRolled = this.outputRolledValues();
        sortedRolled.sort(Collections.reverseOrder());
        for (int i = 0; i < sortedRolled.size()-1; i++) {
            if (sortedRolled.get(i).equals(sortedRolled.get(i+1))) {
                if (2*sortedRolled.get(i) > score)
                    score = 2*sortedRolled.get(i);
            }
        }
        return score;
    }

    public int calculateFives() {
        int score = 0;
        for (int r : rolled) {
            if (r == 5) {
                score += 5;
            }
        }
        return score;
    }
}
