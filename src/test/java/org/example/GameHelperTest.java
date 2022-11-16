package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;


import static org.junit.jupiter.api.Assertions.*;

class GameHelperTest {

    @Test
    @DisplayName("Проверка кейса, когда разместить НЕвозможно")
    void placeDotCom_Test_1() {
        ArrayList<String> testTakenCells = new ArrayList<>();
        testTakenCells.add("a0");
        testTakenCells.add("b1");
        testTakenCells.add("c2");
        testTakenCells.add("d3");
        testTakenCells.add("a3");
        testTakenCells.add("d0");

        int rows = 4;
        int columns = 4;

        assertEquals(new ArrayList<>(), GameHelper.placeDotCom(testTakenCells, rows, columns));
    }

    @Test
    @DisplayName("Проверка кейса, когда разместить возможно")
    void placeDotCom_Test_2() {

        ArrayList<String> testTakenCells = new ArrayList<>();
        testTakenCells.add("a0");
        testTakenCells.add("b1");
        testTakenCells.add("c2");
        testTakenCells.add("d3");

        int rows = 4;
        int columns = 4;

        assertNotEquals(new ArrayList<>(), GameHelper.placeDotCom(testTakenCells, rows, columns));
    }

    @ParameterizedTest
    @DisplayName("Проверка на отсутствие занятых ячеек")
    @ValueSource(strings = {"a0", "b1", "c2", "d3"})
    void placeDotCom_Test_3(String testCase) {

        ArrayList<String> testTakenCells = new ArrayList<>();
        testTakenCells.add("a0");
        testTakenCells.add("b1");
        testTakenCells.add("c2");
        testTakenCells.add("d3");

        int rows = 4;
        int columns = 4;

        assertFalse(GameHelper.placeDotCom(testTakenCells, rows, columns).contains(testCase));
    }

    @Test
    @DisplayName("Проверка, что результат не входит за рамки поля")
    void placeDotCom_Test_4() {

        ArrayList<String> testTakenCells = new ArrayList<>();
        testTakenCells.add("a0");
        testTakenCells.add("b1");
        testTakenCells.add("c2");
        testTakenCells.add("d3");

        int rows = 4;
        int columns = 4;

        ArrayList<Character> checkList = new ArrayList<>();
        checkList.add('a');
        checkList.add('b');
        checkList.add('c');
        checkList.add('d');
        ArrayList<Integer> checkListInt = new ArrayList<>();
        checkListInt.add(0);
        checkListInt.add(1);
        checkListInt.add(2);
        checkListInt.add(3);

        //Проверяем, что строка каждой полученной ячейки не выходит за пределы поля
        assertTrue(checkList.contains(GameHelper.placeDotCom(testTakenCells, rows, columns).get(0).charAt(0)));
        assertTrue(checkList.contains(GameHelper.placeDotCom(testTakenCells, rows, columns).get(1).charAt(0)));
        assertTrue(checkList.contains(GameHelper.placeDotCom(testTakenCells, rows, columns).get(2).charAt(0)));
        //Проверяем, что столбец каждой полученной ячейки не выходит за пределы поля
        assertTrue(checkListInt.contains(Integer.parseInt(GameHelper.placeDotCom(testTakenCells, rows, columns).get(0).substring(1))));
        assertTrue(checkListInt.contains(Integer.parseInt(GameHelper.placeDotCom(testTakenCells, rows, columns).get(1).substring(1))));
        assertTrue(checkListInt.contains(Integer.parseInt(GameHelper.placeDotCom(testTakenCells, rows, columns).get(2).substring(1))));
    }

    @ParameterizedTest(name = "{index} - {0} сайт{1}")
    @DisplayName("Проверка окончаний")
    @CsvSource({"1, ''",
            "2, а",
            "3, а",
            "5, ов",
            "8, ов",
            "10, ов",
            "11, ов",
            "15, ов",
            "20, ов",
            "21, ''",
            "25, ов",
            "61, ''",
            "111, ов",
            "121, ''",
            "1567, ов",
            "1511, ов",
            "1571, ''"})
    void endingCalc_Test_1(int testCase, String result) {
        assertEquals(result, GameHelper.endingCalc(testCase));
    }

    @ParameterizedTest(name = "{index} - {0} сайт{1}")
    @DisplayName("Проверка окончаний из файла")
    @CsvFileSource(resources = "TestCases.csv", delimiter = '|', numLinesToSkip = 1)
    void endingCalc_Test_2(String testCase, String result) {
        assertEquals(result, testCase);
    }
}