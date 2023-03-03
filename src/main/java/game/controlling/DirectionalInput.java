package game.controlling;

import api.Vector2D;
import game.engine.gameobject.GameObject;

/**
 * Updates the direction of a GameObject.
 */
public class DirectionalInput implements InputModel {

    private static final double CHANGE_COEFFICENT = 0.1;

    /**
     * Depending on the input received, considering that the Cartesian
     * coordinates are equal to (0,0) in the upper left corner, we
     * modify the vector representing the speed of the GameObject.
     */
    @Override
    public void update(final GameObject obj, final Input c, final long elapsedTime) {
        if (c.isMoveUp()) {
            obj.setVel(new Vector2D(obj.getVel().getX(), obj.getVel().getY() - CHANGE_COEFFICENT * elapsedTime));
        }
        if (c.isMoveDown()) {
            obj.setVel(new Vector2D(obj.getVel().getX(), obj.getVel().getY() + CHANGE_COEFFICENT * elapsedTime));
        }
        if (c.isMoveLeft()) {
            obj.setVel(new Vector2D(obj.getVel().getX() - CHANGE_COEFFICENT * elapsedTime, obj.getVel().getY()));
        }
        if (c.isMoveRight()) {
            obj.setVel(new Vector2D(obj.getVel().getX() + CHANGE_COEFFICENT * elapsedTime, obj.getVel().getY()));
        }
    }
}
