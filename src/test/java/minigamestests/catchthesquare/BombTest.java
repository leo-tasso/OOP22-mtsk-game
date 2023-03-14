package minigamestests.catchthesquare;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import api.ColorRGB;
import api.Point2D;
import game.engine.gameobject.catchthesqareobjects.Bomb;

/**
 * Test to check the {@link Bomb} {@link GameObject} properties.
 */
class BombTest {
    private static final int SIDE = 20;
    private static final int ITERATIONS = 100;
    private static final long DELTA = 15;

    @Test
    void timeDec() {
        final Bomb b = new Bomb(Point2D.origin(), SIDE, ColorRGB.black());
        final long start = b.getTimer();
        for (int i = 0; i < ITERATIONS; i++) {
            b.updatePhysics(DELTA, null);
        }
        assertTrue(start > b.getTimer());
    }

}
