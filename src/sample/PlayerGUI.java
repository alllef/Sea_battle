package sample;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

abstract public class PlayerGUI {

    Button[][] buttonGrid = new Button[Values.squareGridSize][Values.squareGridSize];
    private final Image playI = new Image("red-cross.png", Values.squareButtonSize, Values.squareButtonSize, false, false);
    private final Image play2 = new Image("dot.png", (double) Values.squareButtonSize / 2, (double) Values.squareButtonSize / 2, false, false);


    public PlayerGUI() {
        setupGrid();
    }

    public void setupGrid() { // setuping buttonGrid for player
        for (int i = 0; i < buttonGrid.length; i++) {
            for (int j = 0; j < buttonGrid[i].length; j++) {
                buttonGrid[i][j] = setupButton(i, j);
            }
        }
    }

   public Button setupButton(int i, int j) { // setuping button implementations for Cell for player
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

   public void disableButtons() {
        for (Button[] buttons : buttonGrid) {
            for (Button button : buttons) {
                button.setDisable(true);
            }
        }
    }

    public ImageView getShotImage() {


        return new ImageView(playI);
    }

    public ImageView getMissImage() {

        return new ImageView(play2);
    }

    abstract void setupButtonAction(final int i, final int j);
}
