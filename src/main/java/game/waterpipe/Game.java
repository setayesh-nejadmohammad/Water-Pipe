package game.waterpipe;

import javafx.animation.RotateTransition;
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

public class Game {
    private int level;
    private static Image icon;
    private static Image backgroundImage;
    private static int CELL_SIZE = HelloApplication.CELL_SIZE;
    private static int SCENE_WIDTH = HelloApplication.SCENE_WIDTH;
    private static int SCENE_HEIGHT = HelloApplication.SCENE_HEIGHT;
    private int moves = 5;
    int SIZE = 5;


    public Game(int level) {
        this.level = level;
        this.icon = loadImage("pics/water-pipe.png");
        this.backgroundImage = loadImage("pics/background.png");
    }


    private Image loadImage(String path) {
        try {
            return new Image(getClass().getResourceAsStream(path));
        } catch (Exception e) {
            System.err.println("Error loading image: " + path);
            return null;
        }
    }

    public void DrawStage(Stage stage) {
        //Stage stage = new Stage();
        stage.setTitle("Water Pipe");
        stage.setResizable(false);
        stage.getIcons().add(icon);

        if(level == 1) SIZE = 5;
        else if(level == 2 || level == 3) SIZE = 7;

        BackgroundImage bgImage = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(100, 100, true, true, true, true)
        );

        ImageView backgroundView = new ImageView(backgroundImage);

        //VBox layout = new VBox(20);
        //layout.setBackground(new Background(bgImage));

        Pane pane = new Pane();
        pane.setPrefSize(SCENE_WIDTH, SCENE_HEIGHT);
        pane.getChildren().addAll(backgroundView);

        // Calculate center position
        double centerX = (SCENE_WIDTH - (CELL_SIZE * SIZE)) / 2;
        double centerY = (SCENE_HEIGHT - (CELL_SIZE * SIZE)) / 2;

        GridPane grid = new GridPane();
        grid.setGridLinesVisible(false); // We'll draw our own borders
        //grid.setBackground(new Background(bgImage));
        grid.setLayoutX(centerX);
        grid.setLayoutY(centerY);
        grid.setPadding(new Insets(0)); // Remove internal padding
        grid.setHgap(0); // Remove horizontal gap
        grid.setVgap(0); // Remove vertical gap


        // Create 5x5 grid of squares
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                Rectangle rect = new Rectangle(CELL_SIZE, CELL_SIZE);
                rect.setFill(null);
                rect.setStroke(Color.BLACK);
                rect.setStrokeWidth(1);
                grid.add(rect, col, row);
            }
        }

        Pipe[][] pipe=new Pipe[10][10];
        Map map = new Map(level);
        map.fillPipes(pipe);

        final int[] moveArr = {moves};
        Label moveChange = new Label("Moves : " + moveArr[0]+ "");
        final Label[] moveLabelArr = {moveChange};
        moveLabelArr[0].setLayoutX(270);
        moveLabelArr[0].setLayoutY(50);

        // Put Stupid pipe pics in grid  + Manage pipes movements
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (pipe[row][col] != null) {
                    ImageView pipeView = new ImageView(pipe[row][col].getImage());
                    pipeView.setFitHeight(CELL_SIZE);
                    pipeView.setFitWidth(CELL_SIZE);

                    if (row == 0 && col == 0 || col == SIZE - 1 && row == SIZE - 1) {
                        grid.add(pipeView, col, row);
                        continue;
                    }

                    // Make pipes more interactive
                    pipeView.setStyle("-fx-cursor: hand;");
                    pipeView.setOnMouseEntered(e -> pipeView.setStyle("-fx-effect: dropshadow(gaussian, #1598ec, 15, 0.7, 0, 0); -fx-cursor: hand;"));
                    pipeView.setOnMouseExited(e -> pipeView.setStyle("-fx-effect: null; -fx-cursor: hand;"));

                    // Store row and col in final variables for use in lambda
                    final int r = row;
                    final int c = col;

                    pipeView.setOnMouseClicked(event -> {
                        System.out.println("Mouse Clicked!");
                        if (level == 2) moveArr[0] -= 1;
                        moveLabelArr[0].setText("Moves : " + (moveArr[0]));
                        if (moveArr[0] == -1) {
                            limits noMoves = new limits();
                            noMoves.outOfMoves();
                            stage.close();
                        }
                        //System.out.println("moves: " + moveArr[0]);

                        // Disable during animation to prevent spam clicks
                        pipeView.setDisable(true);

                        // Determine rotation direction based on mouse button
                        int rotationAngle = 90;
                        if (event.getButton() == MouseButton.SECONDARY) {
                            rotationAngle = -90; // Rotate counter-clockwise for right click
                        }


                        // Create rotation animation
                        RotateTransition rt = new RotateTransition(Duration.millis(300), pipeView);
                        rt.setByAngle(rotationAngle); // Rotate by 90 degrees
                        rt.setCycleCount(1);


                        rt.play(); // Start the animation
                    });
                    grid.add(pipeView, col, row);
                }

            }
        }

        pane.getChildren().addAll(grid);
        Scene scene = new Scene(pane, SCENE_WIDTH, SCENE_HEIGHT);
        stage.setScene(scene);
        stage.show();
    }
}
