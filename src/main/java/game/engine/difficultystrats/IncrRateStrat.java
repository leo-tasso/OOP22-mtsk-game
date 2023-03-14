package game.engine.difficultystrats;

import java.util.function.Function;

/**
 * Spawn strategy that follows an exponetial curve till a maximum steepness and
 * then keeps that one.
 */
public class IncrRateStrat implements Function<Long, Long> {

    private final double difficulty;
    private final double flattenSpawnRate; // the maximum steepness of the curve.

    /**
     * Constructor allows to set the rate of increase of the spawn.
     * 
     * @param difficulty       the rate of increase of the spawning objects.
     *                         Suggested
     *                         values 1< x <1.3
     * @param flattenSpawnRate the maximum rate, once reached it will be kept
     */
    public IncrRateStrat(final double difficulty, final double flattenSpawnRate) {
        this.difficulty = difficulty;
        this.flattenSpawnRate = flattenSpawnRate;

    }

    /**
     * method to get the number of bombs to spawn.
     */
    @Override
    public Long apply(final Long totalElapsed) {
        // if exponetial exeeds max steepness keeps a constant one.
        if (Math.pow(difficulty, (double) totalElapsed / 1000) * Math.log(difficulty) > flattenSpawnRate) {
            final double x = Math.log(flattenSpawnRate / Math.log(difficulty)) / Math.log(difficulty); // the x
                                                                                                       // coordinate
                                                                                                       // corresponding
                                                                                                       // to the
                                                                                                       // target
                                                                                                       // steepness
            return (long) (((double) totalElapsed / 1000 - x) * Math.pow(difficulty, x) * Math.log(difficulty)
                    + Math.pow(difficulty, x)); // the rect crossing the point
        }
        return (long) (Math.pow(difficulty, (double) totalElapsed / 1000)); // follows exponetial curve

    }

}
