package game;

import java.sql.Timestamp;
import java.util.Map;

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
     * @param timestamp the timestamp of the obtained score.
     * @param score     the relative score.
     */
    void saveStats(Timestamp timestamp, long score);

    /**
     * Method to save the statistics.
     * 
     * @return the statistics retrieved.
     */
    Map<Timestamp, Long> getStats();

    /**
     * @return the height in points of the game field that the view will display.
     */
    int getFieldHeight();

}
