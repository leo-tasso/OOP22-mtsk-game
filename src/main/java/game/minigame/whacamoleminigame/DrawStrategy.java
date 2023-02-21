package game.minigame.whacamoleminigame;

import java.util.Set;
import game.gameobject.GameObject;

/**
 * Interface used as a strategy pattern, containing the 
 * interchangeable algorithm that creates moles/bombs in random 
 * number & positions (also assigning them an arbitrary appearance 
 * time) within the limits set by the current level of difficulty.
 */
public interface DrawStrategy {

    /**
     * Method for randomly drawing moles and/or bombs.
     * 
     * @param currentLevel from which we obtain the exit time (speed) of the 
     * GameObjects and the maximum number of them with overlapping exit times
     * @param upperBond The maximum instant in which to start the appearance 
     * of the GameObject
     * @param numHoles number of holes in the playing field
     * @return a Set containing the new GameObjects to add to the game
     */
    Set<GameObject> draw(Level currLevel, long upperBond, int numHoles);
}