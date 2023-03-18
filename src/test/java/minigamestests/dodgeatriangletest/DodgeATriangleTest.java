package minigamestests.dodgeatriangletest;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.function.Consumer;

import org.junit.jupiter.api.Test;

import api.Point2D;
import game.controlling.Input;
import game.controlling.KeyboardInput;
import game.engine.minigame.DodgeATriangle;
import game.engine.minigame.Minigame;

/**
 * Unit test for the minigame DodgeATriangle.
 */
class DodgeATriangleTest {

    private static final long ELAPSED_TIME = 10L;
    private static final int CYCLES = 400;
    private static final int LIMIT_LOW = 170;
    private static final int LIMIT_HIGH = 730;
    private static final int MOVES = 7;

    /**
     * Elapsed Time must be small enough to be able to capture the 
     * moment in which the collision occurs (if it were too large, 
     * the triangle would risk "teleporting" from before to after 
     * the Dodger) while CYCLES must be large enough to be able to 
     * arrive at the moment in which the triangle hits the Dodger.
     */
    @Test
    void hitboxCheck() {
        final Minigame m = new DodgeATriangle();
        final Point2D center = m.getObjects().get(0).getCoor();

        while (m.getObjects().size() == 2 // The last elem is an istance of Slots
            || center.getY() != m.getObjects().get(1).getCoor().getY()) {
            m.compute(ELAPSED_TIME);
        }
        for (int i = 0; i < CYCLES; i++) {
            m.compute(ELAPSED_TIME);
            m.isGameOver();
        }
        assertTrue(m.isGameOver());
    }
}
