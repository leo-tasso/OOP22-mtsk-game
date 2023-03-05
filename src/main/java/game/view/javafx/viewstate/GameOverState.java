package game.view.javafx.viewstate;

import java.util.Optional;

import game.view.javafx.JavaFxViewCoordinator;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;
import javafx.stage.Stage;

/**
 * State to display the game over screen.
 */
public class GameOverState implements ViewState {
    private static final int LABEL_SIZE = 55;

    private static final int BUTTON_SPACING = 20;

    private Scene goScene;

    /**
     * Constructor to initialize the State.
     * 
     * @param view   the {@link JavaFxViewCoordinator} coordinator.
     * @param stage  the {@link stage} that will be used to display.
     * @param points the scored points during the gameplay.
     */
    public GameOverState(final JavaFxViewCoordinator view, final Stage stage, final Long points) {
        Platform.runLater(() -> {
            view.setGameState(Optional.empty());
            final ButtonFactory bf = new ButtonFactory(BUTTON_SPACING);
            final Text scoreLabel = new Text("Score: " + points);
            scoreLabel.setFont(Font.font("Arial", LABEL_SIZE));
            scoreLabel.setFill(Color.WHITE);
            scoreLabel.setBoundsType(TextBoundsType.VISUAL);
            scoreLabel.setStrokeType(StrokeType.OUTSIDE);
            scoreLabel.setStrokeWidth(2);
            scoreLabel.setStrokeLineJoin(StrokeLineJoin.ROUND);
            scoreLabel.setStroke(Color.BLACK);
            final Button exitButton = bf.create(stage, "Exit", e -> Platform.exit());
            final Button playAgainButton = bf.create(stage, "Play Again", e -> {
                Platform.runLater(() -> {
                    new GameStateImpl(view).display(view, stage);
                });
            });
            final Button statsButton = bf.create(stage, "Stats", null); // TODO to implement

            // Create a horizontal box to hold the buttons
            final HBox buttonBox = new HBox(BUTTON_SPACING, exitButton, statsButton, playAgainButton);
            buttonBox.setAlignment(Pos.CENTER);

            // Create a vertical box to hold the label and buttons
            final VBox root = new VBox(BUTTON_SPACING, scoreLabel, buttonBox);
            root.setAlignment(Pos.CENTER);
            final BackgroundImage backgroundImage = new BackgroundImage(
                    new Image(JavaFxViewCoordinator.class.getResourceAsStream("/Pattern.png")), BackgroundRepeat.REPEAT,
                    BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
            final Background background = new Background(backgroundImage);
            root.setBackground(background);
            // Create a scene with the root container
            goScene = new Scene(root, stage.getWidth(), stage.getHeight());

        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void display(final JavaFxViewCoordinator view, final Stage stage) {
        Platform.runLater(() -> {
            stage.setFullScreenExitHint("");
            final Boolean isFullScreen = stage.isFullScreen();
            stage.setScene(goScene);
            stage.setFullScreen(isFullScreen);
        });
    }

}
