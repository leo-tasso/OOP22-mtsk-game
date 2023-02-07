package game.controlling;

import api.Input;
import api.InputModel;
import api.Vector2D;
import game.gameobject.GameObject;

/**
 * Updates the direction of a GameObject.
 */
public class DirectionalInput implements InputModel {

    private static final double CHANGE_COEFFICENT = 0.01;

    /**
     * Method to update the object velocity according to the inputs.
     */
    @Override
    public void update(final GameObject obj, final Input c) {
        if (c.isMoveUp()) {
            obj.setVel(new Vector2D(obj.getVel().getX(), obj.getVel().getY() - CHANGE_COEFFICENT));
        }
        if (c.isMoveDown()) {
            obj.setVel(new Vector2D(obj.getVel().getX(), obj.getVel().getY() + CHANGE_COEFFICENT));
        }
        if (c.isMoveLeft()) {
            obj.setVel(new Vector2D(obj.getVel().getX() - CHANGE_COEFFICENT, obj.getVel().getY()));
        }
        if (c.isMoveRight()) {
            obj.setVel(new Vector2D(obj.getVel().getX() + CHANGE_COEFFICENT, obj.getVel().getY()));
        }
    }
}
