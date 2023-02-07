package game;

import java.util.LinkedList;
import java.util.List;

import api.Input;
import game.controlling.KeyboardInput;
import game.minigame.Minigame;
import game.minigame.TestMinigame;
import game.view.SwingView;
import game.view.View;

/**
 * Main game engine responsible of controlling the game.
 */
public class Engine {
    private static final long TIME_TO_NEXT_MINIGAME = 4000L;
    private static final long PERIOD = 10;
    private final List<Minigame> minigameList = new LinkedList<>();
    private final Input input = new KeyboardInput();

    private final View view = new SwingView(this, input);

    /**
     * Start point of the game, initializes the loop.
     * 
     * @param args not used.
     */
    public static void main(final String[] args) {
        new Engine().mainLoop();
    }

    /**
     * The loop performing each frame update according to the game loop pattern.
     */
    public void mainLoop() {
        final Long startTime = System.currentTimeMillis();
        minigameList.add(new TestMinigame());
        long previousFrame = System.currentTimeMillis();
        while (!minigameList.stream().anyMatch(Minigame::isGameOver)) {
            final long currentFrame = System.currentTimeMillis();
            long elapsed = currentFrame - previousFrame; // NOPMD
            if (System.currentTimeMillis() - startTime > TIME_TO_NEXT_MINIGAME && minigameList.size() < 2) {
                minigameList.add(new TestMinigame());
            }

            processInput();
            updateGame(elapsed);
            render();
            waitForNextFrame(currentFrame);
            previousFrame = currentFrame;
        }
    }

    private void processInput() {
        minigameList.stream().flatMap(m -> m.getGameObjects().stream()).forEach(o -> o.updateinput(input));
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
        view.render();
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
}
