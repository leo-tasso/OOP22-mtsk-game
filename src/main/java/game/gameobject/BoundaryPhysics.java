package game.gameobject;

import api.Vector2D;
import game.minigame.Minigame;

/**
 * Class to model a PhysicsModel that moves the object according its velocity
 * but makes it bouce on the borders.
 */
public class BoundaryPhysics extends SimplePhysics {
    private static final int RIGHT_LIMIT = 1600;
    private static final int BOTTOM_LIMIT = 900;
    private final int radius;

    /**
     * Constructor for the model with the radius of the bounding box of an object.
     * 
     * @param radius
     */
    public BoundaryPhysics(final int radius) {
        this.radius = radius;
    }

    /**
     * Method to update the coordinates and the speed of the object.
     */
    @Override
    public void update(final long dt, final GameObject obj, final Minigame miniGame) {
        super.update(dt, obj, miniGame);
        if (obj.getCoor().getY() > BOTTOM_LIMIT - radius) {
            obj.setVel(new Vector2D(obj.getVel().getX(), -Math.abs(obj.getVel().getY())));
        }
        if (obj.getCoor().getX() > RIGHT_LIMIT - radius) {
            obj.setVel(new Vector2D(-Math.abs(obj.getVel().getX()), obj.getVel().getY()));
        }
        if (obj.getCoor().getX() < 0 + radius) {
            obj.setVel(new Vector2D(Math.abs(obj.getVel().getX()), obj.getVel().getY()));
        }
        if (obj.getCoor().getY() < 0 + radius) {
            obj.setVel(new Vector2D(obj.getVel().getX(), Math.abs(obj.getVel().getY())));

        }
    }

}
