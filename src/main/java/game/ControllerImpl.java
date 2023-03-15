package game;

import game.engine.Engine;
import game.engine.EngineImpl;
import game.view.View;
import game.view.javafx.JavaFxView;
import javafx.application.Application;

/**
 * Implementation of the controller.
 */
public class ControllerImpl implements Controller {
    private static final int FIELD_HEIGHT = 900; // hight in coordinate points that the view should display
    private final View view;
    private static final long TIME_TO_NEXT_MINIGAME = 5_000L;
    private static final long PERIOD = 5;
    private boolean paused;

    /**
     * Constructor.
     * 
     * @param view saves the caller view.
     */
    public ControllerImpl(final View view) {
        this.view = view;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void startGame() {
        final Engine e = new EngineImpl(FIELD_HEIGHT);
        Long lastAddedTime = 0L;
        view.showMessage(e.addMinigame());
        this.setPaused(true);
        long previousFrame = System.currentTimeMillis();
        Long points = 0L;
        while (!e.isGameOver()) {
            final long currentFrame = System.currentTimeMillis();
            final long elapsed = currentFrame - previousFrame;
            if (points - lastAddedTime > TIME_TO_NEXT_MINIGAME
                    && e.activeMinigames() < e.getMinigameSequence().size()) {
                view.showMessage(e.addMinigame());
                this.setPaused(true);
                lastAddedTime = points;
            }

            if (!isPaused()) {
                points += elapsed;
                e.processInput(elapsed, view.getInput());
                e.updateGame(elapsed);
            }
            view.render(e.getMinigameObjects());
            waitForNextFrame(currentFrame);
            previousFrame = currentFrame;
            // TODO FPS for debug-> System.out.println(1/(double)elapsed*1000);
        }
        view.renderGameOver(points);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPaused(final boolean paused) {
        this.paused = paused;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isPaused() {
        return this.paused;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveStats(final LeaderBoard scores) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveStats'");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LeaderBoard getStats() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getStats'");
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
     * {@inheritDoc}
     */
    @Override
    public int getFieldHeight() {
        return FIELD_HEIGHT;
    }

    /**
     * Start point of the game, initializes the loop.
     * 
     * @param args not used.
     */
    public static void main(final String[] args) {
        Application.launch(JavaFxView.class, args);
        // new SwingView(); //to use swing instead of JavaFx
    }
}
