package sample;


import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.*;

public class MyPane {
    private final Pane gamePane = new Pane();
    private HumanGUI humanGUI = new HumanGUI();
    private ComputerGUI computerGUI = new ComputerGUI(humanGUI);
    private final Button toGameButton = setupButton("To game", 1350);
    private final Button restartButton = setupButton("Restart", 50);
    private final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public Pane getGamePane() {
        return gamePane;
    }

    private void makeHumanGrid() { // making grid for user
        Text text = setupText("Your field", 190);
        text.setVisible(true);
        gamePane.getChildren().add(text);

        GridPane tmpGrid = new GridPane();
        tmpGrid.setStyle("-fx-background-color: #ffffff");
        tmpGrid.setGridLinesVisible(true);
        for (int i = 0; i < Values.squareGridSize + 1; i++) {
            for (int j = 0; j < Values.squareGridSize + 1; j++) {
                if (i == 0 && j > 0) {
                    Label label = setupLabel(String.valueOf(alphabet.charAt(j - 1)));
                    tmpGrid.add(label, j, i);


                } else if (j == 0 && i > 0) {
                    Label label = setupLabel(String.valueOf(i));
                    tmpGrid.add(label, j, i);


                } else if (i == 0) tmpGrid.add(setupLabel(""), j, i);
                else {

                    Button button = humanGUI.buttonGrid[i - 1][j - 1];
                    tmpGrid.add(button, j, i);
                }
            }
        }

        tmpGrid.setLayoutX(20);
        tmpGrid.setLayoutY(40);
        gamePane.getChildren().add(0, tmpGrid);


    }

    private void setActionForButtons() { // seting what buttons restart and to game will do when clicking on them
        restartButton.setOnAction(event -> {
            gamePane.getChildren().remove(humanGUI.setupedShipsText);
            this.humanGUI = new HumanGUI();
            computerGUI.setHumanGUI(humanGUI);
            gamePane.getChildren().remove(0);
            gamePane.getChildren().add(humanGUI.setupedShipsText);
            makeHumanGrid();
        });
        toGameButton.setOnAction(event -> {
            if (humanGUI.getHuman().getSetupCounter() == humanGUI.getHuman().shipList.size()) {
                gamePane.getChildren().add(makeComputerGrid());
                gamePane.getChildren().remove(toGameButton);
                gamePane.getChildren().remove(humanGUI.setupedShipsText);
                restartButton.setOnAction(event1 -> {
                    gamePane.getChildren().clear();
                    this.humanGUI = new HumanGUI();
                    this.computerGUI = new ComputerGUI(humanGUI);
                    setupPane();
                });
            }
        });
    }

    private void setupPane() { // setuping default Pane where all other graphical objects will be stored
        gamePane.setStyle("-fx-background-image: url(\"tomswallpapers.com-76799.jpg\")");
        makeHumanGrid();
        gamePane.getChildren().add(toGameButton);
        gamePane.getChildren().add(computerGUI.getFinalText());
        gamePane.getChildren().add(restartButton);
        gamePane.getChildren().add(humanGUI.setupedShipsText);
        setActionForButtons();
    }

    private GridPane makeComputerGrid() { // making grid for computer
        Text text = setupText("Enemy field", 880);
        text.setVisible(true);
        gamePane.getChildren().add(text);
        GridPane tmpGrid = new GridPane();
        tmpGrid.setStyle("-fx-background-color: #ffffff");
        tmpGrid.setGridLinesVisible(true);


        for (int i = 0; i < Values.squareGridSize + 1; i++) {
            for (int j = 0; j < Values.squareGridSize + 1; j++) {
                if (i == 0 && j > 0) {
                    Label label = setupLabel(String.valueOf(alphabet.charAt(j - 1)));
                    tmpGrid.add(label, j, i);
                } else if (j == 0 && i > 0) {
                    Label label = setupLabel(String.valueOf(i));
                    tmpGrid.add(label, j, i);

                } else if (i == 0) tmpGrid.add(setupLabel(""), j, i);
                else {

                    Button button = computerGUI.buttonGrid[i - 1][j - 1];
                    tmpGrid.add(button, j, i);
                }
            }
        }
        tmpGrid.setLayoutX(720);
        tmpGrid.setLayoutY(40);
        return tmpGrid;
    }

    private Text setupText(String userText, int x) { // setuping text
        Text text = new Text(userText);

        text.setVisible(false);
        text.setFont(Font.font("Gill Sans", FontWeight.LIGHT, FontPosture.REGULAR, 25));
        text.setX(x);
        text.setY(27);
        text.setFill(Color.rgb(0, 51, 204));
        text.setStroke(Color.BLUE);
        text.setStrokeWidth(3);
        return text;
    }

    private Label setupLabel(String text) { //setuping Label

        Label label = new Label();
        label.setPrefHeight(Values.squareButtonSize);
        label.setPrefWidth(Values.squareButtonSize);
        label.setAlignment(Pos.CENTER);
        label.setText(text);
        label.setTextAlignment(TextAlignment.CENTER);
        label.setFont(Font.font("Gill Sans", FontWeight.BOLD, FontPosture.REGULAR, 20));
        label.setTextFill(Color.rgb(0, 51, 204));
        label.setStyle("-fx-background-color: #ffffff; -fx-border-color:black; -fx-border-width:0.5px 0.5px");
        return label;
    }

    private Button setupButton(String text, int x) { // stuping buttons restart and to game
        Button button = new Button();
        button.setText(text);
        button.setLayoutX(x);
        button.setLayoutY(750);
        button.setPrefHeight(Values.squareButtonSize * 2);
        button.setPrefWidth(Values.squareButtonSize * 3);
        button.setStyle("-fx-background-color: #0033cc; -fx-border-color:black");
        button.setFont(Font.font("Gill Sans", FontWeight.BOLD, FontPosture.REGULAR, 20));
        button.setTextFill(Color.rgb(255, 255, 255));
        return button;
    }


    public MyPane() {
        setupPane();
    }


}
