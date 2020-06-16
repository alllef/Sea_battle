package sample;

import java.util.ArrayList;

public class Computer extends Player {
    ArrayList<Cell> madeGuesses = new ArrayList<Cell>();


    Computer() {
        super();
        setShipsLocations();
    }


    public void setShipsLocations() {
        int counter = 0;
        for (Ship shipToSet : shipList) {//повторяем скаждым объектом в списке
            ArrayList<Cell> coords = new ArrayList<>();//координаты текущего сайта
            int attempts = 0;//счетчик текущих попыток
            boolean success = false;//нашли подходящее местоположение


            int cardinalDirection = 1 + (int) (Math.random() * 4);//устанавливаем горизонтальный инкремент

            while (!success && attempts++ < 200) {//главный поисковой цикл
                int row = (int) (Math.random() * Values.squareGridSize);
                int column = (int) (Math.random() * Values.squareGridSize);//получаем случайную стартовую точку
                int x = 0;//энная позиция в сайте

                success = true;//предполагаемый успех
                while (success && coords.size() < shipToSet.getSize()) { //ищем соседнюю неиспользованную ячейку
                    if (!getGrid()[row][column].usedForShip()) {//если еще не используется
                        System.out.println("работает" + counter++);
                        if (cardinalDirection == 1) coords.add(getGrid()[row--][column]);
                        if (cardinalDirection == 2) coords.add(getGrid()[row++][column]);
                        if (cardinalDirection == 3) coords.add(getGrid()[row][column++]);
                        if (cardinalDirection == 4) coords.add(getGrid()[row][column--]);

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
                System.out.println("установлены координаты" + cell.row() + " " + cell.col());

            }

        }


    }


    @Override
    public Cell makeGuess() {

        int row = (int) (Math.random() * Values.squareGridSize);
        int collumn = (int) (Math.random() * Values.squareGridSize);

        Cell guess = new Cell(row, collumn);
        if (!madeGuesses.contains(guess)) {
            madeGuesses.add(guess);
            return guess;
        } else makeGuess();
        return guess;
    }
}
