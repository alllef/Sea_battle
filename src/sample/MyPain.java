package sample;

import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;

public class MyPain {

    HumanGUI gui = new HumanGUI();
    ComputerGUI otherGUI = new ComputerGUI(gui);
    FlowPane gamePane = new FlowPane();
    Button button = new Button("To game");

    void makeHumanGrid() {
        GridPane tmpGrid = new GridPane();
        tmpGrid.setGridLinesVisible(true);
        for (int i = 0; i < Values.squareGridSize; i++) {
            for (int j = 0; j < Values.squareGridSize; j++) {
                Button button = gui.buttonGrid[i][j];
                tmpGrid.add(button, j, i);
            }
        }
        gamePane.getChildren().add(tmpGrid);
    }


     GridPane makeComputerGrid() {
        GridPane computerGrid = new GridPane();
        computerGrid.setGridLinesVisible(true);


        for (int i = 0; i < Values.squareGridSize; i++) {
            for (int j = 0; j < Values.squareGridSize; j++) {
                Button button = otherGUI.buttonGrid[i][j];
                computerGrid.add(button, j, i);
            }
        }
        return computerGrid;
    }


    MyPain() {
        makeHumanGrid();
        gamePane.getChildren().add(button);
        gamePane.getChildren();
        gamePane.setHgap(220);
    }



}
