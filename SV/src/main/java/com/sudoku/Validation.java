package com.sudoku;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

class Validation {

    Validation() {
    }

    boolean ValidateSolution (int[][] input) {
        int sum = 0, testSumRows = 0, testSumColumns = 0;
        // get sum of first row
        for (int i = 0; i < input[0].length; i++) {
            sum += input[0][i];
        }

        // testing each row
        for (int row = 0; row < input.length; row++) {
            for (int col = 0; col < input[row].length; col++) {
                testSumRows += input[row][col];
                testSumColumns += input[col][row];
            }
            if (testSumRows != sum || testSumColumns != sum)
                return false;
            testSumRows = 0;
            testSumColumns = 0;
        }

        // edge cases
        ArrayList<Integer> testingCollection= new ArrayList<>();
        for (int i = 0; i < input.length; i++) {
            testingCollection.add(i);
            for (int row = 0; row < input.length; row++) {
                int amount = Collections.frequency(Collections.singletonList(Arrays.stream(input[0]).boxed().collect(Collectors.toList())), testingCollection);
                if (amount > 1)
                    return false;
            }
            testingCollection.clear();
        }

        return true;
    }
}
