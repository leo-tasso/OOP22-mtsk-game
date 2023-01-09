package game.view;

/**
 * Main view interface, shows the game.
 */
public interface View {
    /**
     * Renders.
     */
    void render();

    /**
     * Displays final score.
     */
    void renderGameOver();
}
