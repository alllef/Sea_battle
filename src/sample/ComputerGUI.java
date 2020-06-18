package sample;


public class ComputerGUI extends PlayerGUI {
    private Computer computer = new Computer();

    private HumanGUI humanGUI;

    public ComputerGUI(HumanGUI humanGUI) {
        super();
        this.humanGUI = humanGUI;
    }


    @Override
    public void setupButtonAction(final int i, final int j) {
        String result = computer.checkGuess(new Cell(i, j, true));


        switch (result) {
            case "Попал" -> {
                buttonGrid[i][j].setGraphic(getShotImage());
                buttonGrid[i][j].setStyle("-fx-background-color: #0033cc");
                buttonGrid[i][j].setDisable(true);
            }
            case "Потопил" -> {

                buttonGrid[i][j].setGraphic(getShotImage());

                for (Ship ship : computer.shipList) {
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

                        computer.shipList.remove(ship);
                        if (computer.shipList.isEmpty()) disableButtons();
                        break;
                    }
                }
            }

            case "Мимо" -> {

                buttonGrid[i][j].setGraphic(getMissImage());
                buttonGrid[i][j].setStyle("-fx-background-color: #ffff99; -fx-border-color:black");
                buttonGrid[i][j].setDisable(true);

                boolean isMiss = false;
                while (!isMiss) {
                    Cell guess = computer.makeGuess();
                    String humanResult = humanGUI.checkGuess(new Cell(guess.row(), guess.col(), true));

                    switch (humanResult) {
                        case "Попал" -> computer.guessesforResults.add(guess);
                        case "Потопил" -> computer.guessesforResults.clear();
                        case "Мимо" -> isMiss = true;
                    }

                    if (humanGUI.getHuman().shipList.isEmpty()) {
                        disableButtons();
                        isMiss = true;
                    }

                }
            }
        }
    }

  public   ComputerGUI() {
        super();
    }
}
