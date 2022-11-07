package org.example;

import java.util.ArrayList;

public class DotComBust {

    //VARIABLES
    //TODO сделать проверку на попадание в обозначенный периметр
//    private ArrayList<Character> rows = new ArrayList<>();
//    private ArrayList<Integer> collumns = new ArrayList<>();
    private ArrayList<DotCom> dotComsList = new ArrayList<>();
    private GameHelper helper = new GameHelper();
    private int numOfGuesses = 0;

    //SETTERS
//    public void setRows(ArrayList<Character> rows) {
//        this.rows = rows;
//    }
//    public void setCollumns(ArrayList<Integer> collumns) {
//        this.collumns = collumns;
//    }

    //METHODS
    private void setUpGame() {
        //TODO Сделать запрос количества сайтов и их имен (валидация?!)
        DotCom one = new DotCom();
        one.setName("Pets.com");
        DotCom two = new DotCom();
        two.setName("eToys.com");
        DotCom three = new DotCom();
        three.setName("Go2.com");
        dotComsList.add(one);
        dotComsList.add(two);
        dotComsList.add(three);

        for (DotCom dotComToSet : dotComsList) {
            dotComToSet.setLocationCells(helper.placeDotCom(3));
        }

        System.out.println("Ваша цель - потопить три сайта.");
        System.out.println(one.getName() + ", " + two.getName() + ", " + three.getClass());
        System.out.println("Попытайтесь потопить их за минимальное количество ходов!");
    }
    private void startPlaying() {
        while (!dotComsList.isEmpty()) {
            checkUserGuess(helper.getUserInput("Сделайте ход"));
        }
        finishGame();
    }
    private void checkUserGuess (String userGuess) {
        numOfGuesses++;
        String result = "Мимо";
        for (DotCom dotComToTest : dotComsList) {
            result = dotComToTest.checkYourself(userGuess);
            if (result.equals("Попал")) { //TODO сделать не текс, а ссылки
                break;
            }
            if (result.equals("Потопил")) {
                dotComsList.remove(dotComToTest);
            }
        }
        System.out.println(result);
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
        DotComBust game = new DotComBust();
        game.setUpGame();
        game.startPlaying();
    }


}
