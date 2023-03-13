package game;
/**
 * Interface to model the Controller of the Game.
 */
public interface Controller {

    /**
     * The game loop performing each frame update according to the game loop
     * pattern.
     */
    void startGame();

    /**
     * Method to set if the game loop is paused.
     * 
     * @param paused to set if is paused
     */
    void setPaused(boolean paused);

    /**
     * Method to get if the game loop is paused.
     * 
     * @return true if the game loop is paused
     */
    boolean isPaused();

    /**
     * Method to save the statistics.
     * 
     * @param scores the statistics to save.
     */
    void saveStats(LeaderBoard scores);

    /**
     * Method to save the statistics.
     * 
     * @return the statistics to retrieved.
     */
    LeaderBoard getStats();

}
