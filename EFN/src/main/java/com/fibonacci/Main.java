package com.fibonacci;

public class Main {
    public static void main(String[] args) {
        int limit = 4000000;

        Fibonacci fibonacci = new Fibonacci();
        System.out.println("Sum is: " + fibonacci.sum(limit));
    }
}
