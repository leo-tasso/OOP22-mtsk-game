package game.engine.gameobject.flappybirdalikeobjects;

import api.Point2D;
import api.Vector2D;
import game.controlling.DirectionalInput;
import game.controlling.Input;
import game.controlling.InputModel;
import game.engine.gameobject.GameObject;

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
    public Cursor(final Point2D coor, final Vector2D vel, final double size, final double xSpeed) {
        super(coor, vel);
        this.setInputModel(new InputModel() {

            private final InputModel standard = new DirectionalInput();

            @Override
            public void update(final GameObject obj, final Input c, final long elapsedTime) {
                if (c.isMoveDown() || c.isMoveUp()) {
                    standard.update(obj, c, elapsedTime);
                }
            }

        });
        this.setPhysicsModel(new FlappyPhysics(size));
        this.setAspectModel(new CursorAspect(size, xSpeed));
    }
}
