package org.example;

import java.util.ArrayList;

public class DotComBust {

    //VARIABLES
    private ArrayList<Character> rows = new ArrayList<>();
    private ArrayList<Integer> collumns = new ArrayList<>();
    private ArrayList<DotCom> dotComsList = new ArrayList<>();
    private GameHelper helper = new GameHelper();

    //SETTERS
    public void setRows(ArrayList<Character> rows) {
        this.rows = rows;
    }
    public void setCollumns(ArrayList<Integer> collumns) {
        this.collumns = collumns;
    }

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

    }
    private void finishGame () {

    }

    public static void main(String[] args) {

    }


}
