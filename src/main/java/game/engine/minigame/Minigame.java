package game.engine.minigame;

import java.util.List;

import game.engine.gameobject.GameObject;

/**
 * General interface of a minigame.
 * 
 * !!EACH MINIGAME SHALL HAVE A CONSTRUCTOR WITH ONE INT ARGUMENT (the hight that will be displayed)!!
 */
public interface Minigame {

    /**
     * Checks whether or not the minigame is finished.
     * 
     * @return true if the player lost, false otherwise.
     */
    boolean isGameOver();

    /**
     * Computes the new minigame status.
     * 
     * @param elapsed milliseconds elapsed.
     */
    void compute(long elapsed);

    /**
     * Method to get the list of game objects.
     * 
     * @return the list of game objects.
     */
    List<GameObject> getObjects();

    /**
     * Method to get a help string on how to play the minigame.
     * 
     * @return the string with the instructions.
     */
    String getTutorial();

}
