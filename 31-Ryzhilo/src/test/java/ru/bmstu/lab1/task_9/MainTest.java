package ru.bmstu.lab1.task_9;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import org.junit.jupiter.api.Test;

class MainTest {

    private static String run(String input) {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        PrintStream output = new PrintStream(buffer, true, StandardCharsets.UTF_8);
        Main.solve(new Scanner(input), output);
        return buffer.toString(StandardCharsets.UTF_8).strip();
    }

    @Test
    void shouldReturnYesForStrictlyAscendingArray() {
        assertEquals("YES", run("5\n1 2 3 4 5"));
    }

    @Test
    void shouldReturnYesWhenAdjacentElementsAreEqual() {
        assertEquals("YES", run("5\n1 2 2 3 3"));
    }

    @Test
    void shouldReturnYesWhenAllElementsAreEqual() {
        assertEquals("YES", run("4\n7 7 7 7"));
    }

    @Test
    void shouldReturnNoForDescendingArray() {
        assertEquals("NO", run("5\n5 4 3 2 1"));
    }

    @Test
    void shouldReturnNoWhenFirstPairIsDescending() {
        assertEquals("NO", run("4\n2 1 3 4"));
    }

    @Test
    void shouldReturnNoWhenLastPairIsDescending() {
        assertEquals("NO", run("4\n1 2 4 3"));
    }

    @Test
    void shouldReturnNoWhenViolationIsInTheMiddle() {
        assertEquals("NO", run("5\n1 2 5 3 6"));
    }

    @Test
    void shouldReturnYesForTwoSortedElements() {
        assertEquals("YES", run("2\n1 2"));
    }

    @Test
    void shouldReturnNoForTwoUnsortedElements() {
        assertEquals("NO", run("2\n2 1"));
    }

    @Test
    void shouldHandleNegativeNumbers() {
        assertEquals("YES", run("4\n-5 -3 -3 0"));
        assertEquals("NO", run("3\n-1 -2 -3"));
    }

    @Test
    void shouldHandleIntegerExtremes() {
        assertEquals("YES", run("2\n-2147483648 2147483647"));
        assertEquals("NO", run("2\n2147483647 -2147483648"));
    }

    @Test
    void shouldAcceptNumbersSplitAcrossLines() {
        assertEquals("YES", run("4\n1\n2\n2\n3"));
    }

    @Test
    void shouldHandleMaximumInputSize() {
        int size = 99_999;
        StringBuilder sorted = new StringBuilder().append(size).append('\n');
        StringBuilder violatedAtEnd = new StringBuilder().append(size).append('\n');
        for (int i = 0; i < size; i++) {
            sorted.append(i).append(' ');
            // последний элемент меньше предыдущего
            violatedAtEnd.append(i < size - 1 ? i : 0).append(' ');
        }
        assertEquals("YES", run(sorted.toString()));
        assertEquals("NO", run(violatedAtEnd.toString()));
    }
}