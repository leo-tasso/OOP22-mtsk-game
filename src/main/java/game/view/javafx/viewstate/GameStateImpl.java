package game.view.javafx.viewstate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import api.Point2D;
import game.controlling.Input;
import game.controlling.KeyboardInput;
import game.engine.gameobject.GameObject;
import game.view.Drawings;
import game.view.javafx.JavaFxDrawings;
import game.view.javafx.JavaFxViewCoordinator;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Class to represent the state of the gameplay.
 */
public class GameStateImpl implements GameState {
    private static final int ASPECT_WIDTH = 16;
    private static final int ASPECT_HEIGHT = 9;
    private static final double ASPECT_RATIO = ASPECT_WIDTH / (double) ASPECT_HEIGHT;

    private static final int START_WINDOW_WIDTH = 800;
    private static final int START_WINDOW_HEIGHT = 480;
    private static final List<Color> BACKGROUND_COLORS = List.of(Color.CYAN, Color.MAGENTA, Color.YELLOW, Color.WHITE);
    private final List<Canvas> minigameCanvases = new ArrayList<>();

    private final Input input;

    private final Scene gameScene;
    private final GridPane gp;

    /**
     * Constructor to initialize the state.
     * 
     * @param jView the main {@link JavaFxViewCoordinator}.
     */
    public GameStateImpl(final JavaFxViewCoordinator jView) {
        this.input = new KeyboardInput();
        jView.setGameState(Optional.of(this));
        final Canvas canvas = new Canvas(START_WINDOW_WIDTH, START_WINDOW_HEIGHT);
        gp = new GridPane();
        final BackgroundImage backgroundImage = new BackgroundImage(
                new Image(JavaFxViewCoordinator.class.getResourceAsStream("/Pattern.png")), BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        final Background background = new Background(backgroundImage);
        gp.setBackground(background);
        minigameCanvases.clear();
        minigameCanvases.add(canvas);
        gp.add(canvas, 0, 0);
        gameScene = new Scene(gp, START_WINDOW_WIDTH, START_WINDOW_HEIGHT);
        gameScene.widthProperty().addListener((observable, oldValue, newValue) -> {
            final double width = newValue.doubleValue();
            minigameCanvases.forEach(c -> c.setWidth(width / gp.getColumnCount()));
        });

        gameScene.heightProperty().addListener((observable, oldValue,
                newValue) -> {
            final double height = newValue.doubleValue();
            minigameCanvases.forEach(c -> c.setHeight(height / gp.getRowCount()));
        });

        gameScene.setOnKeyPressed(e -> {
            if (e.getCode().equals(KeyCode.W)) {
                input.setMoveUp(true);
            } else if (e.getCode().equals(KeyCode.A)) {
                input.setMoveLeft(true);
            } else if (e.getCode().equals(KeyCode.S)) {
                input.setMoveDown(true);
            } else if (e.getCode().equals(KeyCode.D)) {
                input.setMoveRight(true);
            } else if (e.getCode().equals(KeyCode.F)) {
                jView.toggleFullScreen();
            }

        });
        gameScene.setOnKeyReleased(e -> {
            if (e.getCode().equals(KeyCode.W)) {
                input.setMoveUp(false);
            } else if (e.getCode().equals(KeyCode.A)) {
                input.setMoveLeft(false);
            } else if (e.getCode().equals(KeyCode.S)) {
                input.setMoveDown(false);
            } else if (e.getCode().equals(KeyCode.D)) {
                input.setMoveRight(false);
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void display(final JavaFxViewCoordinator jView, final Stage stage) {
        new Thread(() -> jView.getController().startGame()).start();
        stage.setScene(gameScene);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void refresh(final List<List<GameObject>> objectsList) {
        Platform.runLater(() -> {
            while (objectsList.size() > minigameCanvases.size()) {
                final Canvas c = new Canvas();
                input.reset();
                minigameCanvases.add(c);
                gp.add(c,
                        (minigameCanvases.size() - 1) % 2, (minigameCanvases.size() - 1) / 2);
            }
            if (objectsList.size() < minigameCanvases.size()) {
                minigameCanvases.remove(minigameCanvases.size() - 1);
            }
            if (!minigameCanvases.isEmpty()) {
                minigameCanvases.forEach(c -> {
                    final GraphicsContext gc = c.getGraphicsContext2D();
                    c.setHeight(gameScene.getHeight() / gp.getRowCount());
                    c.setWidth(gameScene.getWidth() / gp.getColumnCount());

                    gc.clearRect(0, 0, c.getWidth(), c.getHeight());
                    gc.setStroke(Color.BLACK);
                    gc.strokeRect(
                            /*
                             * coordinates of the upper left corner of rectangle: the
                             * last addendum is necessary to enter the right play field
                             */
                            getStartingPoint().getX(), // TODO is it better to draw also outside the area?
                            getStartingPoint().getY(),
                            boxWidth(),
                            boxHeight());
                    gc.setFill(BACKGROUND_COLORS.size() > minigameCanvases.indexOf(c)
                            ? BACKGROUND_COLORS.get(minigameCanvases.indexOf(c))
                            : BACKGROUND_COLORS.get(BACKGROUND_COLORS.size() - 1));
                    gc.fillRect(
                            getStartingPoint().getX(),
                            getStartingPoint().getY(),
                            boxWidth(),
                            boxHeight());
                    final Drawings d = new JavaFxDrawings(c, this.getStartingPoint(), this.boxHeight());
                    objectsList.get(minigameCanvases.indexOf(c)).forEach(o -> o.updateAspect(d));
                });
            }
        });

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Input getInput() {
        return input.clone();
    }

    /**
     * Method to get the starting coordinates of the top left corner of the
     * rectangle of the game area.
     * 
     * @return the starting point of the game area.
     */
    private Point2D getStartingPoint() {
        if (isLarger()) {
            return new Point2D(
                    (minigameCanvases.get(0).getWidth() - minigameCanvases.get(0).getHeight() * ASPECT_RATIO) / 2, 0);
        }
        return new Point2D(0,
                (minigameCanvases.get(0).getHeight() - minigameCanvases.get(0).getWidth() / ASPECT_RATIO) / 2);
    }

    /**
     * Method to get the width of the canvas.
     *
     * @return the width of the canvas
     */
    private double boxWidth() {
        if (isLarger()) {
            return minigameCanvases.get(0).getHeight() * ASPECT_RATIO;
        }
        return minigameCanvases.get(0).getWidth();
    }

    /**
     * Method to get the height of the canvas.
     *
     * @return the height of the canvas
     */
    private double boxHeight() {
        if (isLarger()) {
            return minigameCanvases.get(0).getHeight();
        }
        return minigameCanvases.get(0).getWidth() / ASPECT_RATIO;
    }

    /**
     * Method to determine if the frame is larger than taller.
     *
     * @return whether the frame is larger
     */
    private boolean isLarger() {
        return minigameCanvases.get(0).getHeight() / ASPECT_HEIGHT < minigameCanvases.get(0).getWidth() / ASPECT_WIDTH;
    }

}
