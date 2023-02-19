package game.minigame.catchthesquare;

/**
 * Interface to define the strategy of spawing time for the bombs.
 */
public interface SpawnFreqStrat {
    /**
     * Method to understand how many bombs are expected to be spawn at a specific
     * time.
     * 
     * @param totalElapsed the elapsed time from the beginnign of the minigame.
     * @return the number of bomb that shall be spawned so far.
     */
    int bombNumber(long totalElapsed);
}
