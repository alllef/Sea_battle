package sample;

import java.io.*;
import java.util.*;

public class GameHelper {
    private static final String alphabet = "abcdefghijk";
    private final Cell[][] grid = new Cell[11][11];

    GameHelper() {
        setupGrid(grid);
    }

    public String getAlphabet() {
        return alphabet;
    }


    public void setupGrid(Cell[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = new Cell(alphabet.charAt(j), j);
            }
        }
    }

    public Cell[][]getGrid(){
        return grid;
    }

    public Cell getCellInput(String prompt) {
        String inputLine = null;
        System.out.print(prompt + " ");
        try {
            BufferedReader is = new BufferedReader(new InputStreamReader(System.in));
            inputLine = is.readLine();
            if (inputLine.length() == 0) return null;
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }
        inputLine.toLowerCase();
        return new Cell(inputLine.charAt(0),Integer.parseInt(String.valueOf(inputLine.charAt(1))) );
    }

    public String getUserInput(String prompt){
        System.out.print(prompt + " ");
        String inputLine = null;
        try {
            BufferedReader is = new BufferedReader(new InputStreamReader(System.in));
            inputLine = is.readLine();
        } catch (IOException e){
            System.out.println("IOException: " + e);
        }
        return inputLine.toLowerCase();
    }

    public ArrayList<Cell> placeShip(int shipSize) {

        ArrayList<Cell> coords = new ArrayList<Cell>(shipSize);//координаты текущего сайта
        int attempts = 0;//счетчик текущих попыток
        boolean success = false;//нашли подходящее местоположение


        int cardinalDirection = 1 + (int) Math.random() * 4;//устанавливаем горизонтальный инкремент

        while (!success & attempts++ < 200) {//главный поисковой цикл
            int row = (int) Math.random() * grid.length;
            int column = (int) Math.random() * grid.length;//получаем случайную стартовую точку
            int x = 0;//энная позиция в сайте

            success = true;//предполагаемый успех
            while (success && x < shipSize) { //ищем соседнюю неиспользованную ячейку
                if (!grid[row][column].usedForShip()) {//если еще не исспользуется

                    if (cardinalDirection == 1) coords.add(x++, grid[row++][column]);
                    if (cardinalDirection == 2) coords.add(x++, grid[row--][column]);
                    if (cardinalDirection == 3) coords.add(x++, grid[row][column++]);
                    if (cardinalDirection == 4) coords.add(x++, grid[row][column--]);

                    if (row >= grid.length || column >= grid.length || row < 0 || column < 0)
                        success = false;//вышли за рамкм - низ
                    //неудача


                } else {//нашли уже используемое местоположение
                    success = false;//неудача
                }
            }
        }

        return coords;
    }
}
