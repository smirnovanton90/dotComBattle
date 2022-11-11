package org.example;

import java.util.ArrayList;

public class DotComBust {


    //VARIABLES

    private ArrayList<Character> rows = new ArrayList<>();
    private ArrayList<Character> columns = new ArrayList<>();
    private ArrayList<DotCom> dotComsList = new ArrayList<>();
    private ArrayList<String> cellsTaken = new ArrayList<>(); // Занятые сайтами ячейки на поле
    private GameHelper helper = new GameHelper();
    private int numOfGuesses = 0;
    private int numOfRows;
    private final int maxNumOfRows = 26; //Максимальное количество строк в игровом поле
    private int numOfColumns;
    private final int maxNumOfColumns = 26; //Максимальное количество столбцов в игровом поле
    private int numOfComs;
    private int maxNumOfComs;
    private int averageSize = 3; //Средний размер сайта
    private boolean gameIsSet = false;

    //SETTERS
    public void setRows(ArrayList<Character> rows) {
        this.rows = rows;
    }
    public void setColumns(ArrayList<Character> columns) {
        this.columns = columns;
    }

    //METHODS
    private void setUpGame(DotComBust gameToSet) {

        //Формируем поле для игры
        //Запрашиваем у игрока количество строк поля и проверяем корректность ввода
        try {
            numOfRows = Integer.parseInt(GameHelper.getUserInput("Введите количество строк игрового поля: "));
        } catch (NumberFormatException e) {
            System.out.println("Требуется ввести цифру отличную от 0! Попробуйте еще раз!");
            gameToSet.gameIsSet = false;
            return;
        }
        if (numOfRows == 0) {
            System.out.println("Количество строк игрового поля не может быть 0!");
            gameToSet.gameIsSet = false;
            return;
        }
        if (numOfComs > maxNumOfRows) {
            System.out.println("Количество строк игрового поля не может быть более " + maxNumOfRows);
            gameToSet.gameIsSet = false;
            return;
        }
        //Устанавливаем строки поля
        ArrayList<Character> rowsToSet = new ArrayList<>();
        for (int i = 0; i < numOfRows; i++) {
            rowsToSet.add((char) (i+97));
        }
        gameToSet.setRows(rowsToSet);

        //Запрашиваем у игрока количество столбцов поля и проверяем корректность ввода
        try {
            numOfColumns = Integer.parseInt(GameHelper.getUserInput("Введите количество колонок игрового поля: "));
        } catch (NumberFormatException e) {
            System.out.println("Требуется ввести цифру отличную от 0! Попробуйте еще раз!");
            gameToSet.gameIsSet = false;
            return;
        }
        if (numOfColumns == 0) {
            System.out.println("Количество колонок игрового поля не может быть 0!");
            gameToSet.gameIsSet = false;
            return;
        }

        if (numOfColumns > maxNumOfColumns) {
            System.out.println("Количество колонок игрового поля не может быть более " + maxNumOfColumns);
            gameToSet.gameIsSet = false;
            return;
        }
        //Устанавливаем колонки поля
        ArrayList<Character> columnsToSet = new ArrayList<>();
        for (int i = 0; i < numOfColumns; i++) {
            columnsToSet.add(Character.forDigit(i , 10));
        }
        gameToSet.setColumns(columnsToSet);

        maxNumOfComs = ((numOfRows * numOfColumns) / averageSize); //TODO Уточнить расчет переменной maxNumOfComs

        //Запрашиваем у игрока количество сайтов и проверяем корректность ввода
        try {
            numOfComs = Integer.parseInt(GameHelper.getUserInput("Введите количество сайтов, которые хотите отправить ко дну: "));
        } catch (NumberFormatException e) {
            System.out.println("Требуется ввести цифру отличную от 0! Попробуйте еще раз!");
            gameToSet.gameIsSet = false;
            return;
        }
        if (numOfComs == 0) {
            System.out.println("Количество сайтов не может быть 0!");
            gameToSet.gameIsSet = false;
            return;
        }
        if (numOfComs > maxNumOfComs) {
            System.out.println("Введено слишком большое количество сайтов! Введите значение не более " + maxNumOfComs);
            gameToSet.gameIsSet = false;
            return;
        }
        //Создаем сайты и размещаем их на доске
        for (int i = 0; i < numOfComs;i++) {
            DotCom newDotCom = new DotCom();
            newDotCom.setName("" + (i+1));
            dotComsList.add(newDotCom);
            //Получаем свободные ячейки для размещения
            ArrayList<String> newDotComLocation = GameHelper.placeDotCom(cellsTaken, numOfRows, numOfColumns);
            if (newDotComLocation.isEmpty()) {
                finishGame(2);
            }
            //Размещаем сайт в указанные ячейки
            newDotCom.setLocationCells(newDotComLocation);
            //Добавляем в занятые ячейки на поле все ячейки нового сайта
            for (String location : newDotComLocation) {
                cellsTaken.add(location);
            }
        }

        //Информация об игре
        System.out.println("Ваша цель - потопить " + numOfComs + " сайт" + GameHelper.endingCalc(numOfComs) + ".");
        for (int i = 0; i < numOfComs-1; i ++) {
            System.out.print(dotComsList.get(i).getName() + ", ");
        }
        System.out.println(dotComsList.get(numOfComs-1).getName() + ".");
        System.out.println("Попытайтесь потопить их за минимальное количество ходов!");

        gameToSet.gameIsSet = true; //Игра сформирована, возвращаем значение true
    }
    private void startPlaying() {
        while (!dotComsList.isEmpty()) {
            checkUserGuess(GameHelper.getUserInput("Сделайте ход"));
        }
        finishGame(0);
    }
    private void checkUserGuess (String userGuess) {
        //Валидация ввода
        boolean validation = false;
        if (userGuess.length() != 2 || !rows.contains(userGuess.charAt(0)) || !columns.contains(userGuess.charAt(1))) {
            System.out.println("Некорректный ввод!");
        } else {
            validation = true;
        }
        //Если валидация пройдена, то проверяем введенный пользователем вариант
        if (validation) {
            numOfGuesses++;
            String result = DotCom.RESULTMISS;
            for (DotCom dotComToTest : dotComsList) {
                result = dotComToTest.checkYourself(userGuess);
                if (result.equals(DotCom.RESULTHIT)) {
                    break;
                }
                if (result.equals(DotCom.RESULTDEAD)) {
                    dotComsList.remove(dotComToTest);
                }
            }
            System.out.println(result);
        }
    }

    //Окончание игры
    private void finishGame (int finishCode) {
        switch (finishCode) {
            case 0:
                System.out.println("Все сайты ушли ко дну! Ваши акции теперь ничего не стоят.");
                if (numOfGuesses <= 18) {
                    System.out.println("Это заняло у вас всего " + numOfGuesses + " попыток.");
                    System.out.println("Вы успели выбраться до того, как ваши вложения утонули.");
                } else {
                    System.out.println("Это заняло у вас довольно много времени. " + numOfGuesses + " попыток.");
                    System.out.println("Рыбы водят хороводы вокруг ваших вложений.");
                }
                System.exit(1);
            case 1:
                System.out.println("Так и не получилось завестись... В следующий раз получится! :)");
                System.out.println();
                System.exit(1);
            case 2:
                System.out.println("Мы долго пытались разместить сайты, но у нас не получилось... Попробуйте поменять параметры игры и запустить ее снова!");
                System.out.println();
                System.exit(1);
        }
    }

    private static boolean checkCellInField (int rowNumber, int columnNumber, String cell) {
        if (rowNumber != 0 && columnNumber != 0) {
            int[] adress = GameHelper.convertCell(cell);
            return rowNumber <= adress[0] && columnNumber <= adress[1];
        } else {
            return false;
        }
    }
    public static void main(String[] args) {
        //Создаем новую игру
        DotComBust game = new DotComBust();

        //Пытаемся создать игру. Если по истечении 5 попыток игра так и не создана, то завершаем исполнение программы
        for (int i = 0; i < 5; i++) {
            if (game.gameIsSet) {
                game.startPlaying();
                break;
            } else {
                game.setUpGame(game);
                if (!game.gameIsSet) {
                    System.out.println("Осталось " + (5-(i+1)) + " попытки запустить игру!");
                    System.out.println();
                }
            }
        }
        if (!game.gameIsSet) {
            game.finishGame(1);
        }
    }
}
