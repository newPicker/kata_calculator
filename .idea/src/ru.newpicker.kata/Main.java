package ru.newpicker.kata;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String input = getString();

    }


    private static String getString() {
        // Получаем выраженние, которое нужно посчитать

        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите выражение, которое нужно посчитать: ");

        return scanner.nextLine();
    }


}
