package game.controlling;

import api.Vector2D;
import game.engine.gameobject.GameObject;

/**
 * Input model to move cursor in FlappyBirdAlike.
 */
public class FlappyInput implements InputModel {

    private static final double SPEED_RATIO = -75d;
    private final double upwardSpeed;
    private boolean hold;

    /**
     * Costructor that sets the cursor's jump strenth.
     * 
     * @param height the height of the game's world
     */
    public FlappyInput(final int height) {
        this.upwardSpeed = height / SPEED_RATIO;
    }

    /**
     * This method sets a constant upward speed when necessary.
     */
    @Override
    public void update(final GameObject obj, final Input c, final long elapsedTime) {
        if (c.isJump() && !hold) {
            this.hold = true;
            obj.setVel(new Vector2D(0, upwardSpeed * elapsedTime));
        }

        if (!c.isJump() && hold) {
            this.hold = false;
        }
    }
}
