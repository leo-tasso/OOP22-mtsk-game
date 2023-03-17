package game.controlling;

import api.Point2D;
import api.Vector2D;
import game.engine.gameobject.GameObject;

/**
 * Class that takes care of changing the position of 
 * the main character based on the input received.
 */
public class DodgerInputModel implements InputModel {

    private final Point2D forward;
    private final Point2D backwards;
    private final int limitLow;
    private final int limitHigh;

    /**
     * Constructor for the dodger's input model.
     * 
     * @param step     the unit of the dodger's movement
     * @param initialY the initial Y coordinate 
     */
    public DodgerInputModel(final int step, final int initialY) {
        this.forward = new Point2D(0, -step);
        this.backwards = new Point2D(0, step);
        this.limitLow = initialY - 2 * step;
        this.limitHigh = initialY + 2 * step;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(GameObject obj, Input c, long elapsedTime) {
        if (c.isForward() && obj.getCoor().getY() > limitLow) {
            obj.setCoor(obj.getCoor().sum(forward));
        } else if (c.isBackwards() && obj.getCoor().getY() < limitHigh) {
            obj.setCoor(obj.getCoor().sum(backwards));
        }
        obj.setVel(Vector2D.nullVector());
    }   
}
