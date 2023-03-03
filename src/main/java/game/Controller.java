package game;

import java.util.List;

import game.controlling.Input;
import game.engine.Engine;
import game.engine.gameobject.GameObject;

/**
 * Controller of the game, it mainly manages the interactions between Engine and
 * View.
 */
public interface Controller {
    /**
     * Method to start the game.
     */
    void startGame();

    /**
     * Method will be called when the game stops.
     * 
     * @param score the obtained score.
     */
    void gameOver(long score);

    /**
     * Method to show a message in the view.
     * 
     * @param tutorial
     * @param engine
     */
    void showMessage(String tutorial, Engine engine);

    /**
     * Method to get the Input.
     * 
     * @return the Inputs
     */
    Input getInput();

    /**
     * Method to update the Object representation in the View.
     * 
     * @param list the list of object divided in list for each minigame.
     */
    void render(List<List<GameObject>> list);

    /**
     * Method to save the stats.
     * 
     * @param scores the stats to save.
     */
    void saveStats(LeaderBoard scores);

    /**
     * method to retrive the stats.
     * 
     * @return the stats retrived.
     */
    LeaderBoard getStats();

}
