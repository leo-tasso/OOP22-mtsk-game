package game;

import java.util.List;

import game.controlling.Input;
import game.engine.Engine;
import game.engine.gameobject.GameObject;
import game.view.View;
import game.view.javafx.JavaFxView;
import javafx.application.Application;

/**
 * Implementation of the controller.
 */
public class ControllerImpl implements Controller {
    private final View view;

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
        new Engine(this).mainLoop();
    }

    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public void stopGame(final long score) {
        view.renderGameOver(score);
    }

    /**
     * Start point of the game, initializes the loop.
     * 
     * @param args not used.
     */
    public static void main(final String[] args) {
        Application.launch(JavaFxView.class, args);
        // new Engine().mainLoop();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showMessage(final String tutorial, final Engine engine) {
        view.showMessage(tutorial, engine);
    }
/**
 * {@inheritDoc}
 */
    @Override
    public Input getInput() {
        return view.getInput();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render(final List<List<GameObject>> list) {
        view.render(list);
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

}
