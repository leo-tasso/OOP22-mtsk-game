package game.engine.gameobject;

import api.Point2D;
import api.Vector2D;
import game.engine.minigame.Minigame;

/**
 * Simple PhysicsModel that updates the position according the speed.
 */
public class SimplePhysics implements PhysicsModel {

    private static final double SPEED_COEFF = 0.01;

    /**
     * Method to update the position of a
     * GameObject according to his velocity.
     * 
     * @param dt       the elapsed time between frames.
     * @param obj      this gameObject.
     * @param miniGame the minigame of the gameObject.
     */
    @Override
    public void update(final long dt, final GameObject obj, final Minigame miniGame) {
        final Point2D pos = obj.getCoor();
        final Vector2D vel = obj.getVel();
        obj.setCoor(pos.sum(vel.mul(SimplePhysics.SPEED_COEFF * dt)));
        // TODO remove, debug only System.out.println(obj.getCoor());
    }
}
