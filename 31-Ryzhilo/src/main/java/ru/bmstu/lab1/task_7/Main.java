package ru.bmstu.lab1.task_7;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * Задача 7. Второй максимум: второй по величине элемент массива (все элементы различны).
 * Сложность по времени - O(n)
 */
public class Main {

    /**
     * Запускает программу с вводом и выводом через консоль.
     */
    public static void main(String[] args) {
        solve(new Scanner(System.in), System.out);
    }

    /**
     * Читает n и массив из n различных чисел, затем печатает второй по величине элемент.
     */
    static void solve(Scanner input, PrintStream output) {
        //выделение памяти под массив
        int n = input.nextInt(); //метод nextInt() необходим для считывания целых чисел типа int из потока
        int[] numbers = new int[n];

        for (int i = 0; i < n; i++) {
            numbers[i] = input.nextInt();
        }

        //начальные значения берём из первых двух элементов, а не Integer.MIN_VALUE:
        //так результат верен и тогда, когда в массиве сам элемент Integer.MIN_VALUE
        int max = Math.max(numbers[0], numbers[1]);
        int secondMax = Math.min(numbers[0], numbers[1]);

        //за один проход поддерживаем два наибольших значения
        for (int i = 2; i < n; i++) {
            if (numbers[i] > max) {
                secondMax = max;
                max = numbers[i];
            } else if (numbers[i] > secondMax) {
                secondMax = numbers[i];
            }
        }

        output.println(secondMax);
    }
}