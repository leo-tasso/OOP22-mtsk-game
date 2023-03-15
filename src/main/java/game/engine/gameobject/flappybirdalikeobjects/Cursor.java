package game.engine.gameobject.flappybirdalikeobjects;

import api.Point2D;
import api.Vector2D;
import game.controlling.Input;
import game.controlling.InputModel;
import game.engine.gameobject.GameObject;
import game.engine.gameobject.hitboxmodel.CircleHitBoxModel;

/**
 * A cursor you can move up and down.
 *
 */
public class Cursor extends GameObject {
    private static final double UPWARD_SPEED = -8;

    /**
     * The constructor for the cursor.
     *
     * @param coor the initial coordinates of the cursor.
     * @param vel  the initial speed of the cursor.
     * @param size the size of the cursor.
     * @param xSpeed the speed at which the cursor 'moves forward'
     */
    public Cursor(final Point2D coor, final Vector2D vel, final double size, final double xSpeed) {
        super(coor, vel);
        final double radius = size / Math.sqrt(3) * 3 / 4;
        this.setInputModel(new InputModel() {

            private boolean hold;

            @Override
            public void update(final GameObject obj, final Input c, final long elapsedTime) {
                if (c.isJump() && !hold) {
                    this.hold = true;
                    obj.setVel(new Vector2D(0, UPWARD_SPEED * elapsedTime));
                }

                if (!c.isJump() && hold) {
                    this.hold = false;
                }
            }

        });
        this.setPhysicsModel(new FlappyPhysics(size));
        this.setAspectModel(new CursorAspect(size, xSpeed));
        this.setHitBoxModel(new CircleHitBoxModel(radius));
    }
}
