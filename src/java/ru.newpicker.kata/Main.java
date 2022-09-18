package ru.newpicker.kata;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    /** Тестовые примеры:
    5 + 5 -> 10
    V + V -> 10
    12 + 12 -> ошибка (от 1 до 10)
    XV + X -> ошибка (от 1 до 10)
    V - X -> ошибка (в римской системе нет отрицательных чисел)
    10 / -> ошибка (строка не является математической операцией)
    V + -> ошибка (строка не является математической операцией)
    5 + 5 + 5 -> ошибка (формат математической операции не удовлетворяет заданию)
    V + V + V -> ошибка (формат математической операции не удовлетворяет заданию)
    5 + V -> ошибка (используются одновременно разные системы счисления)
    sdgfv + asf -> ошибка (некорректные данные)
    2.2 + 5 -> ошибка (некорректные данные)
    2.2 + V -> ошибка (некорректные данные)
    I + VIII -> IX
    V - I -> IV
    X / IV -> II
    X / IX -> I
    II * IV -> VIII
    II * IX -> XVIII
    IX * IX -> LXXXI
    */

    public static void main(String[] args) throws IOException {

    String input = getString();
    isCorrectData(input);
    String[] arr = input.split(" ");

    int systemOfOperand1 = systemOfOperand(arr[0]); // 0 - некорректные данные, 1 - араб, 2 - рим
    int systemOfOperand2 = systemOfOperand(arr[2]); // 0 - некорректные данные, 1 - араб, 2 - рим

    if(systemOfOperand1 == 2 && systemOfOperand2 == 2) {
        isValidRomanNumbers(arr);
        calculatorRoman(arr);
    } else if (systemOfOperand1 == 1 && systemOfOperand2 == 1) {
        isValidArabicNumbers(arr);
        calculatorArabic(arr);
    } else {
        throw new IOException("ОШИБКА! т.к. используются одновременно разные системы счисления");
    }
}

    private static String getString() {
        // Получаем выраженние, которое нужно посчитать

        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите выражение, которое нужно посчитать: ");

        return scanner.nextLine();
    }

    private static void isCorrectData(String input) throws IOException {
        // Проверяем формат введенного выражения

        String[] arr = input.split(" ");

        if (arr.length > 3) {
            throw new IOException("ОШИБКА! формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        } else if (arr.length < 3) {
            throw new IOException("ОШИБКА! т.к. строка не является математической операцией");
        }
    }

    private static int systemOfOperand(String s) throws IOException {
        // Проверяем, какая система счисления у операнда

        int operandSystemArabic = 0;
        int operandSystemRoman = 0;

        try {
            Integer.parseInt(s);
            operandSystemArabic = 1;

        } catch (NumberFormatException ex) {}

        if(operandSystemArabic != 1){
            for (int i = 0; i < s.length(); i++) {
                for ( RomanNumber rn: RomanNumber.values()) {
                    String symbol = String.valueOf(s.charAt(i));
                    if ( symbol.equals( rn.toString() ) ) {
                        operandSystemRoman = 1;
                        break;
                    } else {
                        operandSystemRoman = 0;
                    }
                }
            }
        }

        if (operandSystemArabic != 1 && operandSystemRoman != 1) {
            throw new IOException("ОШИБКА! т.к. Введены некорректные данные");
        } else if (operandSystemArabic == 1) {
            return 1;
        } else {
            return 2;
        }
    }
    
    private static void isValidArabicNumbers(String[] arr) throws IOException , NumberFormatException {
        // Проверяем введённые значения - есть ли арабские цифры и входят ли в допустимый диапазон от 1 до 10

        int firstNumber = Integer.parseInt(arr[0]);
        int secondNumber = Integer.parseInt(arr[2]);

        if( firstNumber > 10 || firstNumber < 0 || secondNumber > 10 || secondNumber < 0) {
            throw new IOException("ОШИБКА! допустимы числа от 1 до 10");
        }
    }

    private static void isValidRomanNumbers(String[] arr) throws IOException , IllegalArgumentException {
        // Проверяем введённые значения - есть ли римские цифры и входят ли в допустимый диапазон от 1 до 10

        int firstNumber = RomanNumber.romanToArabic(arr[0]);
        int secondNumber = RomanNumber.romanToArabic(arr[2]);

        if (firstNumber == 0 && secondNumber == 0) {
            throw new IOException("ОШИБКА! допустимы числа от 1 до 10");
        }

        if (firstNumber > 10 || firstNumber < 0 || secondNumber > 10 || secondNumber < 0 ) {
            throw new IOException("ОШИБКА! допустимы числа от 1 до 10");
        }
    }

    private static void calculatorArabic(String[] arr) throws IOException {
        // Считаем полученное выражение с арабскими цифрами

        try {
            System.out.println(calculate(Integer.parseInt(arr[0]), Integer.parseInt(arr[2]), arr[1]));
        }catch (NumberFormatException ex){
            throw new IOException("ОШИБКА! т.к. Введены некорректные данные");
        }

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
        // Основа калькулятора

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
