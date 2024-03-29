package game.engine.gameobject.catchthesqareobjects;

import api.ColorRGB;
import api.Point2D;
import api.Vector2D;
import game.engine.gameobject.GameObject;
import game.engine.gameobject.NullInput;
import game.engine.gameobject.hitboxmodel.SquareHitBoxModel;

/**
 * Bomb gameObject, describes a bomb with a timer.
 */
public class CtsBomb extends GameObject {
    private long timer;
    private static final int STARTING_TIMER = 10_000;

    /**
     * Constructor for the object.
     * 
     * @param coor  the spaw coordinate of the center of the bomb.
     * @param side  the side lenght of the bomb or its size in case of a not square
     *              implementation.
     * @param color the color of the bomb.
     */
    public CtsBomb(final Point2D coor, final int side, final ColorRGB color) {
        super(coor, Vector2D.nullVector(), 0, new NullInput(), new CtsBombTimerPhysics(), new CtsBombAspect(side, color),
                new SquareHitBoxModel(side));
        timer = STARTING_TIMER;
    }

    /**
     * Method to get the timer time.
     * 
     * @return the timer time
     */
    public long getTimer() {
        return timer;
    }

    /**
     * Method to set the timer time.
     * 
     * @param timer the timer time.
     */
    public void setTimer(final long timer) {
        this.timer = timer;
    }
}
