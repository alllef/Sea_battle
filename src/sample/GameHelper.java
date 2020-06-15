package sample;

import java.io.*;
import java.util.*;

public class GameHelper {
    public static final String alphabet = "abcdefghijk";
    private  Cell[][] grid = new Cell[11][11];

    GameHelper() {
        setupGrid(grid);
    }

    public String getAlphabet() {
        return alphabet;
    }


    public void setupGrid(Cell[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = new Cell(i, j);
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
}
