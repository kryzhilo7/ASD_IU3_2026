package ru.bmstu.lab1.task_20;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
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
    void shouldSortUnsortedArray() {
        assertEquals("1 2 3 4 5", run("5\n3 1 5 2 4"));
    }

    @Test
    void shouldKeepAlreadySortedArray() {
        assertEquals("1 2 3 4", run("4\n1 2 3 4"));
    }

    @Test
    void shouldSortDescendingArray() {
        assertEquals("1 2 3 4 5", run("5\n5 4 3 2 1"));
    }

    @Test
    void shouldKeepAllDuplicates() {
        assertEquals("1 1 2 2 2 3", run("6\n2 1 3 2 1 2"));
    }

    @Test
    void shouldHandleAllEqualElements() {
        assertEquals("7 7 7 7", run("4\n7 7 7 7"));
    }

    @Test
    void shouldSortNegativeNumbers() {
        assertEquals("-9 -4 -4 -1", run("4\n-4 -1 -9 -4"));
    }

    @Test
    void shouldSortNumbersAroundZero() {
        assertEquals("-3 -1 0 0 2 5", run("6\n2 -1 0 5 -3 0"));
    }

    @Test
    void shouldHandleSingleElement() {
        assertEquals("42", run("1\n42"));
    }

    @Test
    void shouldPrintEmptyLineForEmptyArray() {
        assertEquals("", run("0"));
    }

    @Test
    void shouldHandleValuesNearIntegerMaximum() {
        // сдвиг на минимум позволяет сортировать большие значения при малом разбросе
        assertEquals("2147483645 2147483646 2147483647 2147483647",
                run("4\n2147483647 2147483645 2147483647 2147483646"));
    }

    @Test
    void shouldHandleValuesNearIntegerMinimum() {
        assertEquals("-2147483648 -2147483647 -2147483646", run("3\n-2147483646 -2147483648 -2147483647"));
    }

    @Test
    void shouldRejectTooLargeValueRange() {
        assertThrows(IllegalArgumentException.class, () -> run("2\n-2147483648 2147483647"));
        assertThrows(IllegalArgumentException.class, () -> Main.countingSort(new int[] {0, Main.MAX_RANGE}));
    }

    @Test
    void shouldAcceptMaximumAllowedValueRange() {
        int[] numbers = {Main.MAX_RANGE - 1, 0};

        assertArrayEquals(new int[] {0, Main.MAX_RANGE - 1}, Main.countingSort(numbers));
    }

    @Test
    void shouldNotModifySourceArray() {
        int[] numbers = {3, 1, 2};

        Main.countingSort(numbers);

        assertArrayEquals(new int[] {3, 1, 2}, numbers);
    }

    @Test
    void shouldAcceptNumbersSplitAcrossLines() {
        assertEquals("1 2 3", run("3\n3\n1\n2"));
    }

    @Test
    void shouldHandleMaximumInputSize() {
        int size = 99_999;
        int[] numbers = new int[size];
        StringBuilder input = new StringBuilder().append(size).append('\n');
        // псевдослучайные значения в диапазоне -500..499; эталон строим стандартной сортировкой
        for (int i = 0; i < size; i++) {
            numbers[i] = (int) ((i * 7919L) % 1000) - 500;
            input.append(numbers[i]).append(' ');
        }
        int[] expected = numbers.clone();
        Arrays.sort(expected);

        assertArrayEquals(expected, Main.countingSort(numbers));
        assertEquals(Arrays.toString(expected).replace("[", "").replace("]", "").replace(",", ""),
                run(input.toString()));
    }
}
