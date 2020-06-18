package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    MyPane firstPain = new MyPane();



    @Override
    public void start(Stage primaryStage) {




// Для отображения сетки
      /*  */
        Scene setup = new Scene(firstPain.getGamePane());

        primaryStage.setScene(setup);
        primaryStage.setTitle("Sea battle");
        primaryStage.setFullScreen(true);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
