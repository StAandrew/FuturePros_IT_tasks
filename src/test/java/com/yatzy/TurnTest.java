package com.yatzy;

import com.yatzy.logic.Turn;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TurnTest {
    private static final int min = 1;
    private static final int max = 6;
    private static final int numberOfRolls = 5;

    private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private static final ByteArrayInputStream inContent = new ByteArrayInputStream("".getBytes());
    private static final PrintStream printStream = new PrintStream(outContent);

    @Test
    void go() {
        Turn turn = new Turn(inContent, printStream);
        assertTrue(turn.outputRolledValues().isEmpty());
        turn.go();
        for (int result : turn.outputRolledValues()) {
            assertTrue(result >= min && result <= max, "Dice roll is out of range: " + result);
        }
    }

    @Test
    void choose() {
        String data = "1 2 3 4 5\n";
        Turn turn = new Turn(new ByteArrayInputStream(data.getBytes()), printStream);
        List<Integer> initial = turn.go().outputRolledValues();
        turn.chooseToRollAgain();
        assertNotEquals(initial, turn.outputRolledValues());
    }

    @Test
    void display() {
        outContent.reset();
        String outputIfEmpty = "List is empty\n";
        String correctOutput = "";

        Turn turn = new Turn(inContent, printStream);
        turn.display();
        assertEquals(outputIfEmpty, outContent.toString());

        outContent.reset();
        turn.go();
        for (int i : turn.outputRolledValues()) {
            correctOutput = correctOutput.concat(i + "\n");
        }
        turn.display();
        assertEquals(correctOutput, outContent.toString());
    }

    @Test
    void chance() {
        Turn turn = new Turn(inContent, printStream);
        turn.go();
        int sum = 0;
        for (int r : turn.outputRolledValues()) {
            sum += r;
        }
        assertEquals(sum, turn.calculateChance(), "Wrong Chance calculation");
    }

    @Test
    void onePair() {
        Turn turn = new Turn(inContent, printStream);
        int maxScore = 0;
        List<Integer> rolledList = turn.go().outputRolledValues();
        List<Integer> pairs = new ArrayList<>();
        for (int i = 0; i < rolledList.size(); i++) {
            for (int j = i + 1; j < rolledList.size(); j++) {
                if(rolledList.get(i).equals(rolledList.get(j))) {
                    pairs.add(rolledList.get(i));
                    rolledList.remove(i);
                    rolledList.remove(j-1);
                    i = 0;
                    j = 0;
                }
            }
        }
        for (int pair : pairs) {
            if (2*pair > maxScore)
                maxScore = 2*pair;
        }
        assertEquals(maxScore, turn.calculateOnePair(), "Wrong Pair calculation");
    }

    @Test
    void fives() {
        Turn turn = new Turn(inContent, printStream);
        turn.go();
        int sum = 0;
        for (int r : turn.outputRolledValues()) {
            if (r == 5)
                sum += r;
        }
        assertEquals(turn.calculateFives(), sum, "Wrong Fives calculation");
    }

    @Test
    void getRolled() {
        Turn turn = new Turn(inContent, printStream);
        turn.go();
        assertEquals(turn.outputRolledValues().size(), numberOfRolls, "Wrong size");
        for (int r : turn.outputRolledValues()) {
            assertTrue(r >= min && r <= max, "Value out of range: " + r);
        }
    }
}