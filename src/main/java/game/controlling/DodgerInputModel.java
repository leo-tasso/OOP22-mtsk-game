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
    private boolean hold;

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
     * If the player asks to move the dodger up, and it isn't 
     * already in the topmost slot (in which case it would stay 
     * there) then he moves it, s.ame applies to the lowest slot. 
     * It takes multiple frames for the user to press a button, 
     * and I have to treat the press as a single movement.
     * 
     */
    @Override
    public void update(final GameObject obj, final Input c, final long elapsedTime) {
        if (!hold && c.isForward() && obj.getCoor().getY() > limitLow) {
            obj.setCoor(obj.getCoor().sum(forward));
            this.hold = true;
        } else if (!hold && c.isBackwards() && obj.getCoor().getY() < limitHigh) {
            obj.setCoor(obj.getCoor().sum(backwards));
            this.hold = true;
        } else if (!(c.isForward() || c.isBackwards())) {
            this.hold = false;
        }
        obj.setVel(Vector2D.nullVector());
    }
}
