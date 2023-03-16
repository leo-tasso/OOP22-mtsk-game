package minigamestests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

import game.engine.difficultystrats.StepRateStrat;

/**
 * Class to check the {@link StepRateStrat} class mathematical properties.
 */
class StepRateStratTest {

    private static final int NUM_STEPS = 10;
    private static final int STEP_VALUE = 1;
    private static final long STEP_LENGTH = 5L;
    private final StepRateStrat s = new StepRateStrat(NUM_STEPS, STEP_VALUE, STEP_LENGTH);

    @Test
    void intervalCheck() {
        assertEquals(s.apply(STEP_LENGTH) - s.apply(0L), STEP_VALUE);
        assertEquals(s.apply(STEP_LENGTH * 2) - s.apply(0L), STEP_VALUE * 2);
        assertEquals(s.apply(STEP_LENGTH * 3) - s.apply(STEP_LENGTH), STEP_VALUE * 2);
    }

    @Test
    void maxValueCheck() {
        assertNotEquals(s.apply(STEP_LENGTH * (NUM_STEPS - 1)), s.apply(STEP_LENGTH * NUM_STEPS));
        assertEquals(s.apply(STEP_LENGTH * (NUM_STEPS + 1)), s.apply(STEP_LENGTH * NUM_STEPS));

        for (final long multiplier : Stream.iterate(0L, x -> x + 1).limit(NUM_STEPS * 2).toList()) {
            assertTrue(s.apply(STEP_LENGTH * multiplier) <= s.apply(STEP_LENGTH * NUM_STEPS));
        }
    }
}
