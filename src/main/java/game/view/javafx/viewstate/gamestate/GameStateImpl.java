package game.view.javafx.viewstate.gamestate;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import api.Point2D;
import game.controlling.Input;
import game.controlling.KeyboardInput;
import game.engine.gameobject.GameObject;
import game.view.Drawings;
import game.view.WamImagesCache;
import game.view.javafx.JavaFxDrawings;
import game.view.javafx.JavaFxViewCoordinator;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Class to represent the state of the gameplay.
 * 
 * Each minigame has a canvas, each canvas is contained into a stackpane
 * container to keep it aligned to the center (both vertically and
 * horizontally),
 * and each container is organized into a gridpane, its sizes are proportional
 * to the active minigames number.
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
    private final GridPane gp;
    private final WamImagesCache wamCache;

    /**
     * Constructor to initialize the state.
     * 
     * @param jView the main {@link JavaFxViewCoordinator}.
     * @param scene the {@link Scene} where this state will be applied.
     * @throws FileNotFoundException if the image files are not found.
     */
    public GameStateImpl(final JavaFxViewCoordinator jView, final Scene scene) {
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
        final StackPane container = new StackPane(canvas);
        gp.add(container, 0, 0);
        setGridNumbers(gp, 1, 1);
        scene.widthProperty().addListener((observable, oldValue, newValue) -> {
            minigameCanvases.forEach(c -> c.setWidth(boxWidth(scene)));
        });

        scene.heightProperty().addListener((observable, oldValue,
                newValue) -> {
            minigameCanvases.forEach(c -> c.setHeight(boxHeight(scene)));
        });
        new InputButtonsImpl().attach(scene, input);
        this.wamCache = new WamImagesCache();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void display(final JavaFxViewCoordinator jView, final Stage stage) {
        new Thread(() -> jView.gameStarter()).start();
        stage.getScene().setRoot(gp);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void refresh(final List<List<GameObject>> objectsList, final Scene scene, final int heightCoefficent) {
        Platform.runLater(() -> {
            while (objectsList.size() > minigameCanvases.size()) {
                final Canvas c = new Canvas();
                input.reset();
                minigameCanvases.add(c);
                final StackPane container = new StackPane(c);
                gp.add(container,
                        (minigameCanvases.size() - 1) % 2, (minigameCanvases.size() - 1) / 2);
                setGridNumbers(gp, gp.getRowCount(), gp.getColumnCount());
            }
            if (objectsList.size() < minigameCanvases.size()) {
                minigameCanvases.remove(minigameCanvases.size() - 1);
            }
            if (!minigameCanvases.isEmpty()) {
                minigameCanvases.forEach(c -> {
                    final GraphicsContext gc = c.getGraphicsContext2D();
                    c.setHeight(boxHeight(scene));
                    c.setWidth(boxWidth(scene));

                    gc.clearRect(0, 0, c.getWidth(), c.getHeight());
                    gc.setStroke(Color.BLACK);
                    gc.strokeRect(
                            /*
                             * coordinates of the upper left corner of rectangle: the
                             * last addendum is necessary to enter the right play field
                             */
                            getStartingPoint(scene).getX(),
                            getStartingPoint(scene).getY(),
                            boxWidth(scene),
                            boxHeight(scene));
                    gc.setFill(BACKGROUND_COLORS.size() > minigameCanvases.indexOf(c)
                            ? BACKGROUND_COLORS.get(minigameCanvases.indexOf(c))
                            : BACKGROUND_COLORS.get(BACKGROUND_COLORS.size() - 1));
                    gc.fillRect(
                            getStartingPoint(scene).getX(),
                            getStartingPoint(scene).getY(),
                            boxWidth(scene),
                            boxHeight(scene));
                    Drawings d;
                    d = new JavaFxDrawings(c, this.getStartingPoint(scene), this.boxHeight(scene),
                                heightCoefficent, this.wamCache);
                    objectsList.get(minigameCanvases.indexOf(c)).forEach(o -> o.updateAspect(d));
                });
            }
        });

    }

    private void setGridNumbers(final GridPane gp, final int rows, final int cols) {
        gp.getColumnConstraints().clear();
        gp.getRowConstraints().clear();
        final ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setHgrow(Priority.ALWAYS);
        columnConstraints.setPercentWidth(100.0 / cols);
        for (int i = 0; i < cols; i++) {
            gp.getColumnConstraints().add(columnConstraints);
        }

        final RowConstraints rowConstraints = new RowConstraints();
        rowConstraints.setVgrow(Priority.ALWAYS);
        rowConstraints.setPercentHeight(100.0 / rows);
        for (int i = 0; i < rows; i++) {
            gp.getRowConstraints().add(rowConstraints);
        }
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
     * @param scene the scene from which extract the size parameter.
     * @return the starting point of the game area.
     */
    private Point2D getStartingPoint(final Scene scene) {
        if (isLarger(scene)) {
            return new Point2D(
                    (minigameCanvases.get(0).getWidth() - minigameCanvases.get(0).getHeight() * ASPECT_RATIO) / 2, 0);
        }
        return new Point2D(0,
                (minigameCanvases.get(0).getHeight() - minigameCanvases.get(0).getWidth() / ASPECT_RATIO) / 2);
    }

    /**
     * Method to get the width of the canvas.
     * 
     * @param scene the scene from which extract the size parameter.
     * @return the width of the canvas
     */
    private double boxWidth(final Scene scene) {
        if (isLarger(scene)) {
            return scene.getHeight() / gp.getRowCount() * ASPECT_RATIO;
        }
        return scene.getWidth() / gp.getColumnCount();
    }

    /**
     * Method to get the height of the canvas.
     * 
     * @param scene the scene from which extract the size parameter.
     * @return the height of the canvas
     */
    private double boxHeight(final Scene scene) {
        if (isLarger(scene)) {
            return scene.getHeight() / gp.getRowCount();
        }
        return scene.getWidth() / gp.getColumnCount() / ASPECT_RATIO;
    }

    /**
     * Method to determine if the frame is larger than taller.
     *
     * @param scene the scene from which extract the size parameter.
     * @return whether the frame is larger
     */
    private boolean isLarger(final Scene scene) {
        return scene.getHeight() / gp.getRowCount() / ASPECT_HEIGHT < scene.getWidth() / gp.getColumnCount()
                / ASPECT_WIDTH;
    }

}
