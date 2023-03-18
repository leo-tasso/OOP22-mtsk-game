package minigamestests.dodgeatriangletest;

import org.junit.jupiter.api.Test;

import api.Point2D;
import game.controlling.Input;
import game.controlling.KeyboardInput;
import game.engine.gameobject.dodgeatriangleobjects.SlotAspect;
import game.engine.minigame.DodgeATriangle;
import game.engine.minigame.Minigame;
import javafx.scene.effect.Light.Point;

/**
 * Unit test for the minigame DodgeATriangle.
 */
class DodgeATriangleTest {

    private static final long ELAPSED_TIME = 10L;

    @Test
    void hitboxCheck() {
        final Minigame m = new DodgeATriangle();
        final Point2D center; /* 
        while(m.getObjects().size() == 1 
            && m.getObjects().get(0).getCoor().getX() != ) {
            m.compute(ELAPSED_TIME);
        }
        */
    }


}