package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    MyPane firstPane = new MyPane();



    @Override
    public void start(Stage primaryStage) {
        Scene setup = new Scene(firstPane.getGamePane()); // setuping main stage of a project
        primaryStage.setScene(setup);
        primaryStage.setTitle("Sea battle");
        primaryStage.setFullScreen(true);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
