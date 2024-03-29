package minigamestests.catchthesquaretest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.junit.jupiter.api.Test;

import api.Point2D;
import api.Vector2D;
import game.controlling.Input;
import game.controlling.KeyboardInput;
import game.engine.difficultystrats.IncrRateStrat;
import game.engine.gameobject.DirectionalLinearInput;
import game.engine.gameobject.GameObject;
import game.engine.gameobject.InputModel;
import game.engine.gameobject.catchthesqareobjects.CtsBomb;
import game.engine.gameobject.catchthesqareobjects.DirectionalInput;
import game.engine.minigame.CatchTheSquare;
import game.engine.minigame.Minigame;

/**
 * Unit test for the minigame CatchTheSquare.
 */
class CatchTheSquareTest {
    private static final int SPEED_COEF = 5;
    private static final int FRAME_HEIGHT = 900;
    private static final int FRAME_LENGHT = 1600;
    private static final int START_SPEED = 200;
    private static final int TIME_TO_WAIT = 100_000;
    private static final int REPETITIONS = 10_000;
    private static final int ELAPSED_TIME = 10;
    private static final int BOUND_HEIGHT = 900;
    private static final int BOUND_LENGHT = 1600;
    private static final double BOMB_SPAWN_DIFF = 1.05;
    private static final double MAX_BOMB_RATE = 0.7;
    private static final List<Function<Long, Long>> SPAWN_STRATS = List
            .of(new IncrRateStrat(BOMB_SPAWN_DIFF, MAX_BOMB_RATE));
    private static final List<InputModel> INPUT_MODEL_STRATS = List.of(new DirectionalInput(),
            new DirectionalLinearInput());

    /**
     * Test to check if bombs explode after some time.
     */
    @Test
    void testExplosion() {
        for (final InputModel inputModel : INPUT_MODEL_STRATS) {
            for (final Function<Long, Long> spawnStrat : SPAWN_STRATS) {
                final Minigame cTS = new CatchTheSquare(spawnStrat,
                        inputModel, FRAME_HEIGHT);
                for (int n = 0; n < REPETITIONS; n++) {
                    cTS.compute(TIME_TO_WAIT / REPETITIONS); // just waits
                }
                assertTrue(cTS.isGameOver()); // bomb should be exploded
            }
        }
    }

    @Test
    void testDefusion() {
        for (final InputModel inputModel : INPUT_MODEL_STRATS) {
            for (final Function<Long, Long> spawnStrat : SPAWN_STRATS) {
                final Minigame cTS = new CatchTheSquare(spawnStrat,
                        inputModel, FRAME_HEIGHT);
                final GameObject defuser = cTS.getObjects().get(0);
                while (cTS.getObjects().size() < 2) {
                    cTS.compute(ELAPSED_TIME); // wait until some bomb is spawned
                }
                int objectsNold = cTS.getObjects().size();
                while (objectsNold <= cTS.getObjects().size()) { // keep checking if any bomb has been defused
                    objectsNold = cTS.getObjects().size();
                    final Optional<GameObject> b = cTS.getObjects().stream().filter(o -> o instanceof CtsBomb)
                            .findAny();
                    if (b.isPresent()) {
                        defuser.setCoor(b.get().getCoor());
                    }
                    cTS.compute(ELAPSED_TIME);
                    assertFalse(cTS.isGameOver()); // game should be ok and running
                }
            }
        }
    }

    @Test
    void testControls() {
        for (final InputModel inputModel : INPUT_MODEL_STRATS) {
            for (final Function<Long, Long> spawnStrat : SPAWN_STRATS) {
                final Minigame cTS = new CatchTheSquare(spawnStrat,
                        inputModel, FRAME_HEIGHT);
                final Input input = new KeyboardInput();
                input.setMoveDown(true);
                cTS.getObjects().forEach(o -> o.updateinput(input, ELAPSED_TIME));
                cTS.compute(ELAPSED_TIME);
                assertNotEquals(cTS.getObjects().get(0).getVel(), Vector2D.nullVector());
                assertNotEquals(cTS.getObjects().get(0).getCoor(), new Point2D(BOUND_LENGHT / 2, BOUND_HEIGHT / 2));

            }
        }
    }

    @Test
    void testBoundary() {
        for (final InputModel inputModel : INPUT_MODEL_STRATS) {
            for (final Function<Long, Long> spawnStrat : SPAWN_STRATS) {
                final Minigame cTS = new CatchTheSquare(spawnStrat,
                        inputModel, FRAME_HEIGHT);
                final GameObject defuser = cTS.getObjects().get(0);
                defuser.setVel(new Vector2D(START_SPEED, START_SPEED));
                for (int n = 0; n < REPETITIONS; n++) {
                    cTS.compute(ELAPSED_TIME);
                    if (defuser.getVel().module() < START_SPEED / 2) {
                        defuser.setVel(defuser.getVel().mul(SPEED_COEF));
                    }
                    assertTrue(defuser.getCoor().getX() >= 0);
                    assertTrue(defuser.getCoor().getX() <= FRAME_LENGHT);
                    assertTrue(defuser.getCoor().getY() >= 0);
                    assertTrue(defuser.getCoor().getY() <= FRAME_HEIGHT);
                }

            }
        }
    }
}
