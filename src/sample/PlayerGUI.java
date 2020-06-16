package sample;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

abstract public class PlayerGUI {
    Button[][] buttonGrid = new Button[Values.squareGridSize][Values.squareGridSize];


    PlayerGUI() {
        setupGrid();
    }

    void setupGrid() {
        for (int i = 0; i < buttonGrid.length; i++) {
            for (int j = 0; j < buttonGrid[i].length; j++) {
                buttonGrid[i][j] = setupButton(i, j);
            }
        }
    }

    Button setupButton(int i, int j) {
        Button button = new Button();

        button.setStyle("-fx-background-color: #ffffff; -fx-border-color:black");
        button.setMinHeight(Values.squareButtonSize);
        button.setMinWidth(Values.squareButtonSize);
        button.setMaxHeight(Values.squareButtonSize);
        button.setMaxWidth(Values.squareButtonSize);
        button.setPrefHeight(Values.squareButtonSize);
        button.setPrefWidth(Values.squareButtonSize);
        button.setOnAction(event -> setupButtonAction(i, j));
        return button;
    }

    ImageView shotImage() {
        Image playI = new Image("red-cross.png", Values.squareButtonSize, Values.squareButtonSize, false, false);

        return new ImageView(playI);
    }

    ImageView missImage() {
        Image playI = new Image("dot.png", Values.squareButtonSize/2, Values.squareButtonSize/2, false, false);
        return new ImageView(playI);
    }

    abstract void setupButtonAction(final int i, final int j);
}
