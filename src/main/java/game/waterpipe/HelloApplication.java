package game.waterpipe;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
        private int SIZE = 5; // 5x5 grid --> This will change if user pick LEVEVL 2
        private static final int CELL_SIZE = 70; // Size of each square cell
        public static final int SCENE_WIDTH = 900;
        public static final int SCENE_HEIGHT = 700;
    //Image icon = new Image(getClass().getResourceAsStream("/pics/water-pipe.png"));

    @Override
    public void start(Stage stage) throws Exception {
        chooseLevel chonenlevel = new chooseLevel();
        chonenlevel.DrawStage();
    }

    public static void main(String[] args) {
        launch(args);
    }
}