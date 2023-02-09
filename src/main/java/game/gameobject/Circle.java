package game.gameobject;

import api.Point2D;
import api.Vector2D;
import game.controlling.DirectionalInput;

/**
 * Game Object rapresenting a circle.
 */
public class Circle extends GameObject {

    /**
     * Constructor for the gameobject circle.
     * 
     * @param coor the initial coordinate.
     * @param vel  initial velocity vector.
     */
    public Circle(final Point2D coor, final Vector2D vel) {
        super(coor, vel);
        setInputModel(new DirectionalInput());
        setPhysicsModel(new SimplePhysics());
    }

}
