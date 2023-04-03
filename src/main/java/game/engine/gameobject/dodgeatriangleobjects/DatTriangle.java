package game.engine.gameobject.dodgeatriangleobjects;

import api.Point2D;
import api.Vector2D;
import game.engine.gameobject.GameObject;
import game.engine.gameobject.NullInput;
import game.engine.gameobject.SimplePhysics;
import game.engine.gameobject.hitboxmodel.RectangleHitBoxModel;

/**
 * Class that models the enemy in the Dodge-a-Triangle 
 * game, as well as triangles that cross the 
 * playing field from right to left or vice versa.
 */
public class DatTriangle extends GameObject {

    /**
     * Simple constructor for DatTriangle fields.
     * 
     * @param coor the initial coor of the triangle
     * @param vel  the speed of the triangle
     * @param side the side of the triangle (equilateral)
     */
    public DatTriangle(final Point2D coor, final Vector2D vel, final int side) {
        super(coor, vel);
        this.setInputModel(new NullInput());
        this.setPhysicsModel(new SimplePhysics());
        this.setAspectModel(new DatTriangleAspectModel(side, coor.getX() > 0));
        this.setHitBoxModel(new RectangleHitBoxModel(side * Math.sqrt(3) / 2, side));
    }
}
