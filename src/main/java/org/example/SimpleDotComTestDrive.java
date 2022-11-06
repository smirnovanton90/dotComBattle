package org.example;

public class SimpleDotComTestDrive {
    public static void main(String[] args) {

        SimpleDotCom dot = new SimpleDotCom();

        int[] locations = {2, 3, 4};

        dot.setLocationCells(locations);

        String[] userGuesses = {"2", "0", "10", "-2", "a2"};
        String[] testResults = {"Неудача", "Неудача", "Неудача", "Неудача", "Неудача"};

        for (int i = 0; i < userGuesses.length; i++) {
            String userGuess = userGuesses[i];
            String result = dot.checkYourself(userGuess);
            switch (i) {
                case 0:
                    if (result.equals("Попал")) {
                        testResults[0] = "Пройден";
                    }
                case 1:
                    if (result.equals("Мимо")) {
                        testResults[1] = "Пройден";
                    }
                case 2:
                    if (result.equals("Введенное значение не должно быть числовым значением в диапазоне от 0 до 6!")) {
                        testResults[2] = "Пройден";
                    }
                case 3:
                    if (result.equals("Введенное значение не должно быть числовым значением в диапазоне от 0 до 6!")) {
                        testResults[3] = "Пройден";
                    }
                case 4:
                    if (result.equals("Введено нечисловое значение!")) {
                        testResults[4] = "Пройден";
                    }
            }
        }

        for (String testResult : testResults) System.out.println(testResult);

    }


}

