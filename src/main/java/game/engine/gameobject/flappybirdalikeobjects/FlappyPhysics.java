package game.engine.gameobject.flappybirdalikeobjects;

import api.Point2D;
import api.Vector2D;
import game.engine.gameobject.GameObject;
import game.engine.gameobject.SimplePhysics;
import game.engine.minigame.Minigame;

/**
 * PhysicsModel that prevents the player from going out of bounds.
 * 
 */
public class FlappyPhysics extends SimplePhysics {

    private static final double Y_MIN = 0;
    private static final double Y_MAX = 1000;
    private final double cursorOffset;

    /**
     * Constructor for FlappyPhysics.
     * 
     * @param cursorSize the size of the player's cursor
     */
    public FlappyPhysics(final double cursorSize) {
        this.cursorOffset = cursorSize / 2;
    }

    /**
     * Method to update the position of the cursor and correct it
     * if outside the boundaries.
     * 
     * @param dt       the elapsed time between frames.
     * @param obj      this gameObject.
     * @param miniGame the minigame of the gameObject.
     */
    @Override
    public void update(final long dt, final GameObject obj, final Minigame miniGame) {
        super.update(dt, obj, miniGame);
        if (obj.getCoor().getY() < Y_MIN + cursorOffset) {
            obj.setCoor(new Point2D(obj.getCoor().getX(), Y_MIN + cursorOffset));
            obj.setVel(Vector2D.nullVector());
        }
        if (obj.getCoor().getY() > Y_MAX - cursorOffset) {
            obj.setCoor(new Point2D(obj.getCoor().getX(), Y_MAX - cursorOffset));
            obj.setVel(Vector2D.nullVector());
        }
    }

}
