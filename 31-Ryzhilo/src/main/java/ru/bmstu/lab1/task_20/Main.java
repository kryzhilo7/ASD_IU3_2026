package ru.bmstu.lab1.task_20;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * Задача 20. Сортировка подсчётом: сортировка массива из n целых чисел по неубыванию
 * методом подсчёта. Сложность по времени - O(n + k), где k - разброс значений (max - min + 1)
 */
public class Main {

    /**
     * Максимально допустимый разброс значений: массив счётчиков такого размера занимает около 40 МБ.
     */
    static final int MAX_RANGE = 10_000_000;

    /**
     * Запускает программу с вводом и выводом через консоль.
     */
    public static void main(String[] args) {
        solve(new Scanner(System.in), System.out);
    }

    /**
     * Читает n и массив из n чисел, затем печатает отсортированный массив через пробел.
     */
    static void solve(Scanner input, PrintStream output) {
        //выделение памяти под массив
        int n = input.nextInt(); //метод nextInt() необходим для считывания целых чисел типа int из потока
        int[] numbers = new int[n];

        for (int i = 0; i < n; i++) {
            numbers[i] = input.nextInt();
        }

        int[] sorted = countingSort(numbers);

        //StringBuilder вместо конкатенации в цикле, иначе вывод был бы O(n^2)
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < sorted.length; i++) {
            if (i > 0) {
                result.append(' ');
            }
            result.append(sorted[i]);
        }

        output.println(result);
    }

    /**
     * Сортирует массив подсчётом и возвращает новый массив; исходный не изменяется.
     *
     * @throws IllegalArgumentException если разброс значений max - min + 1 больше {@link #MAX_RANGE}
     */
    static int[] countingSort(int[] numbers) {
        if (numbers.length == 0) {
            return new int[0];
        }

        int min = numbers[0];
        int max = numbers[0];

        for (int value : numbers) {
            min = Math.min(min, value);
            max = Math.max(max, value);
        }

        //разброс считаем в long: max - min для int может переполниться
        long range = (long) max - min + 1;

        if (range > MAX_RANGE) {
            throw new IllegalArgumentException("Value range " + range + " is too large for counting sort");
        }

        //counts[v - min] - сколько раз значение v встретилось; сдвиг на min позволяет работать с отрицательными
        int[] counts = new int[(int) range];

        for (int value : numbers) {
            counts[value - min]++;
        }

        //проходим по счётчикам от меньшего значения к большему и выписываем каждое значение столько раз,
        //сколько оно встретилось
        int[] sorted = new int[numbers.length];
        int position = 0;

        for (int offset = 0; offset < counts.length; offset++) {
            for (int repeat = 0; repeat < counts[offset]; repeat++) {
                sorted[position++] = min + offset;
            }
        }

        return sorted;
    }
}