package ru.newpicker.kata;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public enum RomanNumber {
    // Для перечисления римских цифр перечисляться должно в порядке убывания

    C(100),
    L(50),
    X(10),
    V(5),
    I(1);

    int arabicValue;
    RomanNumber(int value) {
        this.arabicValue = value;
    }

    static int romanToArabic(String roman) throws IllegalArgumentException {

        String romanNumeral = roman;
        int result = 0;

        // создаётся список всех римских чисел, которые объявлены вверху
        List<RomanNumber> romanNumerals = Arrays.asList(RomanNumber.values());

        // цикл чтобы пробежаться по всем числам из этого списка
        int i = 0;

        // пока (длинна полученной на входе в метод строки > 0 И i < текущего элемента списка)
        while ((romanNumeral.length() > 0) && (i < romanNumerals.size())) {

            // в переменную symbol сохраняем текущий элемент списка
            RomanNumber symbol = romanNumerals.get(i);

            // если ???? от названия текущего элемента списка
            if (romanNumeral.startsWith(symbol.name())) {

                // в result плюсуем число-значение текущего элемента списка
                result += symbol.arabicValue;

                // в строку, полученную на входе в метод, сохраняем ????
                romanNumeral = romanNumeral.substring(symbol.name().length());

                // иначе переходим к следующему элементу списка всех римских чисел
            } else {
                i++;
            }
        }

        return result;
    }

    static String arabicToRoman(int arabic){

        // создаётся список всех римских чисел, которые объявлены вверху
        List<RomanNumber> romanNumerals = Arrays.asList(RomanNumber.values());

        // цикл чтобы пробежаться по всем числам из этого списка
        int i = 0;

        // создаем коробку, в которой будем сохранять строку-римское число
        StringBuilder sb = new StringBuilder();

        // пока (полученное на входе в метод число >0 И i < текущего элемента списка)
        while ((arabic > 0) && (i < romanNumerals.size())) {

            // в переменную currentSymbol сохраняем текущий элемент списка
            RomanNumber currentSymbol = romanNumerals.get(i);

            // если (число-значение текущего элемента списка <= полученного на входе в метод число)
            if (currentSymbol.arabicValue <= arabic) {

                // для коробки ???? римское название текущего элемента списка
                sb.append(currentSymbol.name());

                // от полученного на входе в метод числа отнимаем число-значение текущего элемента списка
                arabic -= currentSymbol.arabicValue;
            } else {

                // иначе переходим к следующему элементу списка всех римских чисел
                i++;
            }
        }

        return sb.toString();
    }

}