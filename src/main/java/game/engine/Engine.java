package game.engine;

import java.util.List;

import game.controlling.Input;
import game.engine.gameobject.GameObject;
import game.engine.minigame.Minigame;

/**
 * Class to model an engine, the entity of the model that manages single
 * minigames.
 */
public interface Engine {
    /**
     * 
     * To start a new minigame.
     * 
     * @return the tutorial string of the minigame just added.
     */
    String addMinigame();

    /**
     * Method to let the minigames process a specific input.
     * 
     * @param elapsedTime the elapsed time since last frame in ms.
     * @param input       the {@link Input} class with the inputs to show the
     *                    minigames.
     */
    void processInput(long elapsedTime, Input input);

    /**
     * A getter for the list of objects for each minigame.
     * 
     * @return the list of objects for each minigame.
     */
    List<List<GameObject>> getMinigameObjects();

    /**
     * Updates the state of the minigames.
     * 
     * @param elapsed the elapsed time.
     */
    void updateGame(long elapsed);

    /**
     * Tells if any of the minigame is over.
     * 
     * @return if any of the minigame is over.
     */
    boolean isGameOver();

    /**
     * Tells the number of active minigames.
     * 
     * @return the number of active minigames.
     */
    int activeMinigames();

    /**
     * To get the list of the expected minigames sequence.
     * 
     * @return the list of classes of the expected minigames sequence.
     */
    List<Class<? extends Minigame>> getMinigameSequence();

    /**
     * Getter method for the width of each playing field.
     * 
     * @return the width.
     */
    int getRightLimit();

    /**
     * Getter method for the height of each playing field.
     * 
     * @return the height.
     */
    int getBottomLimit();
}
