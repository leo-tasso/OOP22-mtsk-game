package game.controlling;

import api.Vector2D;
import game.engine.gameobject.GameObject;

/**
 * Input model to move cursor in FlappyBirdAlike.
 */
public class FlappyInput implements InputModel {
    private static final double UPWARD_SPEED = -8;

    private boolean hold;

    /**
     * This method sets a constant upward speed when necessary.
     */
    @Override
    public void update(final GameObject obj, final Input c, final long elapsedTime) {
        if (c.isJump() && !hold) {
            this.hold = true;
            obj.setVel(new Vector2D(0, UPWARD_SPEED * elapsedTime));
        }

        if (!c.isJump() && hold) {
            this.hold = false;
        }
    }
}
