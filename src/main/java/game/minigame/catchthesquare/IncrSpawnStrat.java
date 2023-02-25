package game.minigame.catchthesquare;

/**
 * Spawn strategy that follows an exponetial curve till a maximum steepness and
 * then keeps that one.
 */
public class IncrSpawnStrat implements SpawnFreqStrat {

    private final double difficulty;
    private static final double FLATTEN_SPAWN_RATE = 0.7; // the maximum steepness of the curve.

    /**
     * Constructor allows to set the rate of increase of the spawn.
     * 
     * @param difficulty the rate of increase of the spawning objects. Suggested
     *                   values 1< x <1.3
     */
    public IncrSpawnStrat(final double difficulty) {
        this.difficulty = difficulty;

    }

    /**
     * method to get the number of bombs to spawn.
     */
    @Override
    public int bombNumber(final long totalElapsed) {
        // if exponetial exeeds max steepness keeps a constant one.
        if (Math.pow(difficulty, (double) totalElapsed / 1000) * Math.log(difficulty) > FLATTEN_SPAWN_RATE) {
            final double x = Math.log(FLATTEN_SPAWN_RATE / Math.log(difficulty)) / Math.log(difficulty); // the x
                                                                                                         // coordinate
                                                                                                         // corresponding
                                                                                                         // to the
                                                                                                         // target
                                                                                                         // steepness
            return (int) (((double) totalElapsed / 1000 - x) * Math.pow(difficulty, x) * Math.log(difficulty)
                    + Math.pow(difficulty, x)); // the rect crossing the point
        }
        return (int) (Math.pow(difficulty, (double) totalElapsed / 1000)); // follows exponetial curve

    }

}
