package minigamestests.whacamoletest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.BinaryOperator;
import game.controlling.Input;
import game.controlling.KeyboardInput;
import game.engine.gameobject.GameObject;
import game.engine.gameobject.whacamoleobjects.Mole;
import game.engine.gameobject.whacamoleobjects.Status;
import game.engine.gameobject.whacamoleobjects.WamBomb;
import game.engine.gameobject.whacamoleobjects.WamObject;
import game.engine.minigame.Minigame;
import game.engine.minigame.whacamoleminigame.WhacAMole;

/**
 * Unit test for the minigame Whac-a-Mole.
 */
class WhacAMoleTest {
    private static final int ELAPSED_TIME = 10;
    private static final int FRAME_HEIGHT = 900;

    @Test
    void hitMoleTest() {
        final Minigame wam = new WhacAMole(FRAME_HEIGHT);

        while (wam.getObjects().stream()
                .noneMatch(o -> o instanceof Mole)
            || wam.getObjects().stream()
                .filter(o -> o instanceof Mole)
                .noneMatch(o -> ((WamObject) o).getAppearanceTime() < ((WhacAMole) wam).getCurrentTime())) {
            /* I iterate until at least one mole is drawn and actually */
            /* comes out of one of the holes becoming hittable         */ 
            wam.compute(ELAPSED_TIME);
        }
        /* I pick the Mole with the closest appearance time and I hit it.      */
        /* If any bombs have been drawn in the meantime, this won't cause any  */
        /* problems, as if they aren't hit they won't alter the GameOver value */
        final Optional<WamObject> moleToHit = wam.getObjects().stream()
                .filter(o -> o instanceof Mole)
                .map(o -> (WamObject) o)
                .reduce(BinaryOperator.minBy(Comparator.comparingLong(WamObject::getAppearanceTime)));

        final Input input = new KeyboardInput();
        input.setNumberPressed(Optional.of(moleToHit.get().getHoleNumber()));
        moleToHit.get().updateinput(input, ELAPSED_TIME);
        assertTrue(moleToHit.get().getStatus().equals(Status.HIT) && !wam.isGameOver());
    }

    @Test
    void missMoleTest() {
        final Minigame wam = new WhacAMole(FRAME_HEIGHT);

        while (wam.getObjects().stream()
                .noneMatch(o -> o instanceof Mole)
            || wam.getObjects().stream()
                .filter(o -> o instanceof Mole)
                .noneMatch(o -> ((WamObject) o).getStatus().equals(Status.IN_MOTION))) {
            wam.compute(ELAPSED_TIME);
        }

        final Optional<WamObject> moleToMiss = wam.getObjects().stream()
            .filter(o -> o instanceof Mole)
            .map(o -> (WamObject) o)
            .reduce(BinaryOperator.minBy(Comparator.comparingLong(WamObject::getAppearanceTime)));
        /* I iterate until the mole re-enters the den that */
        /* has been assigned to it, without hitting it     */
        while (moleToMiss.get().getCoor().getY() <= moleToMiss.get().getStartCoor().getY()) {
            wam.compute(ELAPSED_TIME);
        }
        assertTrue(moleToMiss.get().getStatus().equals(Status.MISSED) && wam.isGameOver());
    }

    @Test
    void hitWamBombTest() {
        final Minigame wam = new WhacAMole(FRAME_HEIGHT);

        while (wam.getObjects().stream()
                .noneMatch(o -> o instanceof WamBomb)
            || wam.getObjects().stream()
                .filter(o -> o instanceof WamBomb)
                .noneMatch(o -> ((WamObject) o).getStatus().equals(Status.IN_MOTION))) {
            /* I iterate until at least one bomb is drawn and actually */
            /* comes out of one of the holes becoming hittable         */
            deleteMoles(wam);
            wam.compute(ELAPSED_TIME);
        }

        final Optional<WamObject> bombToHit = wam.getObjects().stream()
                .filter(o -> o instanceof WamBomb)
                .map(o -> (WamObject) o)
                .reduce(BinaryOperator.minBy(Comparator.comparingLong(WamObject::getAppearanceTime)));

        final Input input = new KeyboardInput();
        input.setNumberPressed(Optional.of(bombToHit.get().getHoleNumber()));
        bombToHit.get().updateinput(input, ELAPSED_TIME);
        assertTrue(bombToHit.get().getStatus().equals(Status.HIT) && wam.isGameOver());
    }

    @Test
    void missWamBombTest() {
        final Minigame wam = new WhacAMole(FRAME_HEIGHT);

        while (wam.getObjects().stream()
                .noneMatch(o -> o instanceof WamBomb)
            || wam.getObjects().stream()
                .filter(o -> o instanceof WamBomb)
                .noneMatch(o -> ((WamObject) o).getAppearanceTime() < ((WhacAMole) wam).getCurrentTime())) {
            deleteMoles(wam);
            wam.compute(ELAPSED_TIME);
        }

        final Optional<WamObject> bombToMiss = wam.getObjects().stream()
                .filter(o -> o instanceof WamBomb)
                .map(o -> (WamObject) o)
                .reduce(BinaryOperator.minBy(Comparator.comparingLong(WamObject::getAppearanceTime)));
        /* After taking the reference to the closest bomb    */
        /* in terms of appearance, I make it exit and return */
        /* to the lair, then verifying that it is correctly  */
        /* marked as MISSED and that the game does not end   */
        while (bombToMiss.get().getCoor().getY() <= bombToMiss.get().getStartCoor().getY()) {
            wam.compute(ELAPSED_TIME);
        }
        assertTrue(bombToMiss.get().getStatus().equals(Status.MISSED) && !wam.isGameOver());
    }

    /** 
     * I need to eliminate all possible moles, since by not
     * handling their input (not hitting them) they could be the
     * cause of the gameOver and produce a "false positive" test.
     * 
     * @param wam the Whac-a-Mole Minigame
     */
    private void deleteMoles(final Minigame wam) {
        final List<GameObject> moles = wam.getObjects().stream()
                .filter(o -> o instanceof Mole)
                .toList();
        final List<WamObject> objs = new ArrayList<>();
        wam.getObjects().stream()
                .map(o -> (WamObject) o)
                .forEach(o -> objs.add(o));
        objs.removeAll(moles);
        ((WhacAMole) wam).setObjects(objs);
    }
}
