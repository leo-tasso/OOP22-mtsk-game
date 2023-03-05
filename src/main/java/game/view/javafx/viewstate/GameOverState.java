package game.view.javafx.viewstate;

import java.util.Optional;

import game.view.javafx.JavaFxViewCoordinator;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * State to display the game over screen.
 */
public class GameOverState implements ViewState {
    private static final int BUTTON_SPACING = 10;

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
            final Label scoreLabel = new Label("Score: " + points);
            final Button exitButton = new Button("Exit");
            exitButton.setOnAction(e -> Platform.exit());
            final Button playAgainButton = new Button("Play Again");
            playAgainButton.setOnAction(e -> {
                Platform.runLater(() -> {
                    new GameStateImpl(view).display(view, stage);
                });
            });

            // Create a horizontal box to hold the buttons
            final HBox buttonBox = new HBox(BUTTON_SPACING, exitButton, playAgainButton);
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
            goScene = new Scene(root);

        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void display(final JavaFxViewCoordinator view, final Stage stage) {
        Platform.runLater(() -> {
            stage.setScene(goScene);
        });
    }

}
