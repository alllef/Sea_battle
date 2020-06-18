package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class Main extends Application {
    MyPain firstPain = new MyPain();



    @Override
    public void start(Stage primaryStage) {




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
