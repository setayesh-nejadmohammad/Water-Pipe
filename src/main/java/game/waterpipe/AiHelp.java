package game.waterpipe;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class AiHelp {
    int CELL_SIZE = HelloApplication.CELL_SIZE;
    int SIZE = 7;
    private Stage stage;
    public AiHelp(Stage stage) {
        this.stage = stage;
    }

    public void findWay(Pipe[][] pipe, GridPane grid) {
        System.out.println("AI Help Find Way is on");
        boolean result = true;

        // The start position
        int x = 1;
        int y = 0;

        // ======================= The gide rectangle ======================
        Rectangle rect = new Rectangle(CELL_SIZE, CELL_SIZE);
        rect.setFill(null);
        rect.setStroke(Color.WHITE);
        rect.setStrokeWidth(1);

        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(10.0);
        dropShadow.setOffsetX(1.0);
        dropShadow.setOffsetY(1.0);
        dropShadow.setSpread(0.8);
        dropShadow.setColor(Color.WHITE);
        rect.setEffect(dropShadow);
        // ==================================================================


        // ======================= Remove all rects in grid ================
        List<Node> nodesToRemove = new ArrayList<>();
        // find all Rectangles in grid
        for (Node node : grid.getChildren()) {
            if (node instanceof Rectangle) {
                nodesToRemove.add(node);
            }
        }
        grid.getChildren().removeAll(nodesToRemove);
        // =================================================================

        // reDO the grid every time that findWay is called
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                Rectangle rect2 = new Rectangle(CELL_SIZE, CELL_SIZE);
                rect2.setFill(null);
                rect2.setStroke(Color.BLACK);
                rect2.setStrokeWidth(1);
                grid.add(rect2, col, row);
            }
        }

    while(!(x == 6 && y == 6) && result && pipe[x][y] != null){
            if(pipe[x-1][y].getNum() == 8 && pipe[x][y].getNum() != 3 && pipe[x][y].getNum() != 1 && pipe[x][y].getNum() != 7){
                result = false;
                grid.add(rect, y, x);
            }
            else if(pipe[x][y].getNum() == 1){
                if(x+1<7 && pipe[x+1][y].getNum() != 1 && pipe[x+1][y].getNum() != 3 && pipe[x+1][y].getNum() != 7){
                    result = false;
                    grid.add(rect, y, x+1);
                }
                else x++;
            }
            else if(pipe[x][y].getNum() == 2){
                if(y+1<6 && pipe[x][y+1].getNum() != 2 && pipe[x][y+1].getNum() != 5 && pipe[x][y+1].getNum() != 7){
                    result = false;
                    grid.add(rect, y+1, x);
                }
                else y++;
            }
            else if(pipe[x][y].getNum() == 7){
                // ای لوله چهار طرفه ما را بیچاره کردی!
                if(x+1<7 && (pipe[x+1][y].getNum() == 1 || pipe[x+1][y].getNum() == 3 || pipe[x+1][y].getNum() == 7)
                && x-1>0 && (pipe[x-1][y].getNum() == 1 || pipe[x-1][y].getNum() == 5 || pipe[x-1][y].getNum() == 7)){
                    // It's using the vertical direction
                    x++;
                }
                else if(y+1<6 && (pipe[x][y+1].getNum() == 2 || pipe[x][y+1].getNum() == 5 || pipe[x][y+1].getNum() == 7)
                        && y-1>=0 && (pipe[x][y-1].getNum() == 2 || pipe[x][y-1].getNum() == 3|| pipe[x][y-1].getNum() == 7)){
                    // It's using the horizontal direction
                    y++;
                }
                else if((y==0 || x==6) && (pipe[x][y+1].getNum() == 2 || pipe[x][y+1].getNum() == 5 || pipe[x][y+1].getNum() == 7 || pipe[x][y+1].getNum() == 9)) y++;
                else if((x==1 || y==5) && (pipe[x+1][y].getNum() == 1 || pipe[x+1][y].getNum() == 3 || pipe[x+1][y].getNum() == 7)) x++;
                else {
                    result = false;
                    if(x+1<7 && pipe[x+1][y].getNum() != 0){
                        grid.add(rect, y, x+1);
                    }
                    else if(y+1<6 && pipe[x][y+1].getNum() != 0){
                        grid.add(rect, y+1, x);
                    }
                }
            }
            else if(pipe[x][y].getNum() == 3){
                if(y+1<6 && pipe[x][y+1].getNum() != 2 && pipe[x][y+1].getNum() != 5 && pipe[x][y+1].getNum() != 7){
                    result = false;
                    grid.add(rect, y+1, x);
                }
                else y++;
            }
            else if(pipe[x][y].getNum() == 5){
                if(x+1<7 && pipe[x+1][y].getNum() != 1 && pipe[x+1][y].getNum() != 3 && pipe[x+1][y].getNum() != 7){
                    result = false;
                    grid.add(rect, y, x+1);
                }
                else x++;
            }
        }
        if(result){
            YouAreAllSet();
        }
    }

    public void YouAreAllSet(){
        Image aiIcon = new Image(getClass().getResourceAsStream("pics/aiOff.png"));
        Stage aiDoneStage = new Stage();
        aiDoneStage.getIcons().add(aiIcon);
        aiDoneStage.setTitle("AI is talking TO YOU!");
        Label label = new Label("WHAT THE HELL YOU WANT ME FOR?\n           just click the play button");
        label.getStyleClass().add("text");
        label.setStyle("-fx-text-fill: blue;");
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.getChildren().add(label);
        Scene lastScene = new Scene(root, 600, 200);
        lastScene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        aiDoneStage.setScene(lastScene);
        aiDoneStage.show();
    }
}
