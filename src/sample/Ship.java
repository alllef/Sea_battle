package sample;

import java.util.ArrayList;

public class Ship {

    private int size;
    private ArrayList<Cell> locationCells;

    Ship() {
    }

    Ship(int size) {
        this.size = size;
    }


    public int getSize() {
        return size;
    }


    public void setLocationCells(ArrayList<Cell> locationCells) {
        for (int i = 0; i < locationCells.size(); i++) {
            locationCells.set(i, locationCells.get(i).useForShip());
        }
        this.locationCells = locationCells;
    }

    public ArrayList<Cell> getLocationCells() {
        return locationCells;
    }

    public String checkYourself(Cell userInput) {
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
        //fSystem.out.println(result);
        return result;
    }
}
