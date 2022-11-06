package org.example;

import java.util.ArrayList;

public class DotCom_Test {
    public static void main(String[] args) {

        DotCom dot = new DotCom();

        ArrayList<String> locations = new ArrayList<>();
        locations.add("A1");
        locations.add("A2");
        locations.add("A3");
        dot.setLocationCells(locations);

        String[] userGuesses = {"A7", "H0", "10", "B1", "A1", "A2", "A3"};
        String[] testResults = {"Мимо", "Мимо", "Мимо", "Мимо", "Попал", "Попал", "Потопил"};

        int testsPassed = 0;

        for (int i = 0; i < userGuesses.length; i++) {
            if (dot.checkYourself(userGuesses[i]).equals(testResults[i])) {
                testsPassed++;
                System.out.println("Тест " + (i+1) + " пройден!");
            } else {
                System.out.println("Тест " + (i+1) + " НЕ пройден!");
            }
        }
        System.out.println("Пройдено " + testsPassed + " из " + userGuesses.length);

    }
}

