package org.example;

import java.util.ArrayList;

public class DotComBust {

    //VARIABLES

    private ArrayList<Character> rows = new ArrayList<>();
    private ArrayList<Character> collumns = new ArrayList<>();
    private ArrayList<DotCom> dotComsList = new ArrayList<>();
    private GameHelper helper = new GameHelper();
    private int numOfGuesses = 0;
    private int numOfRows;
    private final int maxNumOfRows = 26; //Максимальное количество строк в игровом поле
    private int numOfColumns;
    private final int maxNumOfColumns = 26; //Максимальное количество столбцов в игровом поле
    private int numOfComs;
    private int maxNumOfComs;
    private int averageSize = 3; //Средний размер сайта

    //SETTERS
    public void setRows(ArrayList<Character> rows) {
        this.rows = rows;
    }
    public void setCollumns(ArrayList<Character> collumns) {
        this.collumns = collumns;
    }

    //METHODS
    private boolean setUpGame(DotComBust gameToSet) {

        //Формируем поле для игры
        //Запрашиваем у игрока количество строк поля и проверяем корректность ввода
        numOfRows = Integer.parseInt(GameHelper.getUserInput("Введите количество строк игрового поля: "));
        if (numOfRows == 0) {
            System.out.println("Количество строк игрового поля не может быть 0!");
            return false;
        }
        if (numOfComs > maxNumOfRows) {
            System.out.println("Количество строк игрового поля не может быть более " + maxNumOfRows);
            return false;
        }
        //Устанавливаем строки поля
        ArrayList<Character> rowsToSet = new ArrayList<>();
        for (int i = 0; i < (numOfRows - 1); i++) {
            rowsToSet.add((char) (i+65));
        }
        gameToSet.setRows(rowsToSet);

        //Запрашиваем у игрока количество столбцов поля и проверяем корректность ввода
        numOfColumns = Integer.parseInt(GameHelper.getUserInput("Введите количество колонок игрового поля: "));
        if (numOfColumns == 0) {
            System.out.println("Количество колонок игрового поля не может быть 0!");
            return false;
        }

        if (numOfColumns > maxNumOfColumns) {
            System.out.println("Количество колонок игрового поля не может быть более " + maxNumOfColumns);
            return false;
        }
        //Устанавливаем колонки поля
        ArrayList<Character> columnsToSet = new ArrayList<>();
        for (int i = 1; i < (numOfColumns); i++) {
            columnsToSet.add(Character.forDigit(i , 10));
        }
        gameToSet.setCollumns(columnsToSet);

        maxNumOfComs = ((numOfRows * numOfColumns) / averageSize); //TODO Уточнить расчет переменной maxNumOfComs

        //Запрашиваем у игрока количество сайтов и проверяем корректность ввода
        numOfComs = Integer.parseInt(GameHelper.getUserInput("Введите количество сайтов, которые хотите отправить ко дну: "));
        if (numOfComs == 0) {
            System.out.println("Количество сайтов не может быть 0!");
            return false;
        }
        if (numOfComs > maxNumOfComs) {
            System.out.println("Введено слишком большое количество сайтов! Введите значение не более " + maxNumOfComs);
            return false;
        }
        //Создаем сайты
        for (int i = 0; i < numOfComs - 1;i++) {
            DotCom newDotCom = new DotCom();
            newDotCom.setName("" + (i+1));
            dotComsList.add(newDotCom);
        }

        for (DotCom dotComToSet : dotComsList) {
            dotComToSet.setLocationCells(helper.placeDotCom(numOfComs));
        }


        //Информация об игре
        System.out.println("Ваша цель - потопить три сайта.");
        //TODO исправить вывод названий сайтов
//        System.out.println(one.getName() + ", " + two.getName() + ", " + three.getClass());
        System.out.println("Попытайтесь потопить их за минимальное количество ходов!");

        return true; //Игра сформирована, возвращаем значение true
    }
    private void startPlaying() {
        while (!dotComsList.isEmpty()) {
            checkUserGuess(helper.getUserInput("Сделайте ход"));
        }
        finishGame();
    }
    private void checkUserGuess (String userGuess) {
        //Валидация ввода
        boolean validation = false;
        if (userGuess.length() != 2 || !rows.contains(userGuess.charAt(0)) || !collumns.contains(userGuess.charAt(1))) {
            System.out.println("Некорректный ввод!");
        } else {
            validation = true;
        }
        //Если валидация пройдена, то проверяем введенный пользователем вариант
        if (validation) {
            numOfGuesses++;
            String result = "Мимо";
            for (DotCom dotComToTest : dotComsList) {
                result = dotComToTest.checkYourself(userGuess);
                if (result.equals("Попал")) { //TODO сделать не текст, а ссылки
                    break;
                }
                if (result.equals("Потопил")) {
                    dotComsList.remove(dotComToTest);
                }
            }
            System.out.println(result);
        }
    }
    private void finishGame () {
        System.out.println("Все сайты ушли ко дну! Ваши акции теперь ничего не стоят.");
        if (numOfGuesses <= 18) {
            System.out.println("Это заняло у вас всего " + numOfGuesses + " попыток.");
            System.out.println("Вы успели выбраться до того, как ваши вложения утонули.");
        } else {
            System.out.println("Это заняло у вас довольно много времени. " + numOfGuesses + " попыток.");
            System.out.println("Рыбы водят хороводы вокруг ваших вложений.");
        }
    }

    public static void main(String[] args) {
        //Создаем новую игру
        DotComBust game = new DotComBust();

        //TODO сделать цикл с повторением до тех пор пока игра не будет сформирована. Если больше 5-и попыток - завершаем программу
        game.setUpGame(game);
        game.startPlaying();
    }


}
