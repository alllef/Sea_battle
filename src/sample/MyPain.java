package sample;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;

public class MyPain {

    HumanGUI gui = new HumanGUI();
    ComputerGUI otherGUI = new ComputerGUI(gui);
    FlowPane gamePane = new FlowPane();
    Button button = new Button("To game");
    private String alphabet = "ABCDEFGHIJK";

    void makeHumanGrid() {
        GridPane tmpGrid = new GridPane();
        tmpGrid.setGridLinesVisible(true);
        for (int i = 0; i < Values.squareGridSize + 1; i++) {
            for (int j = 0; j < Values.squareGridSize + 1; j++) {
                if (i == 0 && j > 0) tmpGrid.add(new Label(String.valueOf(alphabet.charAt(j-1))), j, i);
                else if (j == 0 && i > 0) tmpGrid.add(new Label(String.valueOf(i)), j, i);
                else if(i == 0) tmpGrid.add(new Label(""),j,i);
                else {

                    Button button = gui.buttonGrid[i-1][j-1];
                    tmpGrid.add(button, j, i);
                }
            }
        }
        gamePane.getChildren().add(tmpGrid);
    }


    GridPane makeComputerGrid() {
        GridPane tmpGrid = new GridPane();
        tmpGrid.setGridLinesVisible(true);


        for (int i = 0; i < Values.squareGridSize + 1; i++) {
            for (int j = 0; j < Values.squareGridSize + 1; j++) {
                if (i == 0 && j > 0) tmpGrid.add(new Label(String.valueOf(alphabet.charAt(j-1))), j, i);
                else if (j == 0 && i > 0) tmpGrid.add(new Label(String.valueOf(i)), j, i);
                else if(i == 0) tmpGrid.add(new Label(""),j,i);
                else {

                    Button button = otherGUI.buttonGrid[i-1][j-1];
                    tmpGrid.add(button, j, i);
                }
            }
        }
        return tmpGrid;
    }


    MyPain() {
        makeHumanGrid();
        gamePane.getChildren().add(button);
        gamePane.getChildren();
        gamePane.setHgap(220);
    }


}
