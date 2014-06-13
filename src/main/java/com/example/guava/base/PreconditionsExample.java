package com.example.guava.base;

import static com.google.common.base.Preconditions.*;

public class PreconditionsExample {
    
    private final int[] values;

    public PreconditionsExample(final String numbersString) {
        checkNotNull(numbersString, "Input value can't be null");
        checkArgument(numbersString.matches("\\d+(,\\d+)*"), "Input must be numbers separated by comma");
        String[] stringNumbers = splitStringOfNumbers(numbersString);
        values = transformToNumbers(stringNumbers);
    }

    private String[] splitStringOfNumbers(String numbersString) {
        return numbersString.split(",");
    }

    private int[] transformToNumbers(String[] stringNumbers) {
        int amountOfNumbers = stringNumbers.length;
        int[] numbers = new int[amountOfNumbers];
        for (int i = 0; i < amountOfNumbers; i++)
            numbers[i] = Integer.valueOf(stringNumbers[i]);
        return numbers;
    }

    public void updateCurrentIndexValue(int index, int valueToSet) {
        int currentIndex = checkElementIndex(index, values.length, "Index out of bounds for values");
        checkArgument(valueToSet <= 100, "Value can't be bigger than 100");
        values[currentIndex] = valueToSet;
    }

    public int sum() {
        checkState(allNumbersArePositive(), "Can't perform operation, all parameters has to be positive");
        int sum = 0;
        for (int number : values)
            sum += number;
        return sum;
    }

    private boolean allNumbersArePositive() {
        for (int number : values)
            if (number > 0)
                return false;
        return true;
    }
}
