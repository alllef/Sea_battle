package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

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



// Для отображения сетки
      /*  */
        Scene setup = new Scene(firstPain.gamePane);
        primaryStage.setScene(setup);
        primaryStage.setTitle("Sea battle");
        primaryStage.setFullScreen(true);
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
