package org.example;

import java.util.ArrayList;

public class SimpleDotComGame {

    public static void main(String[] args) {
        int numOfGuesses = 0;
        GameHelper helper = new GameHelper();
        SimpleDotCom theDotCom = new SimpleDotCom();
        int randomNum = (int) (Math.random()*5);
        int[] locations = {randomNum, randomNum+1, randomNum+2};
        theDotCom.setLocationCells(locations);
        boolean isAlive = true;
        ArrayList<String> guesses = new ArrayList<>();
        while (isAlive) {
            String guess = helper.getUserInput("Введите число:");
            boolean repeatCheck = false;
            if (guesses.size() ==0) {
                guesses.add(guess);
            } else {
                for (String a : guesses) {
                    if (a.equals(guess)) {
                        repeatCheck = true;
                        System.out.println("Вы уже вводили данное число!");
                        numOfGuesses++;
                    } else {
                        guesses.add(guess);
                    }
                    break;
                }
            }
            if (!repeatCheck) {
                String result = theDotCom.checkYourself(guess);
                System.out.println(result);
                numOfGuesses++;
                if (result.equals("Потопил")) {
                    isAlive = false;
                    System.out.println("Вам потребовалось " + numOfGuesses + " попыток(и)");
                }
            }
        }
    }
}
