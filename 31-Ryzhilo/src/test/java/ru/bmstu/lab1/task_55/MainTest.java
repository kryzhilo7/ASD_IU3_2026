package ru.bmstu.lab1.task_55;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import org.junit.jupiter.api.Test;

/** Граничные и функциональные тесты задачи 55. */
class MainTest {

    @Test
    void shouldHandleRectangle() {
        assertEquals("15\n", run("3\n1 2 3\n4 5 6\n7 8 9\n"));
    }

    @Test
    void shouldHandleSingleCell() {
        assertEquals("9\n", run("1\n9\n"));
    }

    @Test
    void shouldHandleNegativeOrDiagonal() {
        assertEquals("-3\n", run("2\n-3 -2\n-1 -4\n"));
    }

    @Test
    void shouldHandleEqualOrConnected() {
        assertEquals("14\n", run("2\n7 7\n7 7\n"));
    }

    @Test
    void shouldHandleLimitsOrEmpty() {
        assertEquals("-1\n", run("2\n2147483647 -2147483648\n2147483647 -2147483648\n"));
    }

    @Test
    void shouldHandleColumn() {
        assertEquals("3\n", run("2\n0 1\n2 3\n"));
    }

    private static String run(String inputText) {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        try (Scanner input = new Scanner(inputText);
             PrintStream output = new PrintStream(buffer, true, StandardCharsets.UTF_8)) {
            Main.solve(input, output);
        }
        return buffer.toString(StandardCharsets.UTF_8).replace("\r\n", "\n");
    }
}
