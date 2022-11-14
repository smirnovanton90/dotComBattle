package org.example;

import java.io.*;
import java.util.*;

public class GameHelper {
    //VARIABLES

    //METHODS
    //Метод, который принимает пользовательский ввод
    public static String getUserInput(String prompt) {
        String inputLine = null;
        System.out.print(prompt + "  ");

        try {
            BufferedReader is = new BufferedReader(new InputStreamReader(System.in));
            inputLine = is.readLine();
            if (inputLine.length() == 0) {
                return "";
            }
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }
        if (inputLine != null) {
            return inputLine.toLowerCase();
        } else {
            return "";
        }
    }

    //Метод, который размещает сайт
    //TODO Сделать логику, чтобы сайты не размещались рядом друг с другом??
    //TODO Сделать возможность размещения сайтов вертикально (сейчас только горизонтально помещаются)
    public static ArrayList<String> placeDotCom (ArrayList<String> takenCells, int rows, int columns) {

        ArrayList<String> result = new ArrayList<>();

        String checkCell1;
        String checkCell2;
        String checkCell3;

        int checkRow;
        int checkColumn;

        for (int i = 0; i <200; i++) {
            checkRow = (int) (Math.random() * rows);
            checkColumn = (int) (Math.random() * columns);

            // проверка горизонтальных
            for (int j = 0; j < 3; j++) {
                if (checkColumn-2+j >=0) {
                    checkCell1 = Character.toString(checkRow + 97) + Character.toString(checkColumn - 2 + j + 48);
                    checkCell2 = Character.toString(checkRow + 97) + Character.toString(checkColumn - 1 + j + 48);
                    checkCell3 = Character.toString(checkRow + 97) + Character.toString(checkColumn + j + 48);

                    if (!takenCells.contains(checkCell1) && !takenCells.contains(checkCell2) && !takenCells.contains(checkCell3) &&
                            checkRow < rows && checkColumn + j < columns) {
                        result.add(checkCell1);
                        result.add(checkCell2);
                        result.add(checkCell3);
                        return result;
                    }
                }
            }
            // проверка вертикальных
            for (int j = 0; j < 3; j++) {
                if (checkRow-2+j >= 0) {
                    checkCell1 = Character.toString(checkRow - 2 + j + 97) + Character.toString(checkColumn + 48);
                    checkCell2 = Character.toString(checkRow - 1 + j + 97) + Character.toString(checkColumn + 48);
                    checkCell3 = Character.toString(checkRow + j + 97) + Character.toString(checkColumn + 48);

                    if (!takenCells.contains(checkCell1) && !takenCells.contains(checkCell2) && !takenCells.contains(checkCell3) &&
                            checkRow + j < rows && checkColumn < columns) {
                        result.add(checkCell1);
                        result.add(checkCell2);
                        result.add(checkCell3);
                        return result;
                    }
                }
            }

        }
        return result;
    }

    //Метод, расчитывающий окончания для слова "сайт" в зависимости от их количества
    public static String endingCalc (int number) {
        if (number > 20 && number % 10 == 1 && number % 100 != 11) {
            return "";
        }
        return switch (number) {
            case 1 -> "";
            case 2, 3, 4 -> "а";
            default -> "ов";
        };
    }
}