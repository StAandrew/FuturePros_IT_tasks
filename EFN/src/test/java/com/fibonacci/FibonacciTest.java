package com.fibonacci;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FibonacciTest {

    @Test
    void sum() {
        Fibonacci fibonacci = new Fibonacci();
        int limit = 4000000;
        assertEquals(4613732, fibonacci.sum(limit));
    }
}