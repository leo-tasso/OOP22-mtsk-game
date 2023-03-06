package game.controlling;

import api.Vector2D;
import game.gameobject.GameObject;
/**
 * Class to model a directional input with constant speed.
 */
public class DirectionalLinearInput implements InputModel {
    private static final double CHANGE_COEFFICENT = 10;

    /**
     * Depending on the input received, considering that the Cartesian
     * coordinates are equal to (0,0) in the upper left corner, we
     * modify the vector representing the speed of the GameObject.
     */
    @Override
    public void update(final GameObject obj, final Input c, final long elapsedTime) {
        obj.setVel(Vector2D.nullVector());
        if (c.isMoveUp()) {
            obj.setVel(obj.getVel().sum(new Vector2D(0, -CHANGE_COEFFICENT * elapsedTime)));
        }
        if (c.isMoveDown()) {
            obj.setVel(obj.getVel().sum(new Vector2D(0, CHANGE_COEFFICENT * elapsedTime)));
        }
        if (c.isMoveLeft()) {
            obj.setVel(obj.getVel().sum(new Vector2D(-CHANGE_COEFFICENT * elapsedTime, 0)));
        }
        if (c.isMoveRight()) {
            obj.setVel(obj.getVel().sum(new Vector2D(CHANGE_COEFFICENT * elapsedTime, 0)));
        }
    }
}
