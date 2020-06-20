package sample;


import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class ComputerGUI extends PlayerGUI {
    private final Computer computer = new Computer();
    private final Text finalText = setupText();
    private HumanGUI humanGUI;

    public void setHumanGUI(HumanGUI humanGUI) {
        this.humanGUI = humanGUI;
    }

    public void setFinalText(String text){
        finalText.setText(text);
        finalText.setVisible(true);
    }

    public Text getFinalText(){
        return finalText;
    }

    public ComputerGUI(HumanGUI humanGUI) {
        super();
        this.humanGUI = humanGUI;
    }

    private Text setupText() { // setuping Text for final notify
        Text text = new Text();
        text.setVisible(false);
        text.setFont(Font.font("Gill Sans", FontWeight.MEDIUM, FontPosture.REGULAR, 80));
        text.setX(650);
        text.setY(800);
        text.setFill(Color.rgb(0, 51, 204));
        text.setStroke(Color.BLUE);
        text.setStrokeWidth(3);
        return text;
    }

    @Override
    public void setupButtonAction(final int i, final int j) { // setuping what graphic implementation of Cell will do after clicking on it
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
                        if (computer.shipList.isEmpty()) {
                            disableButtons();
                            setFinalText("YOU WIN");
                        }
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
                        case "Попал" -> {
                            computer.getGuessesforResults().add(guess);
                            computer.getAffectedshipCells().add(guess);
                        }
                        case "Потопил" ->{
                            computer.getGuessesforResults().clear();
                            computer.getAffectedshipCells().add(guess);
                        }
                        case "Мимо" -> isMiss = true;
                    }

                    if (humanGUI.getHuman().shipList.isEmpty()) {
                        disableButtons();
                        setFinalText("YOU LOSE");
                        for (Ship ship: computer.shipList){
                            for(Cell cell:ship.getLocationCells()){
                                if(cell.usedForShip()) buttonGrid[cell.row()][cell.col()].setStyle("-fx-background-color: #0033cc");
                            }
                        }
                        isMiss = true;
                    }

                }
            }
        }
    }
}
