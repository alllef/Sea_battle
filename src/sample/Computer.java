package sample;

import java.util.ArrayList;

public class Computer extends Player {
    ArrayList<Cell> madeGuesses = new ArrayList<Cell>();
    ArrayList<Cell> guessesforResults = new ArrayList<>();

    Computer() {
        super();
        setShipsLocations();
    }


    public void setShipsLocations() {
        int counter = 0;
        int attempts = 0;
        boolean notSetuped = true;
        while (notSetuped) {
            for (Ship shipToSet : shipList) {//повторяем скаждым объектом в списке
                ArrayList<Cell> coords = new ArrayList<>();//координаты текущего сайта
                //счетчик текущих попыток
                boolean success = false;//нашли подходящее местоположение

                int cardinalDirection = 1 + (int) (Math.random() * 4);//устанавливаем горизонтальный инкремент

                while (!success /*&& attempts++ < 10000*/) {//главный поисковой цикл
                    attempts++;
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
                    System.out.println("установлены координаты" + cell.row() + " " + cell.col() + " " + ++counter);

                }


            }

            if (attempts > 9000) {
                System.out.println("Повтор");
                counter = 0;
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


    @Override
    public Cell makeGuess() {
        int row;
        int collumn;
        int counter = 0;
        while (true) {
            System.out.println("Попытка компьютера " + counter);
            counter++;

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
                        collumn = tmpCell.col();
                        break;
                    }
                }
            } else {

                row = (int) (Math.random() * Values.squareGridSize);
                collumn = (int) (Math.random() * Values.squareGridSize);
            }

            Cell guess = new Cell(row, collumn);
            if (!madeGuesses.contains(guess)) {
                madeGuesses.add(guess);
                return guess;
            }
        }

    }

}
