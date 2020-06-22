package com.fibonacci;

class Fibonacci {
    Fibonacci() {
    }

    Integer sum(final int limit) {
        int totalSum = 0, thisTerm = 0, lastTerm = 1, lastLastTerm = 0;

        while (thisTerm < limit) {
            thisTerm = lastTerm + lastLastTerm;
            lastLastTerm = lastTerm;
            lastTerm = thisTerm;
            // check if even
            if(thisTerm % 2 == 0)
                totalSum += thisTerm;
        }
        return totalSum;
    }
}
