package at.fhtw.highscores;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        showStage(primaryStage);
    }
    public static Parent showStage(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(Main.class.getResource("mainWindow.fxml"));
        primaryStage.setTitle("Highscore");
        primaryStage.setScene(new Scene(root, 400, 275));
        primaryStage.setMinWidth(400);
        primaryStage.show();

        return root;
    }


    public static void main(String[] args) {
        launch(args);
    }
}
