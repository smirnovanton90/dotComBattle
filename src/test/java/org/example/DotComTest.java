package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.*;
import java.util.ArrayList;

class DotComTest {

    DotCom testDotCom;

    @BeforeEach
    void setUp() {
        testDotCom = new DotCom();
        testDotCom.setName("Игра");
    }

    @Test
    void setName_Test_1() {
        assertEquals(testDotCom.getName(), "Игра");
    }

    @Test
    void setName_Test_2() {
        testDotCom.setName("Игра2");
        assertEquals(testDotCom.getName(), "Игра2");
    }

    @Test
    void setLocationCells_checkYourself_Test_1() { //Проверка сразу двух методов - setLocationCells и checkYourself
        ArrayList<String> loc = new ArrayList<>();
        loc.add("A1");
        loc.add("A2");
        loc.add("A3");

        testDotCom.setLocationCells(loc);

        assertEquals("Мимо", testDotCom.checkYourself("A7"));
        assertEquals("Мимо", testDotCom.checkYourself("H0"));
        assertEquals("Мимо", testDotCom.checkYourself("10"));
        assertEquals("Мимо", testDotCom.checkYourself("B1"));
        assertEquals("Попал", testDotCom.checkYourself("A1"));
        assertEquals("Попал", testDotCom.checkYourself("A2"));
        assertEquals("Потопил", testDotCom.checkYourself("A3"));

    }

    @Test
    void getName_Test_1() {
        assertEquals("Игра", testDotCom.getName());
    }

}