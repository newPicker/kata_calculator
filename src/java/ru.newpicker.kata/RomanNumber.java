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

    // Метод для перевода римских цифр в числовое значение (арабскими цифрами)
    static int romanToArabic(String roman) throws IllegalArgumentException {

        int result = 0;
        int buffsum = 0;

        if(roman.length() == 1){
            return RomanNumber.valueOf(roman).arabicValue;
        }

        int currentNumber;
        int nextNumber;

        for(int i = 0 ; i < roman.length() ; i++){
            currentNumber = RomanNumber.valueOf(String.valueOf(roman.charAt(i))).arabicValue;

            // Если дошли до последнего символа, то нужно посчитать сумму
            if(i == roman.length()-1){
                result += currentNumber + buffsum;
                break;
            }

            nextNumber = RomanNumber.valueOf(String.valueOf(roman.charAt(i+1))).arabicValue;

            // Если текущий символ меньше следующего, то нужно вычисть накомленную сумму
            if ( currentNumber < nextNumber) {
                result += buffsum - currentNumber;
                buffsum = 0;
            }else{ // Иначе накапливаем сумму
                buffsum += currentNumber;
            }

        }

        return result;
    }

    // Метод для перевода арабских цифр в римские
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

                // для коробки добавляется римское название текущего элемента списка
                sb.append(currentSymbol.name());

                // от полученного на входе в метод числа отнимаем число-значение текущего элемента списка
                arabic -= currentSymbol.arabicValue;
            } else {

                // иначе переходим к следующему элементу списка всех римских чисел
                i++;
            }
        }

        return sb.toString()
                // Чтоб правильно выводить 4, 9, 40, 90
                .replace("LXXXX" , "XC")
                .replace("XXXX", "XL")
                .replace("VIIII" , "IX")
                .replace("IIII" , "IV" );
    }

}