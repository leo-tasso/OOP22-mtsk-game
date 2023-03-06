package game.engine;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;

import game.Controller;
import game.controlling.Input;
import game.engine.minigame.CatchTheSquare;
import game.engine.minigame.Minigame;
import game.engine.minigame.TestMinigame;

/**
 * Main game engine responsible of controlling the game.
 */
public class Engine {
    private static final List<Class<? extends Minigame>> MINIGAME_SEQUENCE = List.of(CatchTheSquare.class,
            CatchTheSquare.class, TestMinigame.class);
    private static final long TIME_TO_NEXT_MINIGAME = 5_000L;
    private static final long PERIOD = 5;
    private final List<Minigame> minigameList = new LinkedList<>();
    private boolean paused;
    private int addedMinigame;
    private final Controller controller;

    /**
     * Constructor to launch the engine from the view.
     * 
     * @param controller the launching controller.
     */
    public Engine(final Controller controller) {
        this.controller = controller;
    }

    /**
     * The loop performing each frame update according to the game loop pattern.
     */
    public void mainLoop() {
        Long lastAddedTime = 0L;
        addMinigame();
        long previousFrame = System.currentTimeMillis();
        Long points = 0L;
        while (!minigameList.stream().anyMatch(Minigame::isGameOver)) {
            final long currentFrame = System.currentTimeMillis();
            final long elapsed = currentFrame - previousFrame;
            if (points - lastAddedTime > TIME_TO_NEXT_MINIGAME
                    && minigameList.size() < MINIGAME_SEQUENCE.size()) {
                addMinigame();
                lastAddedTime = points;
            }

            if (!isPaused()) {
                points += elapsed;
                processInput(elapsed);
                updateGame(elapsed);
            }
            render();
            waitForNextFrame(currentFrame);
            previousFrame = currentFrame;
            // TODO FPS for debug-> System.out.println(1/(double)elapsed*1000);
        }
        controller.gameOver(points);
    }

    private void addMinigame() {
        setPaused(true);
        Minigame newMinigame;
        try {
            newMinigame = MINIGAME_SEQUENCE.get(addedMinigame++).getDeclaredConstructor().newInstance();
            controller.showMessage(newMinigame.getTutorial(), this);
            minigameList.add(newMinigame);
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            return;
        }

    }

    private void processInput(final long elapsedTime) {
        final Input input = controller.getInput();
        minigameList.stream().flatMap(m -> m.getGameObjects().stream()).forEach(o -> o.updateinput(input, elapsedTime));
    }

    /**
     * A getter for the list of minigames.
     * 
     * @return the list of minigames.
     */
    public List<Minigame> getMinigameList() {
        return new LinkedList<>(minigameList);
    }

    /**
     * Renders.
     */
    private void render() {
        controller.render(this.getMinigameList().stream().map(g -> g.getGameObjects()).toList());
    }

    /**
     * Waits for next frame.
     * 
     * @param currentFrame the current frame.
     */
    private void waitForNextFrame(final long currentFrame) {
        final long delta = System.currentTimeMillis() - currentFrame;
        if (delta < PERIOD) {
            try {
                Thread.sleep(PERIOD - delta);
            } catch (IllegalArgumentException | InterruptedException ex) {
                return;
            }
        }
    }

    /**
     * Updates the minigames.
     * 
     * @param elapsed the elapsed time.
     */
    private void updateGame(final long elapsed) {
        minigameList.forEach(m -> m.compute(elapsed));
    }

    /**
     * Method to set if the mainLoop is paused.
     * 
     * @param paused to set if is paused
     */
    public void setPaused(final boolean paused) {
        this.paused = paused;
    }

    /**
     * Method to get if the mainLoop is paused.
     * 
     * @return true if the mainLoop is paused
     */
    public boolean isPaused() {
        return this.paused;
    }

}
