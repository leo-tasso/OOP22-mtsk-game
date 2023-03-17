package minigamestests.flappybirdalike;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import game.controlling.Input;
import game.controlling.KeyboardInput;
import game.engine.minigame.FlappyBirdAlike;
import game.engine.minigame.Minigame;

/**
 * Unit test for the minigame FlappyBirdAlike
 */
class FlappyBirdAlikeTest {

    private static final int LIMIT_BOTTOM = 800;
    private static final int LIMIT_HIGH = 100;
    private static final int NUM_JUMPS = 100;
    private static final long ELAPSED_TIME = 10L;

    @Test
    void boundaryCheck() {
        final Minigame m = new FlappyBirdAlike();
        assertEquals(m.getObjects().get(0).getCoor().getY(), LIMIT_BOTTOM);
        final Input input = new KeyboardInput();
        for (int i = 0; i < NUM_JUMPS; i++) {
            input.setJump(true);
            m.getObjects().forEach(o -> o.updateinput(input, ELAPSED_TIME));
            m.compute(ELAPSED_TIME);
            input.setJump(false);
            assertTrue(m.getObjects().get(0).getCoor().getY() >= LIMIT_HIGH);
        }
    }

    @Test
    void closestObstacleCheck() {
        final Minigame m = new FlappyBirdAlike();
        while (m.getObjects().size() < 3) {
            m.compute(ELAPSED_TIME);
        }

        assertTrue(m.getObjects().get(1).getCoor().getX() < m.getObjects().get(2).getCoor().getX());
    }

    @Test
    void hitboxCheck() {

    }

    @Test
    void speedCheck() {

    }
    
}
