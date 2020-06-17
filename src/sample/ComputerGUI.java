package sample;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class ComputerGUI extends PlayerGUI {
    Computer computer = new Computer();
    private ArrayList<Cell> shipCells = new ArrayList<>();
    private HumanGUI humanGUI;

    ComputerGUI(HumanGUI humanGUI) {
        super();
        this.humanGUI = humanGUI;
    }


    @Override
    void setupButtonAction(final int i, final int j) {
        String result = computer.checkGuess(new Cell(i, j, true));


        switch (result) {
            case "Попал" -> {
                buttonGrid[i][j].setGraphic(getShotImage());
                buttonGrid[i][j].setStyle("-fx-background-color: #0033cc");
                buttonGrid[i][j].setDisable(true);
                shipCells.add(new Cell(i, j));
            }
            case "Потопил" -> {
                shipCells.add(new Cell(i, j));
                buttonGrid[i][j].setGraphic(getShotImage());
                for (Cell cell : shipCells) {
                    buttonGrid[cell.row()][cell.col()].setStyle("-fx-background-color: #0033cc; -fx-border-color:red; -fx-border-width:3px 3px");
                }
                shipCells.clear();
                buttonGrid[i][j].setDisable(true);
            }
            case "Мимо" -> {
                buttonGrid[i][j].setGraphic(getMissImage());
                buttonGrid[i][j].setStyle("-fx-background-color: #ffff99; -fx-border-color:black");
                buttonGrid[i][j].setDisable(true);
                boolean isMiss = false;
                while (!isMiss) {
                    Cell guess = computer.makeGuess();
                    String humanResult = humanGUI.checkGUIGuess(new Cell(guess.row(), guess.col(), true));
                    System.out.println("Возвращающий результат:" + humanResult);
                    switch (humanResult) {
                        case "Попал" -> computer.guessesforResults.add(guess);
                        case "Потопил" -> computer.guessesforResults.clear();
                        case "Мимо" -> isMiss = true;
                    }

                }
            }
            default -> System.out.println("победил");
        }
    }

    ComputerGUI() {
        super();
    }
}
