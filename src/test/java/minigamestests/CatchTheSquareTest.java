package minigamestests;

import game.controlling.Input;
import game.controlling.KeyboardInput;
import game.gameobject.GameObject;
import game.minigame.CatchTheSquare;
import game.minigame.Minigame;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import api.Point2D;
import api.Vector2D;

/**
 * Unit test for the minigame CatchTheSquare.
 */
class CatchTheSquareTest {
    private static final double SLOWER_COEFFIECIENT = 0.5;
    private static final int SPEEDER_COEFFICIENT = 3;
    private static final int TIME_TO_WAIT = 100_000;
    private static final int REPETITIONS = 100;
    private static final int ELAPSED_TIME = 10;
    private static final int BOUND_HEIGHT = 900;
    private static final int BOUND_LENGHT = 1600;
    private static final int MAX_SPEED = 200;
    private static final int MIN_SPEED = 100;
    private static final int START_SPEED = 50;

    /**
     * Test to check if bombs explode after some time.
     */
    @Test
    void testExplosion() {
        final Minigame cTS = new CatchTheSquare();
        for (int n = 0; n < REPETITIONS; n++) {
            cTS.compute(TIME_TO_WAIT / REPETITIONS); // just waits
        }
        assertTrue(cTS.isGameOver()); // bomb should be exploded
    }

    @Test
    void testDefusion() {
        final Minigame cTS = new CatchTheSquare();
        final GameObject defuser = cTS.getGameObjects().get(0);
        defuser.setVel(new Vector2D(START_SPEED, -START_SPEED)); // launches the defuser
        while (cTS.getGameObjects().size() < 2) {
            cTS.compute(ELAPSED_TIME); // wait until some bomb is spawned
        }
        int objectsNold = cTS.getGameObjects().size();
        while (objectsNold <= cTS.getGameObjects().size()) { // keep checking if any bomb has been defused
            objectsNold = cTS.getGameObjects().size();
            cTS.compute(ELAPSED_TIME);
            if (defuser.getVel().module() < MIN_SPEED) { // limit low speed
                defuser.setVel(defuser.getVel().invert().mul(SPEEDER_COEFFICIENT));
            }
            if (defuser.getVel().module() > MAX_SPEED) { // limit high speed
                defuser.setVel(defuser.getVel().invert().mul(SLOWER_COEFFIECIENT));
            }
        }
        assertFalse(cTS.isGameOver()); // game should be ok and running
    }

    @Test
    void testControls() {
        final Minigame cTS = new CatchTheSquare();
        final Input input = new KeyboardInput();
        input.setMoveDown(true);
        cTS.getGameObjects().forEach(o -> o.updateinput(input, ELAPSED_TIME));
        cTS.compute(ELAPSED_TIME);
        assertNotEquals(cTS.getGameObjects().get(0).getVel(), Vector2D.nullVector());
        assertNotEquals(cTS.getGameObjects().get(0).getCoor(), new Point2D(BOUND_LENGHT / 2, BOUND_HEIGHT / 2));

    }
}
