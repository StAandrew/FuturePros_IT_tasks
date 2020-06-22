package com.yatzy;

import com.yatzy.logic.IntegerArrayListAsker;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IntegerArrayListAskerTest {
    private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @ParameterizedTest(name="Run {index}: min={0}, max={1}")
    @MethodSource("testParameters")
    void ask(int min, int max) {
        String data = "";
        String message = "test";
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = min; i <= max; i++) {
            data = data.concat(i +  " ");
            arrayList.add(i);
        }
        data = data.concat("\n");
        IntegerArrayListAsker asker = new IntegerArrayListAsker(
                        new ByteArrayInputStream(data.getBytes()),
                        new PrintStream(outContent));
        assertEquals(arrayList, asker.ask(message));
        assertEquals(message + "\n", outContent.toString());
        outContent.reset();
    }

    static Stream<Arguments> testParameters() {
        return Stream.of(
                Arguments.of(1, 9),
                Arguments.of(1, 5),
                Arguments.of(5, 7),
                Arguments.of(0, 0),
                Arguments.of(9, 9),
                Arguments.of(5, 6)
        );
    }
}