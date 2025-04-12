package game.waterpipe;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;

public class chooseLevel {
    private static Image icon;
    private static Image backgroundImage;
    private static Image level1;
    private static Image level2;
    private static Image level3;
    private static int SCENE_SIZE;
    private static String cssPath;
    private static int width = HelloApplication.SCENE_WIDTH;
    private static int height = HelloApplication.SCENE_HEIGHT;

    public chooseLevel() {

        this.icon = loadImage("pics/water-pipe.png");
        this.backgroundImage = loadImage("pics/background.png");
        this.level1 = loadImage("pics/level1.png");
        this.level2 = loadImage("pics/level2.png");
        this.level3 = loadImage("pics/level3.png");
        this.cssPath = getClass().getResource("style.css").toExternalForm();
    }

    private Image loadImage(String path) {
        try {
            return new Image(getClass().getResourceAsStream(path));
        } catch (Exception e) {
            System.err.println("Error loading image: " + path);
            return null;
        }
    }

    public void DrawStage(){     // this will return level num
        Stage stageChooseLevel = new Stage();
        stageChooseLevel.getIcons().add(icon);
        stageChooseLevel.setTitle("Water Pipe");
        stageChooseLevel.setResizable(false);
        int[] levels = new int[2];
        levels[0] = 0;

        BackgroundImage bgImage = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(100, 100, true, true, true, true)
        );

        Label label = new Label("Choose Level :");
        label.getStyleClass().add("text");

        // .................................... BUTTONS SET ....................................


        ImageView level1view = new ImageView(level1);
        ImageView level2view = new ImageView(level2);
        ImageView level3view = new ImageView(level3);

        Button button1 = new Button();
        Button button2 = new Button();
        Button button3 = new Button();
        button1.setGraphic(level1view);
        button2.setGraphic(level2view);
        button3.setGraphic(level3view);

        button1.getStyleClass().add("button");
        button2.getStyleClass().add("button");
        button3.getStyleClass().add("button");

        button1.setOnAction(e -> {
            //startGame(1);
            levels[0] = 1;
            stageChooseLevel.close();
        });
        button2.setOnAction(e -> {
            //startGame(2);
            levels[0] = 2;
            stageChooseLevel.close();
        });
        button3.setOnAction(e -> {
            //startGame(3);
            levels[0] = 3;
            stageChooseLevel.close();
        });

        HBox buttonsLayout = new HBox(10);
        buttonsLayout.setAlignment(Pos.CENTER);
        buttonsLayout.getChildren().addAll(button1, button2, button3);

        //...............................................................................................

        VBox layout = new VBox(20);
        layout.setBackground(new Background(bgImage));
        layout.setMinSize(width, height);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(label, buttonsLayout);
        //layout.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));


        Scene scene1 = new Scene(layout, width, height);
        scene1.getStylesheets().add(cssPath);
        stageChooseLevel.setScene(scene1);
        stageChooseLevel.show();
        //return levels[0];
    }

}
