package sample;


import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class HumanGUI extends PlayerGUI {

    private final Human human = new Human();
    Text setupedShipsText = setupText();

    public Human getHuman() {
        return human;
    }


    private Text setupText() {
        Text text = new Text();
        text.setVisible(true);
        text.setFont(Font.font("Gill Sans", FontWeight.MEDIUM, FontPosture.REGULAR, 25));
        text.setX(700);
        text.setY(750);
        text.setFill(Color.rgb(255, 255, 255));
        text.setText("Ships to setup remain " + (human.shipList.size() - human.getSetupCounter()));
        return text;
    }

    @Override
    public void setupButtonAction(final int i, final int j) {

        if (human.getSetupCounter() < human.shipList.size()) {
            Human.AllCoordsVariants allCoordsVariants = human.setupShip(i, j);
            if (!allCoordsVariants.isEmpty()) {
                if (human.shipList.get(human.getSetupCounter()).getSize() == 1) {
                    setShipsOnButton(buttonGrid, allCoordsVariants.up(), allCoordsVariants);
                } else {
                    buttonGrid[i][j].setStyle("-fx-background-color: #7b939c");
                    disableButtons();
                    ArrayList<ArrayList<Cell>> argumentsList = allCoordsVariants.getArgumentsAsList();
                    for (ArrayList<Cell> list : argumentsList) {
                        if (!list.isEmpty()) {
                            Cell first = list.get(1);
                            buttonGrid[first.row()][first.col()].setDisable(false);
                            buttonGrid[first.row()][first.col()].setStyle("-fx-background-color: #3d3d5c");
                            buttonGrid[first.row()][first.col()].setOnAction(event -> setShipsOnButton(buttonGrid, list, allCoordsVariants));
                        }
                    }
                }
            }
        }
    }

    public void setShipsOnButton(Button[][] buttonGrid, ArrayList<Cell> coords, Human.AllCoordsVariants allCoordsVariants) {
        human.setCells(coords);
        for (Cell coord : coords) {
            buttonGrid[coord.row()][coord.col()].setStyle("-fx-background-color: #0033cc");
            buttonGrid[coord.row()][coord.col()].setDisable(true);
        }
        if (coords.size() != 1) {
            ArrayList<ArrayList<Cell>> argumentsList = allCoordsVariants.getArgumentsAsList();
            for (ArrayList<Cell> list : argumentsList) {

                if (!list.isEmpty() && !list.get(1).usedForShip()) {
                    Cell first = list.get(1);
                    buttonGrid[first.row()][first.col()].setOnAction(setupButton(first.row(), first.col()).getOnAction());
                    buttonGrid[first.row()][first.col()].setStyle(setupButton(first.row(), first.col()).getStyle());

                }
            }


            for (Button[] buttons : buttonGrid) {
                for (Button button : buttons) {
                    if (button.getStyle().equals(setupButton(0, 0).getStyle()))
                        button.setDisable(false);
                }
            }
        }
        setupedShipsText.setText("Ships to setup remain " + (human.shipList.size() - human.getSetupCounter()));
    }

    public String checkGuess(Cell cell) {

        String result = human.checkGuess(cell);
        System.out.println(result);
        switch (result) {
            case "Попал" -> {

                buttonGrid[cell.row()][cell.col()].setGraphic(getShotImage());
                buttonGrid[cell.row()][cell.col()].setStyle("-fx-background-color: #0033cc");
                buttonGrid[cell.row()][cell.col()].setDisable(true);

            }
            case "Потопил" -> {
                buttonGrid[cell.row()][cell.col()].setGraphic(getShotImage());

                for (Ship ship : human.shipList) {
                    boolean hasUsedForShip = false;

                    for (Cell cell1 : ship.getLocationCells()) {
                        if (cell1.usedForShip()) {
                            hasUsedForShip = true;
                            break;
                        }
                    }

                    if (!hasUsedForShip) {
                        for (Cell cell1 : ship.getLocationCells()) {
                            buttonGrid[cell1.row()][cell1.col()].setStyle("-fx-background-color: #0033cc; -fx-border-color:red; -fx-border-width:3px 3px");
                            buttonGrid[cell1.row()][cell1.col()].setDisable(true);
                        }
                        human.shipList.remove(ship);
                        break;
                    }
                }
            }

            case "Мимо" -> {
                buttonGrid[cell.row()][cell.col()].setGraphic(getMissImage());
                buttonGrid[cell.row()][cell.col()].setStyle("-fx-background-color: #ffff99; -fx-border-color:black");
                buttonGrid[cell.row()][cell.col()].setDisable(true);
            }
        }
        return result;
    }

    public HumanGUI() {
        super();
    }

}