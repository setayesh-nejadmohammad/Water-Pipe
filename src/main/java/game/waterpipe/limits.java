package game.waterpipe;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class limits {

    public void outOfMoves() {
        Label label = new Label("YOU ARE OUT OF MOVES!");
        label.getStyleClass().add("text");
        label.setAlignment(Pos.CENTER);
        Stage stage = new Stage();
        StackPane root = new StackPane(label);
        Scene scene = new Scene(root, 500, 200);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
}
