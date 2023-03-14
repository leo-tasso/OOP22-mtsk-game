package minigamestests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import game.engine.difficultystrats.IncrRateStrat;

/**
 * Class to check the {@link IncrRateStratTest} class mathematical properties.
 */
class IncrRateStratTest {
    private static final double MAX_BOMB_RATE = 0.7;
    private static final double BOMB_SPAWN_DIFF = 1.05;
    private static final long START1 = 1000;
    private static final long START2 = 30_000;
    private static final long RANGE = 20_000;
    private static final long FLAT_START1 = 50_000;
    private static final long FLAT_START2 = 90_000;
    private final IncrRateStrat s = new IncrRateStrat(BOMB_SPAWN_DIFF, MAX_BOMB_RATE);

    @Test
    void isIncreasing() {

        assertTrue(s.apply(START1 + RANGE) - s.apply(START1) < s.apply(START2 + RANGE) - s.apply(START2));
    }

    @Test
    void isFlattening() {

        assertEquals(s.apply(FLAT_START1 + RANGE) - s.apply(FLAT_START1), s.apply(FLAT_START2 + RANGE)
                - s.apply(FLAT_START2));
    }

}
