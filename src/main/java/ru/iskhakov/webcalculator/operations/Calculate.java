package ru.iskhakov.webcalculator.operations;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Calculate {

    public double additionNumbers(double... numbers) {

        if (numbers.length < 2) {
            throw new IllegalArgumentException("Введите 2 или более чисел");
        }

        double result = 0;
        for (double i : numbers) {
            result += i;
        }
        return result;
    }

    public double multiplyNumbers(double... numbers) {

        if (numbers.length < 2) {
            throw new IllegalArgumentException("Введите 2 или более чисел");
        }

        double result = 1;
        for (double i : numbers) {
            result *= i;
        }
        return result;
    }

    public double multiplyAndAdditionNumbers(double... numbers) {

        if (numbers.length != 3) {
            throw new IllegalArgumentException("Введите 3 числа");
        }

        return numbers[0] * numbers[1] + numbers[2];
    }

    public double divideNumbers(double... numbers) {

        if (numbers.length < 2) {
            throw new IllegalArgumentException("Введите 2 или более чисел");
        }

        for (double j : numbers) {
            if (j == numbers[0]) {
                continue;
            } else if (j == 0) {
                throw new IllegalArgumentException("Деление на 0 недопустимо");
            }
        }
        double result = numbers[0];
        for (double i : numbers) {
            if (i == numbers[0]) {
                continue;
            } else {
                result /= i;
            }
        }
        return result;
    }

    public double[] StringToDoubleArrayConvert(String inputString) {
        String[] replacedArray = inputString.replaceAll(" ", "").replaceAll("[*/+:]", " ").split(" ");
        List<Double> doubleList = new ArrayList<>();
        for (String s : replacedArray) {
            double d = Double.parseDouble(s);
            doubleList.add(d);
        }
        return doubleList.stream().mapToDouble(Double::doubleValue).toArray();
    }

}
