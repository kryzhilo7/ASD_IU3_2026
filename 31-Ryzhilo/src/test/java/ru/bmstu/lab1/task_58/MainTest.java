package ru.bmstu.lab1.task_58;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import org.junit.jupiter.api.Test;

/** Граничные и функциональные тесты задачи 58. */
class MainTest {

    @Test
    void shouldHandleRectangle() {
        assertEquals("1 2 3\n6 5 4\n", run("2 3\n"));
    }

    @Test
    void shouldHandleSingleCell() {
        assertEquals("1\n", run("1 1\n"));
    }

    @Test
    void shouldHandleNegativeOrDiagonal() {
        assertEquals("1 2\n4 3\n5 6\n", run("3 2\n"));
    }

    @Test
    void shouldHandleEqualOrConnected() {
        assertEquals("1 2 3 4\n", run("1 4\n"));
    }

    @Test
    void shouldHandleLimitsOrEmpty() {
        assertEquals("1\n2\n3\n4\n", run("4 1\n"));
    }

    @Test
    void shouldHandleColumn() {
        assertEquals("1 2 3\n6 5 4\n7 8 9\n", run("3 3\n"));
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
