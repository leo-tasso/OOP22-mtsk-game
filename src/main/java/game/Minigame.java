package game;

/**
 * General interface of a minigame.
 */
public interface Minigame {

    /**
    * Checks whether or not the minigame is finished.
    * @return true if the player lost, false otherwise.
    */
    boolean isGameOver();

    /**
    * Computes the new minigame status.
    * @param elapsed milliseconds elapsed.
    */
    void compute(long elapsed);

}
