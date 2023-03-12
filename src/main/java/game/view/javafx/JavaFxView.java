package game.view.javafx;

import java.util.List;
import java.util.Optional;

import game.Controller;
import game.ControllerImpl;
import game.controlling.Input;
import game.controlling.KeyboardInput;
import game.engine.Engine;
import game.engine.gameobject.GameObject;
import game.view.javafx.viewstate.GameOverState;
import game.view.javafx.viewstate.ViewStateMenu;
import game.view.javafx.viewstate.gamestate.GameState;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Implementation of {@link View} using JavaFx.
 */
public final class JavaFxView extends Application implements JavaFxViewCoordinator {

    private static final int START_WINDOW_WIDTH = 800;
    private static final int START_WINDOW_HEIGHT = 480;
    private Stage stage;

    private boolean windowOpen;
    private final Controller controller = new ControllerImpl(this);

    private Optional<GameState> gameState = Optional.empty();

    /**
     * {@inheritDoc}
     */
    @Override
    public void start(final Stage stage) throws Exception {
        initStage(stage);
        new ViewStateMenu(this, stage).display(this, stage);
    }

    private void initStage(final Stage stage) {
        this.stage = stage;
        stage.getIcons().add(new Image(this.getClass().getResourceAsStream("/LayerIcon.png")));
        stage.setOnCloseRequest(event -> {
            Platform.exit();
        });
        stage.setMinWidth(START_WINDOW_WIDTH);
        stage.setMinHeight(START_WINDOW_HEIGHT);
        stage.setTitle("MTSK-Game");
        stage.setFullScreen(false);
        windowOpen = true;
        stage.setHeight(START_WINDOW_HEIGHT);
        stage.setWidth(START_WINDOW_WIDTH);
        final Scene scene = new Scene(new VBox(), stage.getWidth(), stage.getHeight());
        scene.setOnKeyPressed(e -> {
            if (e.getCode().equals(KeyCode.F)) {
                this.toggleFullScreen();
            }
        });
        stage.setScene(scene);
        stage.show();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render(final List<List<GameObject>> objectsList) {
        if (gameState.isPresent()) {
            gameState.get().refresh(objectsList, stage.getScene());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void renderGameOver(final Long points) {
        new GameOverState(this, stage, points).display(this, stage);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showMessage(final String tutorial, final Engine controller) {
        Platform.runLater(() -> {
            final Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Message");
            alert.setHeaderText(null);
            alert.setContentText(tutorial);

            alert.initOwner(stage);

            alert.showAndWait();
            controller.setPaused(false);
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isViewActive() {
        return windowOpen;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void stop() throws Exception {
        windowOpen = false;
    }

    @Override
    public Input getInput() {
        if (gameState.isPresent()) {
            return gameState.get().getInput();
        }
        return new KeyboardInput();
    }

    @Override
    public void setGameState(final Optional<GameState> gameState) {
        this.gameState = gameState;
    }

    @Override
    public Controller getController() {
        return controller;
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public boolean isFullScreen() {
        return stage.isFullScreen();
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public void toggleFullScreen() {
        stage.setFullScreen(!isFullScreen());
    }
}
