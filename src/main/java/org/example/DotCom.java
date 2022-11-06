package org.example;

import java.util.ArrayList;

public class DotCom {
    private String name;
    private ArrayList<String> locationCells;

    public void setName(String name) {
        this.name = name;
    }
    public void setLocationCells(ArrayList<String> loc) {
        locationCells = loc;
    }

    public String checkYourself (String userInput) {
        String result = "Мимо";

        int index = locationCells.indexOf(userInput);

        if (index >= 0) {
            locationCells.remove(index);
            if (locationCells.isEmpty()) {
                result = "Потопил";
            } else {
                result = "Попал";
            }
        }
        return result;
    }


}
