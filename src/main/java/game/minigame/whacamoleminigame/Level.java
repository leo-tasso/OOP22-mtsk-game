package game.minigame.whacamoleminigame;

import api.TimeInterval;
import api.Vector2D;

/**
 * Interface that models a whac-a-mole game level
 */
public interface Level {
    
    /**
     * The higher the level goes, the more difficult the game 
     * will be, and therefore the maximum number of moles and
     * /or bombs displayed at the same time will increase.
     * 
     * @return the new maximum amount of gameObject shown simultaneously
     */
    int getMaxObjsSimultaneouslyOut();

    /**
     * Another factor that determines the difficulty of a level
     * is the speed with which moles and bombs go up and down the 
     * holes: the faster they go, the more difficult it will be.
     * 
     * @return the speed of the GameObjects
     */
    Vector2D getObjSpeed();

    /**
     * Being that I extract GameObjects only when the list of them 
     * becomes empty, the longer GameObjects wait to be spawned
     * ==> the slower the list will empty
     * ==> moles will come out less frequently
     * ==> the easier the level will be
     * 
     * @return minimum and maximum waiting time
     */
    TimeInterval getSpawnWaitingTime();
}