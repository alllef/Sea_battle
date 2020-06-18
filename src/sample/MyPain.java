package sample;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class MyPain {
    Pane gamePane = new Pane();
    HumanGUI humanGUI = new HumanGUI();
    ComputerGUI computerGUI = new ComputerGUI(humanGUI);

    Button button = setupButton();
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

                    Button button = humanGUI.buttonGrid[i-1][j-1];
                    tmpGrid.add(button, j, i);
                }
            }
        }
        tmpGrid.setLayoutX(20);
        tmpGrid.setLayoutY(20);
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

                    Button button = computerGUI.buttonGrid[i-1][j-1];
                    tmpGrid.add(button, j, i);
                }
            }
        }
        tmpGrid.setLayoutX(720);
        tmpGrid.setLayoutY(20);
        return tmpGrid;
    }

    private Button setupButton(){
        Button button = new Button();
        button.setText("To game");
        button.setLayoutX(1350);
        button.setLayoutY(750);
        button.setMinHeight(Values.squareButtonSize*2);
        button.setMinWidth(Values.squareButtonSize*3);
        button.setMaxHeight(Values.squareButtonSize*2);
        button.setMaxWidth(Values.squareButtonSize*3);
        button.setPrefHeight(Values.squareButtonSize*2);
        button.setPrefWidth(Values.squareButtonSize*3);
        button.setStyle("-fx-background-color: #0033cc; -fx-border-color:black");

        button.setOnAction(event -> {
            if(humanGUI.getHuman().getSetupCounter()==humanGUI.getHuman().shipList.size())  {
                gamePane.getChildren().add(makeComputerGrid());
                gamePane.getChildren().remove(1);
            }
        });
        if(humanGUI.getHuman().getSetupCounter()<humanGUI.getHuman().shipList.size()) button.setDisable(true);
        return button;
    }

    MyPain() {
        makeHumanGrid();
        gamePane.getChildren().add(button);


    }


}
