package ru.bmstu.lab1.task_3;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * Задача 3. Подсчёт вхождений: количество элементов массива, равных x. Сложность по времени - O(n)
 */
public class Main {

    /**
     * Запускает программу с вводом и выводом через консоль.
     */
    public static void main(String[] args) {
        solve(new Scanner(System.in), System.out);
    }


    static void solve(Scanner input, PrintStream output) {
        //выделение памяти под массив, ввод x
        int n = input.nextInt(); //метод NextInt() необходим для считывания целых числе типа int из потока
        int[] numbers = new int[n];

        for (int i = 0; i < n; i++) {
            numbers[i] = input.nextInt();
        }

        int x = input.nextInt();
        int count = 0;

        //поиск вхождений x в массив

        for (int i = 0; i < n; i++) {
            if (numbers[i] == x) {
                count++;
            }
        }

        output.println(count);
    }
}