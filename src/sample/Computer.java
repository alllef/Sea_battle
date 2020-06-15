package sample;

import java.util.ArrayList;

public class Computer extends Player {
    ArrayList<Cell> madeGuesses = new ArrayList<Cell>();


    Computer() {
        super();
    }


    public void setShipsLocations() {
        for (Ship shipToSet : shipList) {//повторяем скаждым объектом в списке
            ArrayList<Cell> coords = new ArrayList<Cell>(shipToSet.getSize());//координаты текущего сайта
            int attempts = 0;//счетчик текущих попыток
            boolean success = false;//нашли подходящее местоположение


            int cardinalDirection = 1 + (int) Math.random() * 4;//устанавливаем горизонтальный инкремент

            while (!success & attempts++ < 200) {//главный поисковой цикл
                int row = (int) Math.random() * getGrid().length;
                int column = (int) Math.random() * getGrid().length;//получаем случайную стартовую точку
                int x = 0;//энная позиция в сайте

                success = true;//предполагаемый успех
                while (success && x < shipToSet.getSize()) { //ищем соседнюю неиспользованную ячейку
                    if (!getGrid()[row][column].usedForShip()) {//если еще не используется

                        if (cardinalDirection == 1) coords.add(x++, getGrid()[row--][column]);
                        if (cardinalDirection == 2) coords.add(x++, getGrid()[row++][column]);
                        if (cardinalDirection == 3) coords.add(x++,getGrid()[row][column++]);
                        if (cardinalDirection == 4) coords.add(x++, getGrid()[row][column--]);

                        if (row >= getGrid().length || column >= getGrid().length || row < 0 || column < 0)
                            success = false;//вышли за рамкм - низ
                        //неудача


                    } else {//нашли уже используемое местоположение
                        success = false;//неудача
                    }
                }
            }
            shipToSet.setLocationCells(coords);//Вызываем сеттер из объекта DotCom, чтобы передать ему местоположение которое только что получили от вспомогательного объекта
        }


    }


    @Override
    public Cell makeGuess() {

        int row = (int) Math.random() * 11;
        int collumn = (int) Math.random() * 11;

        Cell guess = new Cell(row,collumn);
        if (!madeGuesses.contains(guess)){
            madeGuesses.add(guess);
            return guess;
        }
        else makeGuess();
        return guess;
    }
}
