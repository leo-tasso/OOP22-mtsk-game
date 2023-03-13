package game.engine.gameobject.catchthesqareobjects;

import api.Point2D;
import api.Vector2D;
import game.engine.gameobject.GameObject;
import game.engine.gameobject.SimplePhysics;
import game.engine.minigame.Minigame;

/**
 * Class to model a PhysicsModel that moves the object according its velocity
 * but makes it bouce on the borders.
 */
public class BoundaryDumpedPhysics extends SimplePhysics {
    private static final int RIGHT_LIMIT = 1600;
    private static final int BOTTOM_LIMIT = 900;
    private final int radius;
    private final double dumpCoefficient;

    /**
     * Constructor for the model with the radius of the bounding box of an object.
     * 
     * @param radius          the radious of the circular bounding box of the
     *                        defuser
     * @param dumpCoefficient the dump coefficient of a bouce on the border, set to
     *                        one to retain all the energy
     */
    public BoundaryDumpedPhysics(final int radius, final double dumpCoefficient) {
        this.radius = radius;
        this.dumpCoefficient = dumpCoefficient;
    }

    /**
     * Method to update the coordinates and the speed of the object.
     */
    @Override
    public void update(final long dt, final GameObject obj, final Minigame miniGame) {
        super.update(dt, obj, miniGame);
        if (obj.getCoor().getY() > BOTTOM_LIMIT - radius) {
            obj.setCoor(new Point2D(obj.getCoor().getX(), BOTTOM_LIMIT - radius));
            obj.setVel(new Vector2D(obj.getVel().getX(), -Math.abs(obj.getVel().getY() / dumpCoefficient)));
        }
        if (obj.getCoor().getX() > RIGHT_LIMIT - radius) {
            obj.setCoor(new Point2D(RIGHT_LIMIT - radius, obj.getCoor().getY()));
            obj.setVel(new Vector2D(-Math.abs(obj.getVel().getX() / dumpCoefficient), obj.getVel().getY()));
        }
        if (obj.getCoor().getX() < 0 + radius) {
            obj.setCoor(new Point2D(radius, obj.getCoor().getY()));
            obj.setVel(new Vector2D(Math.abs(obj.getVel().getX() / dumpCoefficient), obj.getVel().getY()));
        }
        if (obj.getCoor().getY() < 0 + radius) {
            obj.setCoor(new Point2D(obj.getCoor().getX(), radius));
            obj.setVel(new Vector2D(obj.getVel().getX(), Math.abs(obj.getVel().getY() / dumpCoefficient)));

        }
    }

}
