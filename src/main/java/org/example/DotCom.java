package org.example;

import java.util.ArrayList;

public class DotCom {

    //VARIABLES
    private String name;
    private ArrayList<String> locationCells;
    public static final String RESULTMISS = "Мимо";
    public static final String RESULTHIT = "Попал";
    public static final String RESULTDEAD = "Потопил";

    //SETTERS
    public void setName(String name) {
        this.name = name;
    }
    public void setLocationCells(ArrayList<String> loc) {
        locationCells = loc;
    }

    //GETTERS
    public String getName() {
        return name;
    }

    //METHODS
    public String checkYourself (String userInput) {
        String result = RESULTMISS;
        int index = locationCells.indexOf(userInput);
        if (index >= 0) {
            locationCells.remove(index);
            if (locationCells.isEmpty()) {
                result = RESULTDEAD;
            } else {
                result = RESULTHIT;
            }
        }
        return result;
    }
}
