package game.view;

import java.util.List;

import game.Engine;
import game.gameobject.GameObject;

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
     * @param tutorial   the string to display.
     * @param controller the controller to resume.
     */
    void showMessage(String tutorial, Engine controller);

    /**
     * Checks if the view is still active.
     * 
     * @return if the view is still active.
     */
    boolean isViewActive();
}
