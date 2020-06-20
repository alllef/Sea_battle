package sample;

import java.util.ArrayList;

public class Computer extends Player { // Class for computer player
    private final ArrayList<Cell> madeGuesses = new ArrayList<>();
    private final ArrayList<Cell> guessesforResults = new ArrayList<>();
    private final ArrayList<Cell> affectedshipCells = new ArrayList<>();

    public ArrayList<Cell> getGuessesforResults() {
        return guessesforResults;
    }

    public ArrayList<Cell> getAffectedshipCells() {
        return affectedshipCells;
    }

    public Computer() {
        super();
        setShipsLocations();
    }


    public void setShipsLocations() { // setting locations for ships

        int attempts = 0;
        boolean notSetuped = true;

        while (notSetuped) {
            for (Ship shipToSet : shipList) {
                ArrayList<Cell> coords = new ArrayList<>();

                boolean success = false;

                int cardinalDirection = 1 + (int) (Math.random() * 4);

                while (!success) {
                    attempts++;
                    if (attempts > 9000) break;
                    int row = (int) (Math.random() * Values.squareGridSize);
                    int column = (int) (Math.random() * Values.squareGridSize);//получаем случайную стартовую точку


                    success = true;
                    while (success && coords.size() < shipToSet.getSize()) {

                        if (!areShipsNear(row, column)) {

                            switch (cardinalDirection) {
                                case 1 -> coords.add(getGrid()[row--][column]);
                                case 2 -> coords.add(getGrid()[row++][column]);
                                case 3 -> coords.add(getGrid()[row][column++]);
                                case 4 -> coords.add(getGrid()[row][column--]);
                            }

                            if (row >= getGrid().length || column >= getGrid().length || row < 0 || column < 0) {
                                coords.clear();
                                success = false;
                            }

                        } else {
                            coords.clear();
                            success = false;
                        }
                    }

                }

                shipToSet.setLocationCells(coords);

                for (Cell cell : shipToSet.getLocationCells()) {
                    getGrid()[cell.row()][cell.col()] = cell;
                }


            }

            if (attempts > 9000) {
                attempts = 0;

                for (Ship ship : shipList) {
                    ship.setLocationCells(new ArrayList<>());
                }

                for (int i = 0; i < getGrid().length; i++) {
                    for (int j = 0; j < getGrid()[i].length; j++) {
                        getGrid()[i][j] = new Cell(getGrid()[i][j].row(), getGrid()[i][j].col());
                    }
                }

            } else notSetuped = false;
        }
    }

    public Cell makeGuess() { // making guess about Cell where ship can be

        int row;
        int column;

        while (true) {
            if (!guessesforResults.isEmpty()) {
                Cell lastGuess = guessesforResults.get((int) (Math.random() * guessesforResults.size()));
                ArrayList<Cell> guesses = new ArrayList<>();
                guesses.add(new Cell(lastGuess.row() - 1, lastGuess.col()));
                guesses.add(new Cell(lastGuess.row() + 1, lastGuess.col()));
                guesses.add(new Cell(lastGuess.row(), lastGuess.col() + 1));
                guesses.add(new Cell(lastGuess.row(), lastGuess.col() - 1));
                while (true) {


                    Cell tmpCell = guesses.get((int) (Math.random() * guesses.size()));

                    if (tmpCell.row() < getGrid().length && tmpCell.col() < getGrid().length && tmpCell.row() >= 0 && tmpCell.col() >= 0) {
                        row = tmpCell.row();
                        column = tmpCell.col();
                        break;
                    }
                }
            } else {
                boolean areShipsNear;
                do {
                    areShipsNear = false;
                    row = (int) (Math.random() * Values.squareGridSize);
                    column = (int) (Math.random() * Values.squareGridSize);
                    ArrayList<Cell> shipCellsNear = new Cell(row, column).getNearestCellsList();
                    for (Cell cellNear : shipCellsNear) {
                        for (Cell affectedCell : affectedshipCells) {
                            if (affectedCell.row() == cellNear.row() && affectedCell.col() == cellNear.col()) {
                                areShipsNear = true;

                            }
                        }
                    }
                } while (areShipsNear);
            }

            Cell guess = new Cell(row, column);
            if (!madeGuesses.contains(guess)) {
                madeGuesses.add(guess);
                return guess;
            }
        }

    }

}
