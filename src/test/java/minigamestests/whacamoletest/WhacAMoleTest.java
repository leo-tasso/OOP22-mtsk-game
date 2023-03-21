package minigamestests.whacamoletest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.BinaryOperator;
import game.controlling.Input;
import game.controlling.KeyboardInput;
import game.engine.gameobject.GameObject;
import game.engine.gameobject.catchthesqareobjects.Bomb;
import game.engine.gameobject.whacamoleobjects.Mole;
import game.engine.gameobject.whacamoleobjects.Status;
import game.engine.gameobject.whacamoleobjects.WamObject;
import game.engine.minigame.Minigame;
import game.engine.minigame.whacamoleminigame.WhacAMole;

/**
 * Unit test for the minigame Whac-a-Mole.
 */
class WhacAMoleTest {
    private static final int ELAPSED_TIME = 10;

    @Test
    void hitMoleTest() {
        final Minigame wam = new WhacAMole();

        while (wam.getObjects().stream()
                .noneMatch(o -> o instanceof Mole)
            ||  wam.getObjects().stream()
                .filter(o -> o instanceof Mole)
                .noneMatch(o -> ((WamObject) o).getAppearanceTime() > ((WhacAMole) wam).getCurrentTime())) {
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
}
