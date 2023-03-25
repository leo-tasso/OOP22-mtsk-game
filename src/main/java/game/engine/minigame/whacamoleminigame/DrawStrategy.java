package game.engine.minigame.whacamoleminigame;

import java.util.Set;
import game.engine.gameobject.GameObject;

/**
 * Interface used as a strategy pattern, containing the 
 * interchangeable algorithm that creates moles/bombs in random 
 * number and positions (also assigning them an arbitrary appearance 
 * time) within the limits set by the current level of difficulty.
 */
public interface DrawStrategy {

    /**
     * Method for randomly drawing moles and/or bombs.
     * 
     * @param currentLevel from which we obtain the speed of the 
     * GameObjects, the maximum number of them that can exit simultaneously
     * and the amount of time an item should wait before spawning.
     * @param currentTime "local" time of the game, needed to assign meaningful 
     * values to the objects (such as the time of appearance)
     * @return a Set containing the new GameObjects to add to the game
     */
    Set<GameObject> draw(Level currentLevel, long currentTime);
}
