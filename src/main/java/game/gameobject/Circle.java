package game.gameobject;

import api.Point2D;
import api.Vector2D;

/**
 * Game Object rapresenting a circle.
 */
public class Circle extends GameObject {

    private static final int HIGH_BOUND = 600;
    private static final int LOW_BOUND = 300;
    private static final double SPEED = 0.1;
/**
 * Constructor for the gameobject circle.
 * @param coor the initial coordinate.
 * @param vel initial velocity vector.
 */
    public Circle(final Point2D coor, final Vector2D vel) {
        super(coor, vel);
    }

    /**
     *  Updates the Circle position and speed.
     */
    @Override
    public void update(final long time) {
        if (this.getCoor().getY() > HIGH_BOUND) {
            this.setVel(new Vector2D(0, -SPEED));
        }
        if (this.getCoor().getY() < LOW_BOUND) {
            this.setVel(new Vector2D(0, SPEED));
        }
        this.setCoor(this.getCoor().sum(this.getVel().mul(time)));
    }
}
