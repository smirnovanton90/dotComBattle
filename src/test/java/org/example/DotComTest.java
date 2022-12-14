package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;

class DotComTest {

    DotCom testDotCom;

    @BeforeEach
    void setUp() {
        testDotCom = new DotCom();
        testDotCom.setName("Игра");
    }

    @Test
    @DisplayName("Тест метода setName при первой установке")
    void setName_Test_1() {
        assertEquals(testDotCom.getName(), "Игра");
    }

    @Test
    @DisplayName("Тест метода setName при последующих установках")
    void setName_Test_2() {
        testDotCom.setName("Игра2");
        assertEquals(testDotCom.getName(), "Игра2");
    }

    @ParameterizedTest
    @DisplayName("Проверка методов setLocationCells и checkYourself - проверка на мимо и попадание")
    @CsvSource({"'A7', 'Мимо'",
            "'H0', 'Мимо'",
            "'10', 'Мимо'",
            "'B1', 'Мимо'",
            "'A1', 'Попал'",
            "'A2', 'Попал'",
            "'A3', 'Попал'"})
    void setLocationCells_checkYourself_Test_1(String input, String result) {
        ArrayList<String> loc = new ArrayList<>();
        loc.add("A1");
        loc.add("A2");
        loc.add("A3");

        testDotCom.setLocationCells(loc);

        assertEquals(result, testDotCom.checkYourself(input));
    }

    @Test
    @DisplayName("Проверка методов setLocationCells и checkYourself - проверка на потопление")
    void setLocationCells_checkYourself_Test_2() {
        ArrayList<String> loc = new ArrayList<>();
        loc.add("A1");
        loc.add("A2");
        loc.add("A3");

        testDotCom.setLocationCells(loc);

        assertEquals("Попал", testDotCom.checkYourself("A1"));
        assertEquals("Попал", testDotCom.checkYourself("A2"));
        assertEquals("Потопил", testDotCom.checkYourself("A3"));

    }

    @Test
    @DisplayName("Тест метода getName")
    void getName_Test_1() {
        assertEquals("Игра", testDotCom.getName());
    }

}