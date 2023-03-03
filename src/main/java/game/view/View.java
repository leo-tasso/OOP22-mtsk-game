package game.view;

import java.util.List;

import game.controlling.Input;
import game.engine.Engine;
import game.engine.gameobject.GameObject;

/**
 * Main view interface, shows the game.
 */
public interface View {
    /**
     * The method to render the view.
     * 
     * @param objectsList a list of list of gameObjects for each minigame.
     */
    void render(List<List<GameObject>> objectsList);

    /**
     * Displays final score.
     * 
     * @param points the scored points to display.
     */
    void renderGameOver(Long points);

    /**
     * Method to show a String message on a popup window.
     * 
     * @param tutorial the string to display.
     * @param engine   the engine to resume.
     */
    void showMessage(String tutorial, Engine engine);

    /**
     * Checks if the view is still active.
     * 
     * @return if the view is still active.
     */
    boolean isViewActive();

    /**
     * Method to get the input.
     * 
     * @return the input obtained.
     */
    Input getInput();
}
