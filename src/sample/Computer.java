package sample;

import java.util.ArrayList;

public class Computer extends Player {
    private final ArrayList<Cell> madeGuesses = new ArrayList<>();
    private  ArrayList<Cell> guessesforResults = new ArrayList<>();
    private ArrayList<Cell> affectedshipCells = new ArrayList<>();

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


    public void setShipsLocations() {

        int attempts = 0;
        boolean notSetuped = true;

        while (notSetuped) {
            for (Ship shipToSet : shipList) {//повторяем скаждым объектом в списке
                ArrayList<Cell> coords = new ArrayList<>();//координаты текущего сайта
                //счетчик текущих попыток
                boolean success = false;//нашли подходящее местоположение

                int cardinalDirection = 1 + (int) (Math.random() * 4);//устанавливаем горизонтальный инкремент

                while (!success && attempts++ < 10000) {//главный поисковой цикл

                    if (attempts > 9000) break;
                    int row = (int) (Math.random() * Values.squareGridSize);
                    int column = (int) (Math.random() * Values.squareGridSize);//получаем случайную стартовую точку


                    // System.out.println("row" + row + "column" + column);
                    success = true;//предполагаемый успех
                    while (success && coords.size() < shipToSet.getSize()) {
                        //ищем соседнюю неиспользованную ячейку
                        if (!areShipsNear(row, column)) {//если еще не используется
                            //System.out.println("работает");
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
                            //вышли за рамкм - низ
                            //неудача


                        } else {//нашли уже используемое местоположение
                            coords.clear();
                            success = false;//неудача
                        }
                    }

                }

                shipToSet.setLocationCells(coords);//Вызываем сеттер из объекта DotCom, чтобы передать ему местоположение которое только что получили от вспомогательного объекта

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

    public Cell makeGuess() {

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
                    System.out.println("something");
                    row = (int) (Math.random() * Values.squareGridSize);
                    column = (int) (Math.random() * Values.squareGridSize);
                    System.out.println("row " + row + "column " + column);
                    ArrayList<Cell> shipCellsNear = new Cell(row, column).getNearestCellsList();
                    for (Cell cellNear : shipCellsNear) {
                        for (Cell affectedCell : affectedshipCells) {
                            if (affectedCell.row() == cellNear.row() && affectedCell.col() == cellNear.col()) {
                                System.out.println("Нельзя пройти из-за " + row + " " + column);
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
