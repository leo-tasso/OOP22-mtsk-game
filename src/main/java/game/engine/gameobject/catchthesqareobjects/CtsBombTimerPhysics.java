package game.engine.gameobject.catchthesqareobjects;

import game.engine.gameobject.GameObject;
import game.engine.gameobject.PhysicsModel;
import game.engine.minigame.Minigame;

/**
 * PhysicsModel for Bomb objects and subclasses that subtracts the elapsed
 * time to the bomb timer.
 */
public class CtsBombTimerPhysics implements PhysicsModel {
    /**
     * Checks if applied to a Bomb object and subtracts the elapsed time to
     * its timer.
     */
    @Override
    public void update(final long dt, final GameObject obj, final Minigame miniGame) {
        if (!(obj instanceof CtsBomb)) {
            throw new IllegalCallerException("TimerPhysics can be used only on Bomb or his subclasses");
        }
        final CtsBomb bObj = (CtsBomb) obj;
        bObj.setTimer(bObj.getTimer() - dt);

    }

}
