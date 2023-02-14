package game.gameobject.catchthesqareobjects;

import game.gameobject.GameObject;
import game.gameobject.PhysicsModel;
import game.minigame.Minigame;

/**
 * PhysicsModel for BombSquare objects and subclasses that subtracts the elapsed
 * time to the bomb timer.
 */
public class TimerPhysics implements PhysicsModel {
    /**
     * Checks if applied to a BombSquare object and subtracts the elapsed time to
     * its timer.
     */
    @Override
    public void update(final long dt, final GameObject obj, final Minigame miniGame) {
        if (!(obj instanceof BombSquare)) {
            throw new IllegalCallerException("TimerPhysics can be used only on BombSquare or his subclasses");
        }
        final BombSquare bObj = (BombSquare) obj;
        bObj.setTimer(bObj.getTimer() - dt);

    }

}
