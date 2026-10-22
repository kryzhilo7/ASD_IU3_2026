package ru.bmstu.lab1.task_11;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * Задача 11. Ближайший к среднему: элемент массива, ближайший к среднему арифметическому.
 * Если таких несколько - вывести первый по индексу. Среднее может быть дробным.
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
     * Читает n и массив из n чисел, затем печатает элемент, ближайший к среднему арифметическому.
     */
    static void solve(Scanner input, PrintStream output) {
        //выделение памяти под массив
        int n = input.nextInt(); //метод nextInt() необходим для считывания целых чисел типа int из потока
        int[] numbers = new int[n];

        //сумма хранится в long: при n < 10^5 сумма значений типа int может выйти за пределы int
        long sum = 0;

        for (int i = 0; i < n; i++) {
            numbers[i] = input.nextInt();
            sum += numbers[i];
        }

        //Расстояние |a - sum / n| умножаем на n и получаем |a * n - sum|: сравнение идёт в целых числах,
        //без дробного среднего и ошибок округления double. Максимум около 2^31 * 10^5 помещается в long
        int closest = numbers[0];
        long minDistance = Math.abs((long) numbers[0] * n - sum);

        //строгое сравнение: при равных расстояниях остаётся элемент с меньшим индексом
        for (int i = 1; i < n; i++) {
            long distance = Math.abs((long) numbers[i] * n - sum);
            if (distance < minDistance) {
                minDistance = distance;
                closest = numbers[i];
            }
        }

        output.println(closest);
    }
}