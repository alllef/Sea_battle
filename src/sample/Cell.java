package sample;

import java.util.ArrayList;

public record Cell(int row, int col, boolean usedForShip) { // creating Cell record



    public Cell(int row, int col) {
        this(row, col, false);
    }


    public Cell useForShip() { // make Cell used for ship
        if (usedForShip) throw new IllegalStateException(String.format("%s is already used for Ship", this.toString()));
        return new Cell(row, col, true);
    }

    public ArrayList<Cell> getNearestCellsList(){ // get List of nearest Cells both on sidea and corners
        ArrayList<Cell> cellsList = new ArrayList<>();
        cellsList.add(new Cell(row,col));
        cellsList.add(new Cell(row + 1, col));
        cellsList.add(new Cell(row - 1, col));
        cellsList.add(new Cell(row, col + 1));
        cellsList.add(new Cell(row, col - 1));
        cellsList.add(new Cell(row + 1, col + 1));
        cellsList.add(new Cell(row + 1, col - 1));
        cellsList.add(new Cell(row - 1, col + 1));
        cellsList.add(new Cell(row - 1, col - 1));
        return cellsList;
    }

}
