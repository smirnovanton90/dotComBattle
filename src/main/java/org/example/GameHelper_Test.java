package org.example;

import java.util.ArrayList;
import java.util.Arrays;

public class GameHelper_Test {

    public static void main(String[] args) {
        GameHelper_Test test = new GameHelper_Test();
//        test.endingCalcTest();
//        test.convertCellTest();
//        test.placeDotComTest();
    }
    public void endingCalcTest () {
        //Тестирование метода endingCalc
        int[] testCases = {1, 2, 3, 5, 8, 10, 11, 15, 20, 21, 25, 61, 111, 121, 1567, 1511, 1571};
        String[] correctAnswers = {"", "а", "а", "ов", "ов", "ов", "ов", "ов", "ов", "", "ов", "", "ов", "", "ов", "ов", ""};

        int passedTests = 0;
        final int totalTests = testCases.length;

        for (int i = 0; i < totalTests; i++) {
            if (GameHelper.endingCalc(testCases[i]).equals(correctAnswers[i])) {
                System.out.println("Test " + (i+1) + " passed");
                passedTests++;
            } else {
                System.out.println("Test " + (i+1) + " failed!");
            }
        }
        System.out.println("Total " + passedTests + " of " + totalTests + " is passed!");
    }

    public void convertCellTest () {
        //Тестирование метода cellConverter
        String[] testCases = {"A1", "AA1", "1A", "A"};
        int[][] correctAnswers = {{1,1}, {0,0}, {0,0}, {0,0}};

        int passedTests = 0;
        final int totalTests = testCases.length;

        for (int i = 0; i < totalTests; i++) {
            if (Arrays.equals(GameHelper.convertCell(testCases[i]), correctAnswers[i])) {
                System.out.println("Test " + (i+1) + " passed");
                passedTests++;
            } else {
                System.out.println("Test " + (i+1) + " failed!");
            }
        }
        System.out.println("Total " + passedTests + " of " + totalTests + " is passed!");
    }

    public void placeDotComTest () {
        //Тестирование метода placeDotCom

        ArrayList<String> testTakenCells = new ArrayList<>();
        testTakenCells.add("A0");
        testTakenCells.add("B1");
        testTakenCells.add("C2");
        testTakenCells.add("D3");
        testTakenCells.add("A3");
//        testTakenCells.add("D0");

        int rows = 4;
        int columns = 4;

        ArrayList<String> result = GameHelper.placeDotCom(testTakenCells, rows, columns);
        if (result.isEmpty()) {
            System.out.println("Разместить невозможно!");
        }else {
            for (String cells : result) {
                System.out.print(cells + " ");
            }
        }
    }
}


