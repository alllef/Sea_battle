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
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main extends Application {

    /**
     * https://www.baeldung.com/java-record-keyword
     */
    static record TestRec(char row, byte col) {

        private static final Pattern rowPattern = Pattern.compile("([a-z])");

        public TestRec {
            Objects.checkIndex(col, 10);
            if (!rowPattern.matcher(String.valueOf(row)).find()) throw new IllegalArgumentException(String.format("row: '%c' should be ([a-z])", row));
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        try {
            new TestRec('1', (byte) 0);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            new TestRec('A', (byte) 0);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            new TestRec('a', (byte) 10);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            new TestRec('a', (byte) 11);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(new TestRec('a', (byte) 0));
        System.out.println(new TestRec('a', (byte) 0).equals(new TestRec('a', (byte) 0)));
        System.out.println(!(new TestRec('a', (byte) 0).equals(new TestRec('a', (byte) 1))));

// установка надписи
        Text text = new Text("Hello from JavaFX!");
        text.setLayoutY(80);    // установка положения надписи по оси Y
        text.setLayoutX(100);   // установка положения надписи по оси X

        Group group = new Group(text);

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

        Button playB = new Button("", iv1);

        playB.setOnAction((event) ->
                //
                playB.setGraphic(iv11)
        );

        GridPane root = new GridPane();
// Для отображения сетки
        root.setGridLinesVisible(true);
        root.add(new Label("0x000000"), 0, 0);
        root.add(new Label("0x1"), 0, 1);
        root.add(new Label("1x1"), 1, 1);
        root.add(playB, 1, 2);
        root.add(new Button("5x5"), 5, 5);

        group.getChildren().add(root);

        Scene scene = new Scene(group);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Sea battle");
        primaryStage.setWidth(600);
        primaryStage.setHeight(600);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
