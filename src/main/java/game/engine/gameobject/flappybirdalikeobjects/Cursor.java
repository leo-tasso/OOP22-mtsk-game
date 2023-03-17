package game.engine.gameobject.flappybirdalikeobjects;

import api.Point2D;
import api.Vector2D;
import game.controlling.InputModel;
import game.engine.gameobject.GameObject;
import game.engine.gameobject.hitboxmodel.CircleHitBoxModel;

/**
 * A cursor you can move up and down.
 *
 */
public class Cursor extends GameObject {

    /**
     * The constructor for the cursor.
     *
     * @param coor the initial coordinates of the cursor.
     * @param vel  the initial speed of the cursor.
     * @param size the size of the cursor.
     * @param xSpeed the speed at which the cursor 'moves forward'
     */
    public Cursor(final Point2D coor, final Vector2D vel, final double size, final double xSpeed, final InputModel inputModel) {
        super(coor, vel);
        final double radius = size / Math.sqrt(3) * 3 / 4;
        this.setInputModel(inputModel);
        this.setPhysicsModel(new FlappyPhysics(size));
        this.setAspectModel(new CursorAspect(size, xSpeed));
        this.setHitBoxModel(new CircleHitBoxModel(radius));
    }
}
