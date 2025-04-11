package game.waterpipe;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    private int SIZE = 5; // 5x5 grid --> This will change if user pick LEVEVL 2
    private static final int CELL_SIZE = 70; // Size of each square cell
    private static final int SCENE_SIZE = 700; // 700x700 scene

    @Override
    public void start(Stage stage) throws Exception {
        Stage stageChooseLevel = new Stage();

    }

    public static void main(String[] args) {
        launch();
    }
}