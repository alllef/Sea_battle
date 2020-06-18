package sample;

import java.util.ArrayList;
import java.util.List;

 public class Player {
    public List<Ship> shipList = new ArrayList<>();
    private final Cell[][] grid = new Cell[Values.squareGridSize][Values.squareGridSize];

    public void setupGrid() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = new Cell(i, j);
            }
        }
    }

    public Cell[][] getGrid() {
        return grid;
    }

   public Player() {
        setupShips();
        setupGrid();
    }

    public boolean areShipsNear(int row, int col) {
        ArrayList<Cell> nearestCells = new Cell(row,col).getNearestCellsList();


        for(Cell cell:nearestCells){
            if (cell.row() < getGrid().length && cell.col() < getGrid().length && cell.row() >= 0 && cell.col() >= 0)
                if(grid[cell.row()][cell.col()].usedForShip()) return true;
        }

        return false;
    }

    public void setupShips() {
        shipList.add(new Ship(1));
        shipList.add(new Ship(1));
        shipList.add(new Ship(1));
        shipList.add(new Ship(1));
        shipList.add(new Ship(1));
        shipList.add(new Ship(5));
        shipList.add(new Ship(4));
        shipList.add(new Ship(4));
        shipList.add(new Ship(3));
        shipList.add(new Ship(3));
        shipList.add(new Ship(3));
        shipList.add(new Ship(2));
        shipList.add(new Ship(2));
        shipList.add(new Ship(2));
        shipList.add(new Ship(2));
    }




    String checkGuess(Cell playerGuess) {

        String result = "Мимо";//Подразумевает промах, пока не выяснили обратного

        for (Ship shipToTest : shipList) {// повторяем это для всех объектов DotCom в списке
            result = shipToTest.checkYourself(playerGuess);//Просим DotCom проверить пользователя(ищет попадание или потопление)

            if (result.equals("Попал")) {
                break;//выбираем из цикла раньше времени нет смысла проверять другие сайты
            }

            if (result.equals("Потопил")) {//Ему пришел конец, так что удаляем его из списка сайтов
                break;
            }

        }
        return result;
    }


}
