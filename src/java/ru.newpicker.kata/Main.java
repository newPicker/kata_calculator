package ru.newpicker.kata;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        String input = getString();
        String[] arr = input.split(" ");

        isCorrectData(arr);
        boolean usedRomanNumbers;
        boolean usedArabicNumbers;

        try {
            usedRomanNumbers = isRomanNumbers(arr);
        }
        catch (IllegalArgumentException ex){
            usedRomanNumbers = false;
        }

        try {
            usedArabicNumbers = isArabicNumbers(arr);
        } catch (NumberFormatException ex){
            usedArabicNumbers = false;
        }

        if (usedArabicNumbers && usedRomanNumbers) {
            throw new IOException("ОШИБКА! т.к. используются одновременно разные системы счисления");
        } else if (usedArabicNumbers) {
            calculatorArabic(arr);
        } else if (usedRomanNumbers) {
            calculatorRoman(arr);
        } else {
            throw new IOException("ОШИБКА! т.к. Введены некорректные данные");
        }

}

    private static String getString() {
        // Получаем выраженние, которое нужно посчитать

        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите выражение, которое нужно посчитать: ");

        return scanner.nextLine();
    }

    private static void isCorrectData(String[] arr) throws IOException {
        // Проверяем формат введенного выражения

        if (arr.length > 3) {
            throw new IOException("ОШИБКА! формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        } else if (arr.length < 3) {
            throw new IOException("ОШИБКА! т.к. строка не является математической операцией");
        }
    }
    
    private static boolean isArabicNumbers(String[] arr) throws IOException , NumberFormatException {
        // Проверяем введённые значения - есть ли арабские цифры и входят ли в допустимый диапазон от 1 до 10

        boolean isArabicNumbers = false;
        if (Integer.parseInt(arr[0]) > 10 || Integer.parseInt(arr[2]) > 10) {
            throw new IOException("ОШИБКА! допустимы числа от 1 до 10");
        } else if (Integer.parseInt(arr[0]) <= 10 || Integer.parseInt(arr[2]) <= 10) {
            isArabicNumbers = true;
        }

        return isArabicNumbers;
    }

    private static boolean isRomanNumbers(String[] arr) throws IOException {
        // Проверяем введённые значения - есть ли римские цифры и входят ли в допустимый диапазон от 1 до 10

        boolean isRomanNumbers = false;

        String[] arrRoman = new String[RomanNumber.values().length];
        int i = 0;
        for (RomanNumber el : RomanNumber.values()){
            arrRoman[i++] = el.name();
        }

        for (String el : arrRoman) {
            if( arr[0].equals(el) || arr[2].equals(el) ){
                isRomanNumbers =  true;
            }
        }

        if(isRomanNumbers){
            int firstNumber = RomanNumber.romanToArabic(arr[0]);
            int secondNumber = RomanNumber.romanToArabic(arr[2]);

            if(  firstNumber > 10 || firstNumber < 1
                || secondNumber > 10 || secondNumber < 1 ){
                throw new IOException("ОШИБКА! допустимы числа от 1 до 10");
            }
        }

        return isRomanNumbers;
    }

    private static void calculatorArabic(String[] arr) throws IOException {
        // Считаем полученное выражение с арабскими цифрами

        System.out.println(calculate(Integer.parseInt(arr[0]) , Integer.parseInt(arr[2]) , arr[1] ));
    }

    private static void calculatorRoman(String[] arr) throws IOException {
        // Считаем полученное выражение с римскими цифрами

        int result = calculate(RomanNumber.romanToArabic(arr[0]), RomanNumber.romanToArabic(arr[2]), arr[1]);

        if (result < 1 ) {
            throw new IOException("ОШИБКА! Римские числа только положительные!");
        } else {
            System.out.println(RomanNumber.arabicToRoman(result));
        }
    }

    private static int calculate(int operand1, int operand2, String operand) throws IOException {
        // Считаем полученное выражение с арабскими цифрами

        int result = 0;

        switch (operand) {
            case "+" : result = operand1 + operand2;
                break;
            case "-" : result = operand1 - operand2;
                break;
            case "/" : result = operand1 / operand2;
                break;
            case "*" : result = operand1 * operand2;
                break;
            default:
                throw new IOException("ОШИБКА! введена недопустимая операция!");

        }
        return result;
    }


}
