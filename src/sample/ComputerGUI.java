package sample;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class ComputerGUI extends PlayerGUI {
    Computer computer = new Computer();

    private HumanGUI humanGUI;

    ComputerGUI(HumanGUI humanGUI) {
        super();
        this.humanGUI = humanGUI;
    }

    ArrayList<Cell> shipCells = new ArrayList<>();

    @Override
    void setupButtonAction(final int i, final int j) {
        String result = computer.checkGuess(new Cell(i, j, true));


        if (result.equals("Попал")) {
            buttonGrid[i][j].setGraphic(shotImage());
            buttonGrid[i][j].setStyle("-fx-background-color: #0033cc");
            buttonGrid[i][j].setDisable(true);
            shipCells.add(new Cell(i, j));

        }

        if (result.equals("Потопил")) {
            shipCells.add(new Cell(i, j));
            buttonGrid[i][j].setGraphic(shotImage());

            for (Cell cell : shipCells) {
                buttonGrid[cell.row()][cell.col()].setStyle("-fx-background-color: #0033cc; -fx-border-color:red");
            }

            shipCells.clear();
            buttonGrid[i][j].setDisable(true);
        }
        if (result.equals("Мимо")) {
            buttonGrid[i][j].setGraphic(missImage());
            buttonGrid[i][j].setStyle("-fx-background-color: #ffff99; -fx-border-color:black");
            buttonGrid[i][j].setDisable(true);

            while(true) {
                String humanResult = humanGUI.checkGUIGuess(computer.makeGuess().useForShip());
                if(humanResult.equals("Мимо")) break;
            }
        }
        else System.out.println("победил");
    }

    ComputerGUI() {
        super();
    }
}
