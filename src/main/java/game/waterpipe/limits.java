package game.waterpipe;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class limits {
    public int timeSeconds = 30;
    private Label timerLabel = new Label();
    Timeline timeline = new Timeline();

    public int getTimeSeconds() {
        return timeSeconds;
    }
    public void setTimeSeconds(int timeSeconds) {
        this.timeSeconds = timeSeconds;
    }
    public void setTimerLabel(Label timerLabel) {
        this.timerLabel = timerLabel;
    }

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

    public void TimeLimit(Stage stage, Pane pane) {
        timerLabel.setStyle("-fx-font-size: 24px; -fx-text-fill: red;");
        timerLabel.setText("Time: " + timeSeconds);
        pane.getChildren().add(timerLabel);
        timerLabel.setLayoutX(400);
        timerLabel.setLayoutY(40);
        timerLabel.getStyleClass().add("text");

        timeline.setCycleCount(Timeline.INDEFINITE);

        KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), event -> {
            timeSeconds--;
            timerLabel.setText("Time: " + timeSeconds);

            if (timeSeconds <= 0) {
                timeline.stop();
                TimesOut();
                stage.close();
                System.out.println("GAME OVER LOSER!");
            }
        });

        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    }

    public void resetTimer() {
        timeline.stop();
        timeSeconds = 33;  // 3 Extera seconds because game has 3 sec delay for restart :/ i don't know why
        updateTimerLabel();
        timeline.play();
    }
    public void TimeStop(){
        timeline.stop();
    }

    private void updateTimerLabel() {
        timerLabel.setText("Time: " + timeSeconds);
    }

    private void TimesOut(){
        Stage timeOutStage = new Stage();
        Label label = new Label("YOUR TIME'S UP LOSER!");
        label.getStyleClass().add("text");
        label.setStyle("-fx-text-fill: red;");
        label.setAlignment(Pos.CENTER);
        VBox vbox = new VBox(label);
        vbox.setAlignment(Pos.CENTER);
        Scene timeScene = new Scene(vbox, 500, 200);
        timeScene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        timeOutStage.setScene(timeScene);
        timeOutStage.show();
    }

}
