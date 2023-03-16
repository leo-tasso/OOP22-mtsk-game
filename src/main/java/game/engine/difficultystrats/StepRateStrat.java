package game.engine.difficultystrats;

import java.util.Optional;
import java.util.function.Function;

/**
 * Spawn strategy that follows a step-like function (similar to a floor function)
 * until it reaches the maximum value allowed.
 */
public class StepRateStrat implements Function<Long, Integer> {

    private final int numSteps;
    private final int stepValue;
    private final long stepLength;

    /**
     * Constructor for StepRateStrat.
     * 
     * @param numSteps the number of steps required to reach the maximum value
     * @param stepValue the difference between two adjacent steps
     * @param stepLength the distance to get to the next step
     */
    public StepRateStrat(final int numSteps, final int stepValue, final long stepLength) {
        this.numSteps = numSteps;
        this.stepValue = stepValue;
        this.stepLength = stepLength;
    }

    /**
     * Method to get the x coordinate the final obstacle has to reach
     * before spawning a new one.
     */
    @Override
    public Integer apply(final Long totalElapsed) {
        return Optional.of(totalElapsed / stepLength)
                .map(x -> (int) (x >= numSteps ? numSteps * stepValue : x.intValue() * stepValue))
                .get();
    }
}
