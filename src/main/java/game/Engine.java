package game;

import java.util.List;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
/**
 * Unused variables.
 */
@SuppressFBWarnings

/**
 * Main game engine responsible of controlling the game.
 */
public class Engine {
    private View view; // NOPMD
    private List<Minigame> minigameList;

    /**
     * Start point of the game, initializes the loop.
     * 
     * @param args not used.
     */
    public static void main(final String[] args) {

    }

    /**
     * The loop performing each frame update according to the game loop pattern.
     */
    public void mainLoop() {
        long previousFrame = System.currentTimeMillis();
        while (!minigameList.stream().anyMatch(Minigame::isGameOver)) {
            final long currentFrame = System.currentTimeMillis();
            long elapsed = currentFrame - previousFrame; // NOPMD
            // processInput();
            // updateGame(elapsed);
            // render();
            // waitForNextFrame(currentFrame);
            previousFrame = currentFrame;
        }
    }
}
