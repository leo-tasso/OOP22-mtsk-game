package minigamestests.flappybirdalike;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import game.engine.minigame.FlappyBirdAlike;
import game.engine.minigame.Minigame;

/**
 * Unit test for the minigame FlappyBirdAlike
 */
class FlappyBirdAlikeTest {

    @Test
    void boundaryCheck() {
        final Minigame m = new FlappyBirdAlike();
        assertEquals(m.getObjects().get(0).getCoor().getY(), 0);
    }

    @Test
    void closestObstacleCheck() {

    }

    @Test
    void hitboxCheck() {

    }

    @Test
    void speedCheck() {

    }
    
}
