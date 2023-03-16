package game.engine.difficultystrats;

import java.util.function.Function;

/**
 * Spawn strategy that follows a step-like function (similar to a floor function)
 * until it reaches the maximum amount of steps.
 */
public class StepRateStrat implements Function<Long, Integer> {

    private int numSteps;
    private long denominator;

    public StepRateStrat(final int numSteps, final long denominator) {
        this.numSteps = numSteps;
        this.denominator = denominator;
    }

    @Override
    public Integer apply(Long arg0) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'apply'");
    }
    
}
