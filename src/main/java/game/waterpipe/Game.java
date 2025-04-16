package game.waterpipe;

import javafx.animation.RotateTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Stack;

public class Game {
    private int level;
    private static Image icon;
    private static Image backgroundImage;
    private static Image home;
    private static Image play;
    private static Image undo;
    private static Image restart;
    private static Image nextLevel;
    private static int CELL_SIZE = HelloApplication.CELL_SIZE;
    private static int SCENE_WIDTH = HelloApplication.SCENE_WIDTH;
    private static int SCENE_HEIGHT = HelloApplication.SCENE_HEIGHT;
    private int moves = 15;
    final int[] moveArr = {moves};
    int SIZE = 5;
    limits limit = new limits();
    int ICON_SIZE = 50;
    Button UndoButton = new Button();
    private final Stack<int[]> moveHistory = new Stack<>(); // to store [row, col, previousNum]
    private final Stack<Integer> rotationHistory = new Stack<>(); // to store rotate


    public Game(int level) {
        this.level = level;
        this.icon = loadImage("pics/water-pipe.png");
        this.backgroundImage = loadImage("pics/background.png");
        this.home = loadImage("pics/home.png");
        this.play = loadImage("pics/play.png");
        this.undo = loadImage("pics/undo.png");
        this.restart = loadImage("pics/restart.png");
        this.nextLevel = loadImage("pics/next level.png");
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

        Pipe[][] pipe = new Pipe[10][10];
        Map map = new Map(level);
        map.fillPipes(pipe);


        Label moveChange = new Label("Moves : " + moveArr[0]+ "");
        final Label[] moveLabelArr = {moveChange};
        moveLabelArr[0].setLayoutX(400);
        moveLabelArr[0].setLayoutY(40);
        moveLabelArr[0].getStyleClass().add("text");

        if(level == 2) pane.getChildren().addAll(moveLabelArr[0]);
        if(level == 3) limit.TimeLimit(stage, pane);

        pane.getChildren().addAll(grid);
        Scene scene = new Scene(pane, SCENE_WIDTH, SCENE_HEIGHT);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        sideButtons(scene, stage, pane, pipe, grid, moveLabelArr);
        MovePipe(grid, pipe, moveLabelArr, stage, pane, scene);
        stage.setScene(scene);
        stage.show();
    }

    private void sideButtons(Scene scene, Stage stage, Pane pane, Pipe[][] pipe, GridPane grid, Label[] moveLabelArr) {
        ImageView homeView = new ImageView(home);
        homeView.setFitHeight(ICON_SIZE); homeView.setFitWidth(ICON_SIZE);
        ImageView playView = new ImageView(play);
        playView.setFitHeight(ICON_SIZE); playView.setFitWidth(ICON_SIZE);
        ImageView undoView = new ImageView(undo);
        undoView.setFitHeight(ICON_SIZE); undoView.setFitWidth(ICON_SIZE);
        ImageView restartView = new ImageView(restart);
        restartView.setFitHeight(ICON_SIZE); restartView.setFitWidth(ICON_SIZE);
        ImageView nextLevelView = new ImageView(nextLevel);
        nextLevelView.setFitHeight(ICON_SIZE); nextLevelView.setFitWidth(ICON_SIZE);

        Button HomeButton = new Button();
        Button PlayButton = new Button();
        //Button UndoButton = new Button();
        Button RestartButton = new Button();
        Button NextLevelButton = new Button();

        HomeButton.getStyleClass().add("button");
        PlayButton.getStyleClass().add("button");
        UndoButton.getStyleClass().add("button");
        RestartButton.getStyleClass().add("button");
        NextLevelButton.getStyleClass().add("button");

        HomeButton.setGraphic(homeView);
        PlayButton.setGraphic(playView);
        UndoButton.setGraphic(undoView);
        RestartButton.setGraphic(restartView);
        NextLevelButton.setGraphic(nextLevelView);

        Stage gameResult = new Stage(); // This is for play button and the result widow
        NextLevelButton.setOnAction(event ->{
            if(level == 1){
                Game game = new Game(2);
                game.DrawStage(stage);
                gameResult.close();
            }
            else if(level == 2){
                Game game = new Game(3);
                game.DrawStage(stage);
                gameResult.close();
            }
        });

        HomeButton.setOnAction(event -> {
            chooseLevel chonenlevel = new chooseLevel(stage);
            chonenlevel.DrawStage();
        });

        PlayButton.setOnAction(event -> {
            WayCheck gameCheck = new WayCheck(pipe, level);
            boolean result = gameCheck.stupidCheck();
            limit.TimeStop();

            Label label;
            VBox root = new VBox();
            Scene gameResultScene;
            gameResult.setTitle("Game Result");


            if(result){
                label = new Label("YOU WIN!");
                gameResultScene = new Scene(root, 400, 200, Color.LIGHTBLUE);
                root.setStyle("-fx-background-color: green;");
                root.getChildren().add(label);
                root.getChildren().add(NextLevelButton);
            }
            else{
                label = new Label("GAME OVER!");
                gameResultScene = new Scene(root, 400, 200, Color.RED);
                root.setStyle("-fx-background-color: red;");
                root.getChildren().add(label);
            }

            root.setAlignment(Pos.CENTER);
            root.setSpacing(20);
            label.getStyleClass().add("text");
            label.setStyle("-fx-text-fill: white;");

            gameResultScene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
            gameResult.setScene(gameResultScene);
            gameResult.show();
        });

        RestartButton.setOnAction(event -> {
            limit.resetTimer();
            Map map = new Map(level);
            map.fillPipes(pipe);
            MovePipe(grid, pipe, moveLabelArr, stage, pane, scene);
        });

        HBox hbox = new HBox();
        hbox.getChildren().addAll(HomeButton, PlayButton, RestartButton, UndoButton);
        hbox.setPadding(new Insets(20));
        hbox.setSpacing(6);
        pane.getChildren().add(hbox);
    }

    private void MovePipe(GridPane grid, Pipe[][] pipe, Label[] moveLabelArr, Stage stage, Pane pane, Scene scene) {
        moveArr[0] = moves;
        moveLabelArr[0].setText("Moves : " + (moveArr[0]));

        // Clean the grid
        for(int row = 0; row < SIZE; row++){
            for(int col = 0; col < SIZE; col++){
                final int finalCol = col;
                final int finalRow = row;
                grid.getChildren().removeIf(node ->
                        GridPane.getColumnIndex(node) == finalCol && GridPane.getRowIndex(node) == finalRow
                );
            }
        }

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
        // Put Stupid pipe pics in grid  + Manage pipes movements
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (pipe[row][col] != null) {
                    ImageView pipeView = new ImageView(pipe[row][col].getImage());
                    pipeView.setFitHeight(CELL_SIZE);
                    pipeView.setFitWidth(CELL_SIZE);

                    if (row == 0 && col == 0 || col == SIZE - 1 && row == SIZE - 1 || pipe[row][col].getState() == Pipe.pipeState.STATIC) {
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

                    //Undo(pipeView, grid, pipe, pane, scene);

                    pipeView.setOnMouseClicked(event -> {

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

                        moveHistory.push(new int[]{r, c, pipe[r][c].getNum()});
                        rotationHistory.push(pipe[r][c].rotateAngle);


                        // Determine rotation direction based on mouse button
                        int rotationAngle = 90;
                        if (event.getButton() == MouseButton.SECONDARY) {
                            rotationAngle = -90; // Rotate counter-clockwise for right click
                        }
                        pipe[r][c].rotateAngle = rotationAngle;


                        // Create rotation animation
                        RotateTransition rt = new RotateTransition(Duration.millis(300), pipeView);
                        rt.setByAngle(rotationAngle); // Rotate by 90 degrees
                        rt.setCycleCount(1);

                        rt.setOnFinished(e -> {
                            pipeView.setDisable(false);
                        });


                        rt.play(); // Start the animation

                        // Update the pipe array
                        if (pipe[r][c].getNum() == 1) {
                            pipe[r][c].setNum(2);
                        } else if (pipe[r][c].getNum() == 2) {
                            pipe[r][c].setNum(1);
                        } else if (pipe[r][c].getNum() == 3) {
                            if (rotationAngle == 90) pipe[r][c].setNum(4);
                            else pipe[r][c].setNum(6);
                        } else if (pipe[r][c].getNum() == 4) {
                            if (rotationAngle == 90) pipe[r][c].setNum(5);
                            else pipe[r][c].setNum(3);
                        } else if (pipe[r][c].getNum() == 5) {
                            if (rotationAngle == 90) pipe[r][c].setNum(6);
                            else pipe[r][c].setNum(4);
                        } else if (pipe[r][c].getNum() == 6) {
                            if (rotationAngle == 90) pipe[r][c].setNum(3);
                            else pipe[r][c].setNum(5);
                        }
                    });
                    UndoButton.setOnAction(event -> {
                        if(moveHistory.size() > 2){
                            moveHistory.remove(0);
                        }
                        undoMove(pipe, grid, stage, moveLabelArr);
                    });
                    grid.add(pipeView, col, row);
                }

            }
        }
    }

    private void undoMove(Pipe[][] pipe, GridPane grid, Stage stage, Label[] moveLabelArr) {
        if (!moveHistory.isEmpty() && moveHistory.size() <= 2) {
            int[] lastMove = moveHistory.pop();
            int lastRotation = rotationHistory.pop();

            int row = lastMove[0];
            int col = lastMove[1];
            int previousNum = lastMove[2];

            // return to the previous version
            pipe[row][col].setNum(previousNum);

            // update pipe pics
            grid.getChildren().removeIf(node ->
                    GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row
                            && node instanceof ImageView
            );

            ImageView pipeView = new ImageView(pipe[row][col].getImage());
            pipeView.setFitHeight(CELL_SIZE);
            pipeView.setFitWidth(CELL_SIZE);

            // if the pipes are not STATIC keep the rotation ability
            if (!(row == 0 && col == 0 || col == SIZE - 1 && row == SIZE - 1 || pipe[row][col].getState() == Pipe.pipeState.STATIC)) {
                pipeView.setStyle("-fx-cursor: hand;");
                final int r = row;
                final int c = col;
                pipeView.setOnMouseClicked(event -> {
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

                    // Make pipes more interactive
                    pipeView.setStyle("-fx-cursor: hand;");
                    pipeView.setOnMouseEntered(e -> pipeView.setStyle("-fx-effect: dropshadow(gaussian, #1598ec, 15, 0.7, 0, 0); -fx-cursor: hand;"));
                    pipeView.setOnMouseExited(e -> pipeView.setStyle("-fx-effect: null; -fx-cursor: hand;"));


                    // Determine rotation direction based on mouse button
                    int rotationAngle = 90;
                    if (event.getButton() == MouseButton.SECONDARY) {
                        rotationAngle = -90; // Rotate counter-clockwise for right click
                    }

                    // Create rotation animation
                    RotateTransition rt = new RotateTransition(Duration.millis(300), pipeView);
                    rt.setByAngle(rotationAngle); // Rotate by 90 degrees
                    rt.setCycleCount(1);

                    rt.setOnFinished(e -> {
                        pipeView.setDisable(false);
                    });


                    rt.play(); // Start the animation

                    // Update the pipe array
                    if (pipe[r][c].getNum() == 1) {
                        pipe[r][c].setNum(2);
                    } else if (pipe[r][c].getNum() == 2) {
                        pipe[r][c].setNum(1);
                    } else if (pipe[r][c].getNum() == 3) {
                        if (rotationAngle == 90) pipe[r][c].setNum(4);
                        else pipe[r][c].setNum(6);
                    } else if (pipe[r][c].getNum() == 4) {
                        if (rotationAngle == 90) pipe[r][c].setNum(5);
                        else pipe[r][c].setNum(3);
                    } else if (pipe[r][c].getNum() == 5) {
                        if (rotationAngle == 90) pipe[r][c].setNum(6);
                        else pipe[r][c].setNum(4);
                    } else if (pipe[r][c].getNum() == 6) {
                        if (rotationAngle == 90) pipe[r][c].setNum(3);
                        else pipe[r][c].setNum(5);
                    }
                });
            }
            grid.add(pipeView, col, row);

            moveArr[0]++; // add moves
            moveLabelArr[0].setText("Moves : " + (moveArr[0]));
        }
    }
}
