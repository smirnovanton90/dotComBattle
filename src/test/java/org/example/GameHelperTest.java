package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;


import static org.junit.jupiter.api.Assertions.*;

class GameHelperTest {

    @Test
    void placeDotCom_Test_1() { //Проверяем кейс, когда разместить невозможно
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
    void placeDotCom_Test_2() { //Проверяем кейс, когда разместить возможно

        ArrayList<String> testTakenCells = new ArrayList<>();
        testTakenCells.add("a0");
        testTakenCells.add("b1");
        testTakenCells.add("c2");
        testTakenCells.add("d3");

        int rows = 4;
        int columns = 4;

        assertNotEquals(new ArrayList<>(), GameHelper.placeDotCom(testTakenCells, rows, columns));
    }

    @Test
    void placeDotCom_Test_3() { //Проверка на отсутствие занятых ячеек

        ArrayList<String> testTakenCells = new ArrayList<>();
        testTakenCells.add("a0");
        testTakenCells.add("b1");
        testTakenCells.add("c2");
        testTakenCells.add("d3");

        int rows = 4;
        int columns = 4;

        assertFalse(GameHelper.placeDotCom(testTakenCells, rows, columns).contains("a0"));
        assertFalse(GameHelper.placeDotCom(testTakenCells, rows, columns).contains("b1"));
        assertFalse(GameHelper.placeDotCom(testTakenCells, rows, columns).contains("c2"));
        assertFalse(GameHelper.placeDotCom(testTakenCells, rows, columns).contains("d3"));
    }

    @Test
    void placeDotCom_Test_4() { //Проверка, что результат не входит за рамки поля

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

    @Test
    void endingCalc_Test_1() {

        assertEquals("", GameHelper.endingCalc(1));
        assertEquals("а", GameHelper.endingCalc(2));
        assertEquals("а", GameHelper.endingCalc(3));
        assertEquals("ов", GameHelper.endingCalc(5));
        assertEquals("ов", GameHelper.endingCalc(8));
        assertEquals("ов", GameHelper.endingCalc(10));
        assertEquals("ов", GameHelper.endingCalc(11));
        assertEquals("ов", GameHelper.endingCalc(15));
        assertEquals("ов", GameHelper.endingCalc(20));
        assertEquals("", GameHelper.endingCalc(21));
        assertEquals("ов", GameHelper.endingCalc(25));
        assertEquals("", GameHelper.endingCalc(61));
        assertEquals("ов", GameHelper.endingCalc(111));
        assertEquals("", GameHelper.endingCalc(121));
        assertEquals("ов", GameHelper.endingCalc(1567));
        assertEquals("ов", GameHelper.endingCalc(1511));
        assertEquals("", GameHelper.endingCalc(1571));


    }
}