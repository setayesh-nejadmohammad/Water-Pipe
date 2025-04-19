package game.waterpipe;
import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class HelloApplication extends Application {
    public int SIZE = 5; // 5x5 grid --> This will change if user pick LEVEVL 2
    public static final int CELL_SIZE = 70; // Size of each square cell
    public static final int SCENE_WIDTH = 900;
    public static final int SCENE_HEIGHT = 700;
    @Override
    public void start(Stage stage) throws Exception {
        chooseLevel chonenlevel = new chooseLevel(stage);
        chonenlevel.DrawStage();
    }
    public static void main(String[] args) {
        launch(args);
    }
}