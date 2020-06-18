package com.yatzy.logic;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class IntegerArrayListAsker {
    private final Scanner scanner;
    private final PrintStream out;

    public IntegerArrayListAsker(InputStream in, PrintStream out) {
        scanner = new Scanner(in).useDelimiter(" *");
        this.out = out;
    }

    public ArrayList<Integer> ask(String message) {
        out.println(message);
        ArrayList<Integer> input = new ArrayList<>();
        while(scanner.hasNextInt() && scanner.hasNext()) {
            input.add(scanner.nextInt());
        }
        return input;
    }
}
