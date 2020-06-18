package sample;


import javafx.scene.control.Button;
import java.util.ArrayList;

public class HumanGUI extends PlayerGUI {

   private Human human = new Human();

public Human getHuman(){
    return human;
}


    @Override
   public void setupButtonAction(final int i, final int j) {

        if (human.getSetupCounter() < human.shipList.size()) {
            Human.AllCoordsVariants allCoordsVariants = human.setupShip(i, j);
            if (allCoordsVariants != null) {
                if (human.shipList.get(human.getSetupCounter()).getSize() == 1) {
                    setForOneShip(buttonGrid, allCoordsVariants.up());
                } else {
                    disableButtons();
                    ArrayList<ArrayList<Cell>> argumentsList = allCoordsVariants.getArgumentsAsList();
                    for (ArrayList<Cell> list:argumentsList){
                        if(!list.isEmpty()){
                            buttonGrid[i - 1][j].setDisable(false);
                            buttonGrid[i - 1][j].setStyle("-fx-background-color: #3d3d5c");
                            buttonGrid[i - 1][j].setOnAction(event -> setShipsOnButton(buttonGrid, allCoordsVariants.up(), allCoordsVariants));
                        }
                    }
                }
            }
        }
    }



   public void setForOneShip(Button[][] buttonGrid, ArrayList<Cell> coords) {
        human.setCells(coords);
        buttonGrid[coords.get(0).row()][coords.get(0).col()].setStyle("-fx-background-color: #0033cc");
        buttonGrid[coords.get(0).row()][coords.get(0).col()].setDisable(true);
    }

  public void setShipsOnButton(Button[][] buttonGrid, ArrayList<Cell> coords, Human.AllCoordsVariants allCoordsVariants) {
        human.setCells(coords);

        for (Cell coord : coords) {
            buttonGrid[coord.row()][coord.col()].setStyle("-fx-background-color: #0033cc");
            buttonGrid[coord.row()][coord.col()].setDisable(true);
        }

        ArrayList<ArrayList<Cell>> argumentsList = allCoordsVariants.getArgumentsAsList();
        for (ArrayList<Cell> list:argumentsList){

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

   public String checkGuess(Cell cell) {

        String result = human.checkGuess(cell);
        System.out.println(result);
        // checkGUIGuess(computerGUI.computer.makeGuess());
        //checkGUIGuess(computerGUI.computer.makeGuess());
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