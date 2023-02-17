package game.minigame.whacamoleminigame;

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
     * @return the total time spent outside the hole (in milliseconds)
     */
    long getMoleExitPeriod();
}