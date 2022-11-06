package org.example;

public class SimpleDotCom {
    int[] locationCells;
    int numOfHits = 0;

    public void setLocationCells(int[] locs) {
        locationCells = locs;
    }

    public String checkYourself (String stringGuess) {

        String result = "Мимо";

        int guess;

        try {
            guess = Integer.parseInt(stringGuess);
        } catch (NumberFormatException e) {
            result = "Введено нечисловое значение!";
            return result;
        }


        if (guess < 0 || guess > 6) {
            result = "Введенное значение не должно быть числовым значением в диапазоне от 0 до 6!";
            return result;
        }

        for (int cell : locationCells) {
            if (guess == cell) {
                result = "Попал";
                numOfHits++;
                break;
            }
        }

        if (numOfHits == locationCells.length) {
            result = "Потопил";
        }

        return result;
    }
}
