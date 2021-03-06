package sample;

import java.util.ArrayList;

public class Ship {

    private final int size;
    private ArrayList<Cell> locationCells;

    Ship(int size) {
        this.size = size;
    }


    public int getSize() {
        return size;
    }


    public void setLocationCells(ArrayList<Cell> locationCells) { // seting locstionCells for ship
        for (int i = 0; i < locationCells.size(); i++) {
            locationCells.set(i, locationCells.get(i).useForShip());
        }
        this.locationCells = locationCells;
    }

    public ArrayList<Cell> getLocationCells() {
        return locationCells;
    }

    public String checkYourself(Cell userInput) { // checking whether ship has this Cell
        String result = "Мимо";
        int index = locationCells.indexOf(userInput);

        if (index >= 0) {
            locationCells.set(index, new Cell(userInput.row(), userInput.col()));

            for (Cell cell : locationCells) {
                if (cell.usedForShip()) return "Попал";
            }

            result = "Потопил";
        }

        return result;
    }

}
