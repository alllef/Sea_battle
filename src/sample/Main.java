package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main extends Application {
    MyPain firstPain = new MyPain();

   /* GridPane makeHumanGrid() {
        GridPane tmpGrid = new GridPane();
        tmpGrid.setGridLinesVisible(true);
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                Button button =gui.buttonGrid[i][j];
                tmpGrid.add(button, j, i);
            }
        }
        return tmpGrid;
    }*/

    @Override
    public void start(Stage primaryStage) {


// установка надписи
       /* Text text = new Text("Hello from JavaFX!");
        text.setLayoutY(80);    // установка положения надписи по оси Y
        text.setLayoutX(100);   // установка положения надписи по оси X*/

        // FlowPane gamePane = new FlowPane();

//        Button buttonGetInfo = new Button("Info");
//        Button buttonGetInfo1 = new Button("Info1");

        //group.getChildren().add(buttonGetInfo);
//        group.getChildren().add(buttonGetInfo1);


        // Image playI=new Image("file:///c:/Users/Farhad/Desktop/icons/play2.jpg");
        Image playI = new Image("https://habrastorage.org/files/e21/e69/dcc/e21e69dcca4148ee8b6215fac9b0706b.jpg");
        ImageView iv1 = new ImageView(playI);
        iv1.setFitHeight(100);
        iv1.setFitWidth(100);

        Image playI1 = new Image("https://habrastorage.org/files/e9f/7bc/988/e9f7bc98872e467dbb3e237c7f0c7f3e.jpg");
        ImageView iv11 = new ImageView(playI1);
        iv11.setFitHeight(100);
        iv11.setFitWidth(100);


// Для отображения сетки
      /*  */
        Scene setup = new Scene(firstPain.gamePane);
        firstPain.button.setOnAction(event -> {
            if(firstPain.gui.human.setupCounter==15)  {
              firstPain.gamePane.getChildren().add(firstPain.makeComputerGrid());
                firstPain.gamePane.getChildren().remove(1);
            }
        });
        primaryStage.setScene(setup);


        primaryStage.setTitle("Sea battle");
        primaryStage.setFullScreen(true);
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
